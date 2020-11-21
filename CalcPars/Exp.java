package org.ioopm.calculator.ast;
import java.util.HashMap;

/**
 * @file Exp.java
 * @author Erik Norén, Vladislav Bertilsson
 * @date 25 Nov 2019
 * @brief Exp is a class that handles Unary-class expressions that are mathematical exponential expressions
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment3.html for more info
 */

public class Exp extends Unary {

    public static final String NAME = "Exp";
    
    public Exp(SymbolicExpression value){
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
     * @brief Checks if an object is of class Exp and if so, checks if the Exp objects are equal
     * @return True if the Exp's are equal
     */  
    public boolean equals(Object other) {
	if (other instanceof Exp) {
	    return this.equals((Exp) other);
	} else {
	    return false;
	}
    }

    public boolean equals(Exp other) {
	/// access a private field of other!
	return (this.getNode().equals(other.getNode()));
	    
    }

    public SymbolicExpression eval(Environment vars) {
	SymbolicExpression arg = this.getNode().eval(vars);
	if (arg.isConstant()) {
	    return new Constant(Math.exp(arg.getValue()));
	} else {
	    return new Exp(arg);
	}
    }

}

