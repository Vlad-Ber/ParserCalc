package org.ioopm.calculator.ast;

/**
 * @file Addition.java
 * @author Erik Norén, Andreas Bleichner
 * @date 25 Nov 2019
 * @brief Addition is a class that handles Binary-class expressions that are mathematical additions
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment3.html for more info
 */

public class Addition extends Binary
{
    
    public Addition(SymbolicExpression leftValue, SymbolicExpression rightValue)
    {
	super(leftValue, rightValue);
	this.setPriority(6);
    }

    @Override
    public String getName()
    {
	return "+";
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }
    
    /**
     * @brief Checks if an object is of class Addition and if so, checks if the Addition objects are equal
     * @return True if the Additions are equal
     */  
    public boolean equals(Object other) {
	if (other instanceof Addition) {
	    return this.equals((Addition) other);
	} else {
	    return false;
	}
    }

    public boolean equals(Addition other) {
	/// access a private field of other!
	return ((this.getLeft().equals(other.getLeft()) && (this.getRight().equals(other.getRight())))
	       || (this.getLeft().equals(other.getRight()) && this.getRight().equals(other.getLeft())));
	 
    }

    public SymbolicExpression eval(Environment vars) {
	SymbolicExpression arg1 = this.getLeft().eval(vars);
	SymbolicExpression arg2 = this.getRight().eval(vars);
	if (arg1.isConstant() && arg2.isConstant()) {
	    return new Constant(arg1.getValue() + arg2.getValue());
	}
	else return new Addition(arg1, arg2);
    }
}

