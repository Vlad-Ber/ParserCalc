package org.ioopm.calculator.ast;
import java.util.HashMap;

/**
 * @file Subtraction.java
 * @author Erik Norén, Andreas Bleichner
 * @date 25 Nov 2019
 * @brief Subtraction is a class that handles the Binary-class expressions that are mathematical subtractions.
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment3.html for more info
 */

public class Subtraction extends Binary {

    public Subtraction(SymbolicExpression leftValue, SymbolicExpression rightValue){
        super(leftValue, rightValue);
	this.setPriority(6);
    }

    @Override
    public String getName()
    {
	return "-";
    }

    @Override
     public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }
    
    
    /**
     * @brief Checks if an object is of class Subtraction and if so, checks if the Subtraction objects are equal
     * @return True if the Subtractions are equal
     */  
    public boolean equals(Object other) {
	if (other instanceof Subtraction) {
	    return this.equals((Subtraction) other);
	} else {
	    return false;
	}
    }

    public boolean equals(Subtraction other) {
	/// access a private field of other!
	return (this.getLeft().equals(other.getLeft()) && this.getRight().equals(other.getRight()));
    }

        public SymbolicExpression eval(Environment vars) {
	SymbolicExpression arg1 = this.getLeft().eval(vars);
	SymbolicExpression arg2 = this.getRight().eval(vars);
	if (arg1.isConstant() && arg2.isConstant()) {
	    return new Constant(arg1.getValue() - arg2.getValue());
	}
	else return new Subtraction(arg1, arg2);
    }
}
