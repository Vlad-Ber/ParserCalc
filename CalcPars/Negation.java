package org.ioopm.calculator.ast;
import java.util.HashMap;

/**
 * @file Negation.java
 * @author Erik Norén, Andreas Bleichner
 * @date 25 Nov 2019
 * @brief Negation is a class that handles Unary-class expressions that negates a mathematical expression
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment3.html for more info
 */

public class Negation extends Unary {

    public Negation(SymbolicExpression valueToNegate){
	super(valueToNegate);
	this.setPriority(10);
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
     * @brief Checks if an object is of class Negation and if so, checks if the Negation objects are equal
     * @return True if the Negations are equal
     */  

    public boolean equals(Object other) {
	if (other instanceof Negation) {
	    return this.equals((Negation) other);
	} else {
	    return false;
	}
    }

    public boolean equals(Negation other) {
	/// access a private field of other!
	return (this.getNode().equals(other.getNode()));
	    
    }

    public SymbolicExpression eval(Environment vars) {
	SymbolicExpression arg = this.getNode().eval(vars);
	if (arg.isConstant()) {
	    return new Constant(arg.getValue() * (-1));
	} else {
	    return new Negation(arg);
	}
    }
}
