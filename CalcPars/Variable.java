package org.ioopm.calculator.ast;
import java.util.HashMap;
import java.lang.Comparable;

/**
 * @file Variable.java
 * @author Erik Norén,Vladislav Bertilsson
 * @date 25 Nov 2019
 * @brief Variable is class that handles the Atom-class expressions that are expressed as variables 
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment3.html for more info
 */
public class Variable extends Atom implements Comparable<Variable> {
    private String identifier;

    public Variable(String identifier) {
	if (identifier == "vars" || identifier == "quit")
	    {
		throw new IllegalArgumentException("identifier is not valid");
	    }
	this.identifier = identifier;
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }

    @Override
    public String toString()
    {
	return identifier;
    }

    @Override
    public boolean isNamedConstant()
    {
	if (NamedConstant.namedConstants.containsKey(this.identifier))
	    return true;
	else
	    return false;
    }
    
    @Override
    public boolean isVariable() {
	return true;
    }

    @Override
    /**
     * @brief Generates a hashcode for the String name of a Variable
     * @return A hashcode
     */ 
    public int hashCode() {
	return this.identifier.hashCode();
    }

    /**
     * @brief Checks if an object is of class Variable, and if so checks if the Variable objects are equal
     * @return True if they are equal
     */ 
    public boolean equals(Object other) {
	if (other instanceof Variable) {
	    return this.equals((Variable) other);
	} else {
	    return false;
	}
    }

    public boolean equals(Variable other) {
	return this.identifier.equals(other.identifier);
    }
    
    public SymbolicExpression eval(Environment vars) {
	
	if (vars.containsKey(this)) {
	    return vars.get(this);
	}
	return this;
    }

    @Override
    public int compareTo(Variable other)
    {
	return this.identifier.compareTo(other.identifier);
	
    }
}
