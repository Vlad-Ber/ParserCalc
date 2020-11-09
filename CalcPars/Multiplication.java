package org.ioopm.calculator.ast;
import java.util.HashMap;

/**
 * @file Multiplication.java
 * @author Erik Norén, Andreas Bleichner
 * @date 25 Nov 2019
 * @brief Multiplication is a class that handles Binary-class expressions that are mathematical multiplications
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment3.html for more info
 */

public class Multiplication extends Binary {
    
    public Multiplication(SymbolicExpression leftValue, SymbolicExpression rightValue){
	super(leftValue, rightValue);
	this.setPriority(9);
    }

    @Override
    public String getName()
    {
	return "*";	
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }
  
    /**
     * @brief Checks if an object is of class Multiplication and if so, checks if the Multiplication objects are equal
     * @return True if the Multiplications are equal
     */  
    public boolean equals(Object other) {
	if (other instanceof Multiplication) {
	    return this.equals((Multiplication) other);
	} else {
	    return false;
	}
    }

    public boolean equals(Multiplication other) {
	/// access a private field of other!
	return ((this.getLeft().equals(other.getLeft()) && (this.getRight().equals(other.getRight())))
		|| (this.getLeft().equals(other.getRight()) && this.getRight().equals(other.getLeft())));	    
    }

    public SymbolicExpression eval(Environment vars) {
	SymbolicExpression arg1 = this.getLeft().eval(vars);
	SymbolicExpression arg2 = this.getRight().eval(vars);
	if (arg1.isConstant() && arg2.isConstant()) {
	    return new Constant(arg1.getValue() * arg2.getValue());
	}
	else return new Multiplication(arg1, arg2);
    }
}
