package org.ioopm.calculator.ast;
import java.util.*;

/**
 * @file IllegalArgumentExpression.java
 * @author Erik Norén, Andreas Bleichner
 * @date 25 Nov 2019
 * @brief IllegalArgumentExpression is a class that is used to throw one specific type of exceptions
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment3.html for more info
 */

public class IllegalExceptionExpression extends RuntimeException {
    public IllegalExceptionExpression() {
        super();
    }
    public IllegalExceptionExpression(String msg) {
        super(msg);
    }
}
