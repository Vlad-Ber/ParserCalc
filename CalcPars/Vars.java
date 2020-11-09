package org.ioopm.calculator.ast;

/**
 * @file Vars.java
 * @author Erik Norén, Andreas Bleichner
 * @date 25 Nov 2019
 * @brief Vars is class that handles Command-class expression Vars
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment3.html for more info
 */
public class Vars extends Command {
    private static final Vars theInstance = new Vars();
    
    private Vars() {}

    /**
     * @brief Returns an instance of a Vars object
     * @return A Vars object
     */ 
    public static Vars instance() {
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
