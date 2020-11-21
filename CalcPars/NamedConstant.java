package org.ioopm.calculator.ast;
import java.util.*;

/**
 * @file Multiplication.java
 * @author Erik Norén, Vladislav Bertilsson
 * @date 25 Nov 2019
 * @brief NamedConstant is a class that handles variables that are bound and pre-defined and therefore cannot be redefined 
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment3.html for more info
 */

public class NamedConstant{
    public static final HashMap<String, Double> namedConstants = new HashMap<>();

    static {
	NamedConstant.namedConstants.put("pi", Math.PI);
	NamedConstant.namedConstants.put("e",  Math.E);
	NamedConstant.namedConstants.put("Answer",  42.0);
	NamedConstant.namedConstants.put("L",  6.022140857e23);
    }

    /**
     * @brief Checks if a HashMap contains a specified key
     * @return True if the HashMap contains the specified key
     */  
    public boolean isNamedConstant(String s) {
	return namedConstants.containsKey(s);
    }

    /**
     * @brief Checks if a HashMap contains a specified key and if so fetches the associated value
     * @return The value associated to specified key as a double
     * @throws IllegalArgumentexception if you attempt to bind a value to one of the pre-defined variables
     */  
    public double getNamedConstant (String s) {
	if (isNamedConstant(s)) {	    
	    return namedConstants.get(s);
	}
	else {
	    throw new IllegalArgumentException("Invalid name!");
	}
    }
}
