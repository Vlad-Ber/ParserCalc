package org.ioopm.calculator.ast;
import java.util.HashMap;

/**
 * @file Constant.java
 * @author Erik Norén, Andreas Bleichner
 * @date 25 Nov 2019
 * @brief Constant is a class that handles the Atom-class expressions that are expressed as constants (number values)
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment3.html for more info
 */

public class Constant extends Atom {
    private double value;


    public Constant(double value) {
	this.value = value;
    }

    @Override
    public boolean isConstant(){
	return true;
    }
    
    public double getValue()
    {
	return value;
    }

    @Override
    public String toString() {
	return String.valueOf(this.value);
    }
     /**
     * @brief Checks if an object is of class Constant, and if so checks if the Constant objects are equal
     * @return True if they are equal
     */ 
    public boolean equals(Object other) {
	if (other instanceof Constant) {
	    return this.equals((Constant) other);
	} else {
	    return false;
	}
    }

    public boolean equals(Constant other) {
	return this.value == other.getValue();
    }

    public Constant eval(Environment vars) {
	return new Constant (this.getValue());
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }

}

