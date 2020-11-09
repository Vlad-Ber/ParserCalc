package org.ioopm.calculator.ast;
import java.util.*;

/**
 * @file Assignment.java
 * @author Erik Norén, Andreas Bleichner
 * @date 25 Nov 2019
 * @brief Assignment is a class that handles Binary-class expressions that are mathematical assignments
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment3.html for more info
 */

public class Assignment extends Binary{
    private final NamedConstant constants = new NamedConstant();
    
    public Assignment(SymbolicExpression leftValue, SymbolicExpression rightValue){
	super(leftValue, rightValue);
	this.setPriority(3);
    }

    @Override
    public String getName()
    {
	return "=";
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }

    @Override
    public boolean isAssignment(){
	return true;
    }

    public boolean equals(Object other) {
	if (other instanceof Assignment) {
	    return this.equals((Assignment) other);
	} else {
	    return false;
	}
    }
    
    /// @brief Checks whether a given Assignment is equal to a another Assignment
    /// @param The Assignment which to compare equality to
    /// @return True if they are equal, otherwise false.
    public boolean equals(Assignment other) {
	/// access a private field of other!
	SymbolicExpression left1 = getLeft();
	SymbolicExpression right1 = getRight();
	SymbolicExpression left2 = other.getLeft();
	SymbolicExpression right2 = other.getRight();
	return left1.equals(left2) && right1.equals(right2);
    }

    public NamedConstant getNamed(){
	return this.constants;
    }
	
    public SymbolicExpression eval(Environment vars) {
	
	SymbolicExpression expression = this.getLeft().eval(vars);
	SymbolicExpression key = this.getRight();
	if(constants.isNamedConstant(key.toString())) {
	    throw new IllegalArgumentException("Variable cannot be a named constant!");
		}
	    
	if (!key.isVariable()) throw new IllegalArgumentException("Variable not valid!");
	else {	    
	    vars.put((Variable) key, expression);
	}			    
	if (expression.isConstant()) return new Constant(expression.getValue());
	return expression;
    }
}
