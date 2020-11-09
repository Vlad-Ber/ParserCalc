package org.ioopm.calculator.ast;

import java.util.*;

public class EvaluationVisitor implements Visitor {
    private Stack<Environment> stack = new Stack<Environment>();
    private Environment env = null;

    public SymbolicExpression evaluate(SymbolicExpression topLevel, Environment envi) {
	//this.env = envi;
	stack.push(envi);
	return topLevel.accept(this);
    }
    
    // This method gets called from Addition.accept(Visitor v) -- you should
    // be able to see from the eval() methods how these should behave (i.e., 
    // compare this method with your Addition::eval() and Symbolic.addition)
    @Override
    public SymbolicExpression visit(Addition n) {
	// Visit the left hand side and right hand side subexpressions
	SymbolicExpression left = n.getLeft().accept(this);
	SymbolicExpression right = n.getRight().accept(this);
	// When we come back here, the visitor has visited all subexpressions, 
	// meaning left and right point to newly created trees reduced to 
	// the extent possible (best case -- both are constants)

	// If subexpressions are fully evaluated, replace them in
	// the tree with a constant whose value is the sub of the
	// subexpressions, if not, simply construct a new addition
	// node from the new subexpressions
	if (left.isConstant() && right.isConstant()) {
	    return new Constant(left.getValue() + right.getValue());
	} else {
	    return new Addition(left, right);
	}
    }
    
    @Override
    public SymbolicExpression visit(Assignment n) {
	 	
	SymbolicExpression expression = n.getLeft().accept(this);
	SymbolicExpression key = n.getRight();
	Environment tmpEnv = stack.peek();
	
	if(n.getNamed().isNamedConstant(key.toString())) {
	    throw new IllegalArgumentException("Variable cannot be a named constant!");
	}
	    
	if (!key.isVariable()) throw new IllegalArgumentException("Variable not valid!");
	else {	    
	    tmpEnv.put((Variable) key, expression);
	}			    
	if (expression.isConstant()) return new Constant(expression.getValue());
	return expression;
    }
    
    @Override
    public SymbolicExpression visit(Constant n) {
	return new Constant (n.getValue());
    }

    @Override
    public SymbolicExpression visit(Cos n){
	SymbolicExpression arg = n.getNode().accept(this);
	if (arg.isConstant()) {
	    return new Constant(Math.cos(arg.getValue()));
	} else {
	    return new Cos(arg);
	}
    }    
    
    @Override
    public SymbolicExpression visit(Division n) {
	SymbolicExpression arg1 = n.getLeft().accept(this);
	SymbolicExpression arg2 = n.getRight().accept(this);
	if (arg1.isConstant() && arg2.isConstant()) {
	    return new Constant(arg1.getValue() / arg2.getValue());
	}
	else return new Division(arg1, arg2);
    }
    
    @Override
    public SymbolicExpression visit(Exp n) {
	SymbolicExpression arg = n.getNode().accept(this);
	if (arg.isConstant()) {
	    return new Constant(Math.exp(arg.getValue()));
	} else {
	    return new Exp(arg);
	}
    }
    
    @Override
    public SymbolicExpression visit(Log n) {
	SymbolicExpression arg = n.getNode().accept(this);
	if (arg.isConstant()) {
	    return new Constant(Math.log(arg.getValue()));
	} else {
	    return new Log(arg);
	}
    }
    
    @Override
    public SymbolicExpression visit(Multiplication n) {
	SymbolicExpression arg1 = n.getLeft().accept(this);
	SymbolicExpression arg2 = n.getRight().accept(this);
	if (arg1.isConstant() && arg2.isConstant()) {
	    return new Constant(arg1.getValue() * arg2.getValue());
	}
	else return new Multiplication(arg1, arg2);
    }
    
    @Override
    public SymbolicExpression visit(Negation n) {
	SymbolicExpression arg = n.getNode().accept(this);
	if (arg.isConstant()) {
	    return new Constant(arg.getValue() * (-1));
	} else {
	    return new Negation(arg);
	}
    }
  
    @Override
    public SymbolicExpression visit(Quit n) {
	throw new IllegalArgumentException("Commands may not be evaluated!");
    }
    
    
    @Override
    public SymbolicExpression visit(Sin n) {
	SymbolicExpression arg = n.getNode().accept(this);
	if (arg.isConstant()) {
	    return new Constant(Math.sin(arg.getValue()));
	} else {
	    return new Cos(arg);
	}
    }
    
    @Override
    public SymbolicExpression visit(Subtraction n) {
	SymbolicExpression arg1 = n.getLeft().accept(this);
	SymbolicExpression arg2 = n.getRight().accept(this);
	if (arg1.isConstant() && arg2.isConstant()) {
	    return new Constant(arg1.getValue() - arg2.getValue());
	}
	else return new Subtraction(arg1, arg2);
    }
    
    @Override
    public SymbolicExpression visit(Variable n)
    {

	if (NamedConstant.namedConstants.containsKey(n.toString()))
	    {
		return new Constant(NamedConstant.namedConstants.get(n.toString()));
	    }
	else{
	    Stack<Environment> tempStack = new Stack<Environment>();
	    Environment tempEnv = new Environment();
	    SymbolicExpression s = null;
	    
	    
	    while(!stack.empty()){
		Environment top = stack.pop();
		tempStack.push(top);
		if (top.containsKey(n)) {
		    tempEnv = top;
		    break;
		}
	    }

	    while(!tempStack.empty()){
		stack.push(tempStack.pop());
	    }

	    if(tempEnv.containsKey(n)) {
		return tempEnv.get(n).accept(this);
	    }
	    else{
		return new Variable(n.toString());
	    }
	}
    }
    
    @Override
    public SymbolicExpression visit(Vars n) {
	throw new IllegalArgumentException("Commands may not be evaluated");
    }
    
    @Override
    public SymbolicExpression visit(Clear n) {
	throw new IllegalArgumentException("Commands may not be evaluated");
    }
    @Override
    public SymbolicExpression visit(Scope n) {
	Environment temp = new Environment();
	stack.push(temp);
	
	SymbolicExpression arg = n.getArg().accept(this);
	stack.pop();
	return arg;
    }

    @Override
    public SymbolicExpression visit(FunctionDeclaration a){
	throw new IllegalArgumentException("Functional Declaration may not be evaulated");
    }


    @Override
    public SymbolicExpression visit(FunctionCall a){
	//Lägger in extra sequences

	FunctionCall functionCall = new FunctionCall(a.getFunc(), a.getArgumentValues());
	LinkedList<SymbolicExpression> arguments = functionCall.getArguments(); //Variables
	Queue<SymbolicExpression> argValues =  functionCall.getArgumentValues(); //Constants
       
	
	//System.out.println(argValues);
	Queue<SymbolicExpression> sequencesss = functionCall.getSequences();
	Queue<SymbolicExpression> tmp = new LinkedList<SymbolicExpression>();
	Environment tempor = stack.pop();	       
	int counterr = 0;
	while(!argValues.isEmpty()){;
	    SymbolicExpression valueToAssign = argValues.poll();
	    SymbolicExpression addedToSeq = null;

	    SymbolicExpression recurArg = valueToAssign.accept(this);
	    addedToSeq = new Assignment (recurArg, arguments.get(counterr));
	    tmp.add(addedToSeq);
	    counterr++;
	}
	stack.push(tempor);
	
	while(!sequencesss.isEmpty()){
	    tmp.add(sequencesss.poll());
	}
	functionCall.newSeq(tmp);
	a = functionCall;

	Environment temp = new Environment();
	stack.push(temp);

	SymbolicExpression arg = null;
	SymbolicExpression toEval = null;
	Queue<SymbolicExpression> sequences = a.getSequences();
	if(!sequences.isEmpty()){
	    while(!sequences.isEmpty()){
		toEval = sequences.poll();
		arg = toEval.accept(this);
	    }
	    stack.pop();
	    return arg;
	}
	else{
	    throw new IllegalArgumentException("No sequences in function");
	}
    }
    
    @Override
    public SymbolicExpression visit(Conditional n)
    {

	SymbolicExpression lhs = n.getLeft().accept(this);
	SymbolicExpression rhs = n.getRight().accept(this);
	String op = n.getOP();
	SymbolicExpression s1 = null;
	SymbolicExpression s2 = null;

	
	if (!(lhs.isConstant() && rhs.isConstant()))
	    throw new IllegalArgumentException("Operation " + op + " can only be applied to expressions that can be fully evaluated");
     
	if (op.equals("<")){
	    if (lhs.getValue() < rhs.getValue()){
		Environment temp = stack.pop();
		s1 = n.getS1().accept(this);
		stack.push(temp);
		return s1;
	    }
	    else {
		s2 = n.getS2().accept(this);
		return s2;
	    }
		    
	}
	if (op.equals(">")){
	    if (lhs.getValue() > rhs.getValue()){
		s1 = n.getS1().accept(this);
		return s1;
	    }
	    else {
		s2 = n.getS2().accept(this);
		return s2;
	    }
	}
	if (op.equals("<=")){
	    if (lhs.getValue() <= rhs.getValue()){
		s1 = n.getS1().accept(this);
		return s1;
	    }
	    else{
		s2 = n.getS2().accept(this);
		return s2;
	    }
	}
	if (op.equals(">=")){
	    if (lhs.getValue() >= rhs.getValue()){
		s1 = n.getS1().accept(this);
		return s1;
	    }
	    else {
		s2 = n.getS2().accept(this);
		return s2;
	    }
	}
	if (op.equals("==")){
	    if (lhs.getValue() == rhs.getValue()){
		s1 = n.getS1().accept(this);
		return s1;
	    }
	    else{
		s2 = n.getS2().accept(this);
		return s2;
	    }
	}

	return n;
	// if 1<2 {3} else {4}
    }
}
