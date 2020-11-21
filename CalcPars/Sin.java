package org.ioopm.calculator.ast;
import java.util.HashMap;

/**
 * @file Sin.java
 * @author Erik Norén, Vladislav Bertilsson
 * @date 25 Nov 2019
 * @brief Sin is a class that handles Unary-class expressions that are mathematical sine expressions
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment3.html for more info
 */

public class Sin extends Unary {

    public static final String NAME = "Sin";

    public Sin(SymbolicExpression value){
	super(value);
	this.setPriority(10);
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }

    @Override
    public String getName()
    {
	return NAME;
    }

    /**
     * @brief Checks if an object is of class Sin and if so, checks if the Sin objects are equal
     * @return True if the Sins are equal
     */  
    public boolean equals(Object other) {
	if (other instanceof Sin) {
	    return this.equals((Sin) other);
	} else {
	    return false;
	}
    }

    public boolean equals(Sin other) {
	/// access a private field of other!
	return (this.getNode().equals(other.getNode()));
	    
    }

    public SymbolicExpression eval(Environment vars) {
	SymbolicExpression arg = this.getNode().eval(vars);
	if (arg.isConstant()) {
	    return new Constant(Math.sin(arg.getValue()));
	} else {
	    return new Sin(arg);
	}
    }
}
