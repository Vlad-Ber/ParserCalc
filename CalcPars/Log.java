package org.ioopm.calculator.ast;
import java.util.HashMap;

/**
 * @file Log.java
 * @author Erik Norén, Andreas Bleichner
 * @date 25 Nov 2019
 * @brief is a class that handles Unary-class expressions that are mathematical logarithmic expressions with base ten
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment3.html for more info
 */

public class Log extends Unary{

    public static final String NAME = "Log";

    public Log(SymbolicExpression value){
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
     * @brief Checks if an object is of class Log and if so, checks if the Log objects are equal
     * @return True if the Logs are equal
     */  
    public boolean equals(Object other) {
	if (other instanceof Log) {
	    return this.equals((Log) other);
	} else {
	    return false;
	}
    }

    public boolean equals(Log other) {
	/// access a private field of other!
	return (this.getNode().equals(other.getNode()));
	    
    }

        public SymbolicExpression eval(Environment vars) {
	    SymbolicExpression arg = this.getNode().eval(vars);
	if (arg.isConstant()) {
	    return new Constant(Math.log(arg.getValue()));
	} else {
	    return new Log(arg);
	}
    }
}
