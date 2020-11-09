package org.ioopm.calculator.ast;

public class VariableAssignedChecker implements Visitor {

    private Environment env;
    
    public boolean check(SymbolicExpression exp, Environment environment)
    {
	this.env = environment;
	try {exp.accept(this);}
	catch(IllegalArgumentException e)
	    {
		System.out.println(e.getMessage());
		return false;
	    }
	return true;
    }

    // Recurse down the AST tree
    @Override
    public SymbolicExpression visit(Addition a) {
	a.getLeft().accept(this);
	a.getRight().accept(this);
	return a; // No need to create a new tree
    }

    // When we hit an assignment, make sure to check!
    @Override
    public SymbolicExpression visit(Assignment a) {
	a.getLeft().accept(this);
	a.getRight().accept(this);
	return a;
    }

    @Override
    public SymbolicExpression visit(Constant a) {
	return a;
    }

    @Override
    public SymbolicExpression visit(Cos a)
    {
	a.getNode().accept(this);
	return a;
    }

    @Override
    public SymbolicExpression visit(Division a)
    {
	a.getLeft().accept(this);
	a.getRight().accept(this);
	return a;
    }

    @Override
    public SymbolicExpression visit(Exp a)
    {
	a.getNode().accept(this);
	return a;
    }

    @Override
    public SymbolicExpression visit(Log a)
    {
	a.getNode().accept(this);
	return a;
    }

    @Override
    public SymbolicExpression visit(Multiplication a)
    {
	a.getLeft().accept(this);
	a.getRight().accept(this);
	return a;
    }

    @Override
    public SymbolicExpression visit(Negation a)
    {
	a.getNode().accept(this);
	return a;
    }

    @Override
    public SymbolicExpression visit(Quit a)
    {
	throw new IllegalArgumentException("Commands cannot be checked");
    }

    @Override
    public SymbolicExpression visit(Sin a)
    {
	a.getNode().accept(this);
	return a;
    }

    @Override
    public SymbolicExpression visit(Subtraction a)
    {
	a.getLeft().accept(this);
	a.getRight().accept(this);
	return a;
    }

    @Override
    public SymbolicExpression visit(Variable a)
    {
	if(!this.env.containsKey(a)){
	    throw new IllegalArgumentException("Unassigned variables in condition statements");
	}
	return a;
    }

    @Override
    public SymbolicExpression visit(Vars a)
    {
	throw new IllegalArgumentException("Commands cannot be checked");
    }

    @Override
    public SymbolicExpression visit(Clear a)
    {
	throw new IllegalArgumentException("Commands cannot be checked");
    }

    @Override
    public SymbolicExpression visit(Scope a)
    {
	a.getArg().accept(this);
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
	return a; //???
    }

    
    @Override
    public SymbolicExpression visit(FunctionDeclaration a){
	throw new IllegalArgumentException("Functional declaration can not be checked");
    }
}
