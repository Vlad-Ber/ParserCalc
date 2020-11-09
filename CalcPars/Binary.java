package org.ioopm.calculator.ast;
import java.util.HashMap;

/**
 * @file Binary.java
 * @author Erik Norén, Andreas Bleichner
 * @date 25 Nov 2019
 * @brief Binary is a class that handles SymbolicExpression-class expressions that are mathematical additions, subtractions, multiplications or divisions 
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment3.html for more info
 */

public abstract class Binary extends SymbolicExpression {
    private SymbolicExpression lhs = null;
    private SymbolicExpression rhs = null;

    public Binary(SymbolicExpression a, SymbolicExpression b){
	this.lhs = a;
	this.rhs = b;
    }
    
    /**
     * @brief Fetches the left branch of the Binary class tree
     * @return The SymbolicExpression in the left branch of the Binary class tree
     */  
    public SymbolicExpression getLeft() {
	return this.lhs;
    }

    /**
     * @brief Fetches the right branch of a Binary class tree
     * @return The SymbolicExpression in the right branch of the Binary class tree
     */  
    public SymbolicExpression getRight() {
	return this.rhs;
    }

    @Override
    public String toString()
    {
	if (this.getPriority() > rhs.getPriority() && this.getPriority() > lhs.getPriority())
	    return "(" + this.lhs + ")" + this.getName() + "(" + this.rhs + ")";

        if (this.getPriority() > rhs.getPriority())
	    return this.lhs + this.getName() + "(" + this.rhs + ")";

	if (this.getPriority() > lhs.getPriority())
	    return "(" + this.lhs + ")" + this.getName() + this.rhs;

	return this.lhs + "" + this.getName() + this.rhs;
    }
	
}
