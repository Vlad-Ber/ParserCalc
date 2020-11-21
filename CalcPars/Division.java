package org.ioopm.calculator.ast;
import java.util.HashMap;

/**
 * @file Division.java
 * @author Erik Norén, Vladislav Bertilsson
 * @date 25 Nov 2019
 * @brief Division is a class that handles Binary-class expressions that are mathematical divisions.
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment3.html
 */

public class Division extends Binary{
    
    public Division(SymbolicExpression leftValue, SymbolicExpression rightValue){
	super(leftValue, rightValue);
	this.setPriority(9);
    }

    @Override
    public String getName()
    {
	return "/";
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }
  
    /**
     * @brief Checks if an object is of class Division and if so, checks if the Division objects are equal
     * @return True if the Divisions are equal
     */  
    public boolean equals(Object other) {
	if (other instanceof Division) {
	    return this.equals((Division) other);
	} else {
	    return false;
	}
    }

    public boolean equals(Division other) {
	return (this.getLeft().equals(other.getLeft()) && (this.getRight().equals(other.getRight())));
    }
    
    public SymbolicExpression eval(Environment vars) {
	SymbolicExpression arg1 = this.getLeft().eval(vars);
	SymbolicExpression arg2 = this.getRight().eval(vars);
	if (arg1.isConstant() && arg2.isConstant()) {
	    return new Constant(arg1.getValue() / arg2.getValue());
	}
	else return new Division(arg1, arg2);
    }
}
