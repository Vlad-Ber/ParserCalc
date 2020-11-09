package org.ioopm.calculator.ast;

public class Conditional extends SymbolicExpression
{
    private SymbolicExpression lhs;
    private SymbolicExpression rhs;
    private String op;
    private Scope s1;
    private Scope s2;
       
    
    public Conditional(SymbolicExpression lhs, SymbolicExpression rhs, String op, SymbolicExpression s1, SymbolicExpression s2)
    {
	this.lhs = lhs;
	this.rhs = rhs;
	this.op = op;
	this.s1 = (Scope) s1;
	this.s2 = (Scope) s2;
    }

    @Override
    public String toString(){
	return "if " + lhs + op + rhs + " " + s1 +" " + "else "+ s2;
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }

    @Override
    public boolean isConditional(){
	return true;
    }
    /**
     * @return A SymbolicExpression the lhs of the conditional expression
     */
    public SymbolicExpression getLeft(){
	return this.lhs;
    }

    /**
     * @return A SymbolicExpression the rhs of the conditional expression
     */
    public SymbolicExpression getRight(){
	return this.rhs;
    }

    /**
     * @return A String representing the operation of the conditional expression
     */
    public String getOP(){
	return this.op;
    }

    /**
     * @return A Scope representing the if-scope
     */
    public Scope getS1(){
	return this.s1;
    }

    /**
     * @return A Scope representing the else-scope
     */
    public Scope getS2(){
	return this.s2;
    }
    
    public SymbolicExpression eval(Environment env){return new Constant(0);}
}
