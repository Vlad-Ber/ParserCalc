package org.ioopm.calculator.ast;
import java.util.HashMap;

/**
 * @file Calculator.java
 * @author Erik Norén, Vladislav Bertilsson
 * @date 25 Nov 2019
 * @brief Cos is a class that handles Unary-class expressions that are mathematical cosine expressions
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment3.html for more info
 */

public class Cos extends Unary {

    public static final String NAME = "Cos";
    
    public Cos(SymbolicExpression value){
	super(value);
	this.setPriority(10);
    }

    @Override
    public String getName()
    {
	return NAME;
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }

    /**
     * @brief Checks if an object is of class Cos and if so, checks if the Cos objects are equal
     * @return True if the Cosines are equal
     */  
    public boolean equals(Object other) {
	if (other instanceof Cos) {
	    return this.equals((Cos) other);
	} else {
	    return false;
	}
    }

    public boolean equals(Cos other) {
	/// access a private field of other!
	return (this.getNode().equals(other.getNode()));
	    
    }
    
    public SymbolicExpression eval(Environment vars) {
	SymbolicExpression arg = this.getNode().eval(vars);
	if (arg.isConstant()) {
	    return new Constant(Math.cos(arg.getValue()));
	} else {
	    return new Cos(arg);
	}
    }
}


