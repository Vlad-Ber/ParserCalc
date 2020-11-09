package org.ioopm.calculator.ast;

/**
 * @file Clear.java
 * @author Erik Norén, Andreas Bleichner
 * @date 25 Nov 2019
 * @brief Clear is class that handles Command-class expression Clear
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment3.html for more info
 */
public class Clear extends Command {
    private static final Clear theInstance = new Clear();
    
    private Clear() {}

    /**
     * @brief Returns an instance of a Clear object
     * @return A Clear object
     */ 
    public static Clear instance() {
	return theInstance;
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }

    @Override
    public boolean isCommand() {
	return true;
    }

    public SymbolicExpression eval(Environment vars) {
	throw new IllegalArgumentException("Commands may not be evaluated");
    }
}
