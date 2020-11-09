package org.ioopm.calculator.ast;
import java.util.*;

/**
 * @file SymbolicExpression.java
 * @author Erik Norén, Vladislav Bertilsson
 * @date 12 Dec 2019
 * @brief SymbolicExpression is the class that branches out to all other sub-classes in the program, and so every expression that the program handles can be viewed as a SymbolicExpression
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment3.html for more info
 */

public abstract class SymbolicExpression {
    private int priority = 11;

    /**
     * @brief Checks if a SymbolicExpression is a constant
     * @return True if the SymbolicExpression is a constant
     */  
    public boolean isConstant()
    {
	return false;
    }

    /**
     * @brief Checks if a SymbolicExpression is a named constant
     * @return True if the SymbolicExpression is a named constant
     */  
    public boolean isNamedConstant()
    {
	return false;
    }

    /**
     * @brief Checks if a SymbolicExpression is a conditional
     * @return True if the SymbolicExpression is a conditional
     */ 
    public boolean isConditional(){
	return false;
    }

    /**
     * @brief Checks if a SymbolicExpression is a assignment
     * @return True if the SymbolicExpression is a assignment
     */ 
    public boolean isAssignment(){
	return false;
    }
    
    public SymbolicExpression accept(Visitor v) {
	throw new IllegalArgumentException("calling accept on symbolicexpression");
    }

    /**
     * @brief Checks if a SymbolicExpression is a variable
     * @return True if the SymbolicExpression is a variable
     */  
    public boolean isVariable() {
	return false;
    }

    /**
     * @brief Checks if a SymbolicExpression is a command
     * @return True if the SymbolicExpression is a command
     */  
    public boolean isCommand() {
	return false;
    }

    /**
     * @brief Checks if a SymbolicExpression is a scope
     * @return True if the SymbolicExpression is a scope
     */ 
    public boolean isScope() {
	return false;
    }

    /**
     * @brief Makes a string out of an expression
     * @return A string of an expression
     */  
    public String getName()
    {
	throw new IllegalArgumentException("getName() called on expression with no operator");
    }
    
    /**
     * @brief Fetches the value of a SymbolicExpression, if it contains one
     * @return The value of a SymbolicExpression as a double
     */  
    public double getValue()
    {
	throw new IllegalArgumentException("getValue() called on expression with no value");
    }

    /**
     * @brief Evaluates a SymbolicExpression as much as possible
     * @return A SymbolicExpression
     * @example exp.eval(env) where exp = sin(x), x is unbound, and env = new Environment returns (Sin) sin(x) as it cannot be further evaluated
     * @example exp.eval(env) where exp = 2+2 and env = new Environment returns (constant) 4 as it cannot be further evalutaed
     * @throws IllegalArgumentException if attempting to evaluate something that cannot be evaluated
     */  
    public abstract SymbolicExpression eval(Environment vars);


    /**
     * @brief Fetches the priority of a SymbolicExpression
     * @return The priority of a Symbolicexpression as an int
     */  
    public int getPriority() {
	return this.priority;
    }

    /**
     * @brief Sets the priority attribute of a SymbolicExpression 
     */  
    public void setPriority(int priority) {
	this.priority = priority;
    }
}
