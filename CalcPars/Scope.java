package org.ioopm.calculator.ast;

public class Scope extends SymbolicExpression{

    private SymbolicExpression expression;
    
    public Scope(SymbolicExpression exp){
	this.expression = exp;
	this.setPriority(10);
    }
    
    
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }

    public SymbolicExpression getArg(){
	return this.expression;
    }


    public String toString(){
	return "{" + getArg().toString() + "}";
    }

    @Override
    public boolean isScope(){
	return true;
    }

   
    public SymbolicExpression eval(Environment env){
        throw new IllegalArgumentException("Error");
    }
}
