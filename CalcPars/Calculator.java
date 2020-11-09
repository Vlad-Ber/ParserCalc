package org.ioopm.calculator;

import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.*;
import java.io.*;
import java.util.*;

/**
 * @file Calculator.java
 * @author Erik Norén, Vladislav Bertilsson
 * @date 13 Dec 2019
 * @brief This class contains the main method for our Calculator. The calculator is
 * started then the user is allowed to use the terminal to input expressions. If these
 * expressions are correctly expressed they are evaluated, otherwise exceptions are 
 * thrown. Calculator supports functions and conditionals.
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment4.html
 */

public class Calculator
{
    //private final static Scanner scanner = new Scanner(System.in);
    private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final static Environment env = new Environment();
    private static HashMap<String, FunctionDeclaration> functions= new HashMap<String, FunctionDeclaration>();
    
    public static void main(String[] args)
    {
	double nrOfGoodEvals = 0;
	double nrOfEvals = 0;
	boolean shouldCont = true;

	System.out.println("Calculator started! Enter an expression:");
	
	while (shouldCont) {
	    final CalculatorParser parser = new CalculatorParser();
	    final NamedConstantChecker checker = new NamedConstantChecker();
	    final ReassignmentChecker reAssignChecker = new ReassignmentChecker();
	    final EvaluationVisitor evaluator = new EvaluationVisitor();
	    try {
		String input = br.readLine();
		SymbolicExpression exp = parser.parse(input, functions);
		if (exp.isCommand())
		    {
			if (exp instanceof Vars)
			    {
				System.out.println(env);
			    }
			else if(exp instanceof Clear)
			    {
				env.clear();
				System.out.println("Variable list has been cleared");
			    }
			else if(exp instanceof FunctionDeclaration)
			    {
				FunctionDeclaration func = (FunctionDeclaration) exp;
				String ide = func.getIdentifier();
				if(functions.containsKey(ide)){
				    functions.replace(ide, func);
				}
				else{
				    functions.put(ide, func);
				}
				System.out.println("Amount of functions currently stored: " + functions.size());
			    }
			else if(exp instanceof Quit)
			    {

				System.out.println("# of expressions entered: " + (int) nrOfEvals + "\n" + "# of successfully evaluated expressions: " + (int) nrOfGoodEvals + "\n" + (nrOfGoodEvals/nrOfEvals)*100.0 + "% of expressions were successfully evaluated" + "\n" + "You've exited the Calculator");
				shouldCont = false;
			    }
		    }
		else
		    {
			if (checker.check(exp) && reAssignChecker.check(exp))
			    {
				SymbolicExpression finalSymbolicExpression = evaluator.evaluate(exp,env);
				System.out.println(finalSymbolicExpression);
				if(finalSymbolicExpression.isConstant()){nrOfGoodEvals++;}
				else if (exp.isVariable() && env.containsKey(exp)) {nrOfGoodEvals++;}
				nrOfEvals++;
			    }
		    }		
	    }
	    catch(Exception e) {
	    	System.out.println(e.getMessage());
	    }
	}
    }
}
