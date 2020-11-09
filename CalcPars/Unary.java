package org.ioopm.calculator.ast;

/**
 * @file Unary.java
 * @author Erik Norén, Andreas Bleichner
 * @date 25 Nov 2019
 * @brief Binary is a class that handles SymbolicExpression-class expressions that are mathematical sine, cosine, exponential, logarithmic or negation expressions 
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment3.html for more info
 */

public abstract class Unary extends SymbolicExpression {
    private SymbolicExpression node = null;
    
    public Unary(SymbolicExpression value){
	this.node = value;
    }

    /**
     * @brief Fetches the node of a Unary class tree
     * @return The SymbolicExpression in the node of the Unary class tree
     */  
    public SymbolicExpression getNode() {
	return this.node;
    }

    @Override
    public String toString() {
	/// Note how the call to toString() is not necessary
	return this.getName() + "(" + this.node.toString() + ")";
    }

}
