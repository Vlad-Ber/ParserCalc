package org.ioopm.calculator.parser;
import java.util.*;

/**
 * @file SyntaxErrorException.java
 * @author Erik Norén, Vladislav Bertilsson
 * @date 25 Nov 2019
 * @brief SyntaxErrorException is a class that is used to define one specific type of exceptions
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment3.html for more info
 */

public class SyntaxErrorException extends RuntimeException {
    public SyntaxErrorException() {
        super();
    }
    public SyntaxErrorException(String msg) {
        super(msg);
    }
}
