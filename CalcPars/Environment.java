package org.ioopm.calculator.ast;

import java.util.HashMap;
import java.util.TreeSet;
import java.util.Iterator;
/**
 * @file Environment.java
 * @author Erik Norén, Andreas Bleichner
 * @date 25 Nov 2019
 * @brief Environment is a class that is used to store pre-defined variables in the calculator
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment3.html for more info
 **/

public class Environment extends HashMap<Variable, SymbolicExpression> {
    
    @Override
    public String toString()
    {
	StringBuilder sb = new StringBuilder();
	TreeSet<Variable> vars = new TreeSet<>(this.keySet());

	sb.append("{");
	for (Iterator<Variable> iter = vars.iterator(); iter.hasNext(); )
	    {
		Variable v = iter.next();
		sb.append(v.toString());
		sb.append("=");
		sb.append(this.get(v));
		if (iter.hasNext()) {sb.append(", ");}
	    }
	sb.append("}");
	return sb.toString();
    }

}
