package org.ioopm.calculator.ast;

/**
 * @file Quit.java
 * @author Erik Norén, Andreas Bleichner
 * @date 25 Nov 2019
 * @brief Quit is class that handles Command-class expression Quit
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment3.html for more info
 */
public class Quit extends Command {
    private static final Quit theInstance = new Quit();
    
    private Quit() {}

    /**
     * @brief Returns an instance of a Quit object
     * @return A Quit object
     */ 
    public static Quit instance() {
	return theInstance;
    }

    @Override
    public boolean isCommand() {
	return true;
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }
    public SymbolicExpression eval(Environment vars) {
	throw new IllegalArgumentException("Commands may not be evaluated");
    }

}
