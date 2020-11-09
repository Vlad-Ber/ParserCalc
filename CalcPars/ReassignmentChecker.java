package org.ioopm.calculator.ast;
import java.util.*;
    


public class ReassignmentChecker implements Visitor {
    private Stack<LinkedList> stack = new Stack<LinkedList>();
	
    public boolean check(SymbolicExpression topLevel){
	try{
	    LinkedList<String> temp = new LinkedList<String>();
	    stack.push(temp);
	    topLevel.accept(this);
	}
	catch(IllegalArgumentException e){
	    System.out.println(e.getMessage());
	    return false;
	}
	return true;
    }

    @Override
    public SymbolicExpression visit(Addition a) {
        a.getLeft().accept(this);
        a.getRight().accept(this);
        return a; // No need to create a new tree
    }

    @Override
    // When we hit an assignment, make sure to check!
    public SymbolicExpression visit(Assignment a) {
        a.getLeft().accept(this);
	String identifier = a.getRight().toString();
	LinkedList<String> map = stack.peek();
	if(map.contains(identifier)){
	    throw new IllegalArgumentException("The variable " + identifier + " is reassigned");
	}
	else{
	    map.add(identifier);
	}
        return a;
    }
    @Override
    public SymbolicExpression visit(Constant a) {
        return a;
    }
    @Override
    public SymbolicExpression visit(Cos a) {
        a.getNode().accept(this);
        return a;
    }
    @Override
    public SymbolicExpression visit(Division a) {
	a.getLeft().accept(this);
        a.getRight().accept(this);
        return a;
    }
    @Override
    public SymbolicExpression visit(Exp a) {
	a.getNode().accept(this);
        return a;
    }
    @Override
    public SymbolicExpression visit(Log a) {
        a.getNode().accept(this);
        return a;
    }
    @Override
    public SymbolicExpression visit(Multiplication a) {
        a.getLeft().accept(this);
        a.getRight().accept(this);
        return a;
    }
    @Override
    public SymbolicExpression visit(Negation a) {
	a.getNode().accept(this);
        return a;
    }
    @Override
    public SymbolicExpression visit(Quit a) {
	throw new IllegalArgumentException("Commands not allowed in expression");
    }
    @Override
    public SymbolicExpression visit(Sin a) {
        a.getNode().accept(this);
        return a;
    }
    @Override
    public SymbolicExpression visit(Subtraction a) {
	a.getLeft().accept(this);
	a.getRight().accept(this);
        return a;
    }
    @Override
    public SymbolicExpression visit(Variable a) {
        return a;
    }
    @Override
    public SymbolicExpression visit(Vars a) {
        throw new IllegalArgumentException("Commands not allowed in expression");
    }
    @Override
    public SymbolicExpression visit(Clear a) {
	throw new IllegalArgumentException("Commands not allowed in expression");
    }

    @Override
    public SymbolicExpression visit(Scope a)
    {
	LinkedList<String> temp = new LinkedList<String>();
	stack.push(temp);	
	a.getArg().accept(this);
	stack.pop();
	return a;
    }

    @Override
    public SymbolicExpression visit(Conditional a)
    {
	a.getLeft().accept(this);
	a.getRight().accept(this);
	a.getS1().accept(this);
	a.getS2().accept(this);
	return a;
    }

    @Override
    public SymbolicExpression visit(FunctionCall a){
	LinkedList<String> temp = new LinkedList<String>();
	stack.push(temp);
	Queue<SymbolicExpression> sequences = a.getSequences();
	while(!sequences.isEmpty()){
	    Scope scopeEnv = new Scope(sequences.poll().accept(this));
	    scopeEnv.accept(this);
	}
	stack.pop();
	return a;
    }
    
    @Override
    public SymbolicExpression visit(FunctionDeclaration a){
	throw new IllegalArgumentException("Functional declaration cant be checked");
    }
}

