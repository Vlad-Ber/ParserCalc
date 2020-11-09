package org.ioopm.calculator.testers;

import org.ioopm.calculator.ast.*;
import org.junit.Test;
import org.junit.Before;
import java.util.*;

import junit.framework.TestCase;
import static org.junit.Assert.*;

public class TestIntegration extends TestCase
{

    @Test
    public void testConditionals(){
	Environment env = new Environment();
	final EvaluationVisitor evaluator = new EvaluationVisitor();

	SymbolicExpression scope1 = new Scope(new Constant(42));
	SymbolicExpression scope2 = new Scope(new Constant(11));
	SymbolicExpression scope4 = new Scope(new Constant(75));

	SymbolicExpression c1 = new Constant(2);
	SymbolicExpression c2 = new Negation(c1);
	SymbolicExpression c3 = new Constant(3);
	SymbolicExpression addition1 = new Addition(c1,c3); //5
	SymbolicExpression subtraction1 = new Subtraction(c1, c2); //4
	SymbolicExpression addition2 = new Addition(c1, c1); //4 
	String op1 = "==";
	String op2 = "<";
	String op3 = ">";
	String op4 = "<=";
	String op5 = ">=";

	SymbolicExpression conditional1 = new Conditional(addition1, subtraction1, op1, scope1, scope2);
	SymbolicExpression conditional2 = new Conditional(addition1, subtraction1, op2, scope1, scope4);
	SymbolicExpression conditional3 = new Conditional(addition1, subtraction1, op1, scope1, scope2);
	SymbolicExpression scope3 = new Scope(conditional3);
	SymbolicExpression conditional4 = new Conditional(addition1, subtraction1, op4, scope1, scope3);
	SymbolicExpression conditional5 = new Conditional(subtraction1, addition2, op5, scope1, scope3);
	
	assertEquals(conditional1.toString(), "if 2.0+3.0==2.0--(2.0) {42.0} else {11.0}");
	assertEquals(conditional2.toString(), "if 2.0+3.0<2.0--(2.0) {42.0} else {75.0}");
	assertEquals(conditional4.toString(), "if 2.0+3.0<=2.0--(2.0) {42.0} else {if 2.0+3.0==2.0--(2.0) {42.0} else {11.0}}");
	
	assertEquals(evaluator.evaluate(conditional1, env).toString(), "11.0");
	assertEquals(evaluator.evaluate(conditional2, env).toString(), "75.0");
	assertEquals(evaluator.evaluate(conditional3, env).toString(), "11.0");
	assertEquals(evaluator.evaluate(conditional4, env).toString(), "11.0");
	assertEquals(evaluator.evaluate(conditional5, env).toString(), "42.0");
    }

	
    
    @Test
    public void testIntegrationAddition(){
	Environment env = new Environment();
	final EvaluationVisitor evaluator = new EvaluationVisitor();
       
	SymbolicExpression c1 = new Constant(1);
	SymbolicExpression c2 = new Constant(2);
	SymbolicExpression c3 = new Constant(10);
	SymbolicExpression c4 = new Constant(5);
	SymbolicExpression log1 = new Log(c1);
	SymbolicExpression neg1 = new Negation(c1);
	SymbolicExpression subtraction1 = new Subtraction(c4, c4);
	SymbolicExpression addition0 = new Addition (c1, c2);
	Multiplication multiplication1 = new Multiplication(c1, log1);
	Addition addition1 = new Addition(subtraction1, c2);
	Addition addition2 = new Addition(addition1, addition0);
	Addition addition3 = new Addition(c1, c2);
        Addition addition4 = new Addition(multiplication1, subtraction1);
	Addition addition5 = new Addition(addition2, c2);
	Addition addition6 = new Addition(addition1, c2);
        Addition addition7 = new Addition(c2, addition1);
        Addition addition8 = new Addition(addition1, c2);
	Addition addition9 = new Addition(c1, neg1);
	Addition addition10 = new Addition(c1, addition9);
	Addition addition11 = new Addition(neg1, neg1);
 
	//toString()
	assertEquals(addition1.toString(), "5.0-5.0+2.0");
	assertEquals(addition2.toString(), "5.0-5.0+2.0+1.0+2.0");
	assertEquals(addition3.toString(), "1.0+2.0");
	assertEquals(addition4.toString(), "1.0*Log(1.0)+5.0-5.0");
	assertEquals(addition5.toString(), "5.0-5.0+2.0+1.0+2.0+2.0");
	assertEquals(addition6.toString(), "5.0-5.0+2.0+2.0");
	assertEquals(addition9.toString(), "1.0+-(1.0)");
	assertEquals(addition10.toString(), "1.0+1.0+-(1.0)");

	//getLeft() och getRight()
	assertEquals(addition2.getLeft(), addition6.getLeft());
	assertEquals(addition6.getRight(), addition5.getRight());

        //Equals
	assertTrue(addition6.equals(addition7));
	assertTrue(addition6.equals(addition8));
	assertFalse(addition6.equals(addition1));
	assertFalse(addition5.equals(addition1));
	
	//Eval
	assertEquals(addition1.eval(env).toString(), "2.0");
	assertEquals(addition2.eval(env).toString(), "5.0");
	assertEquals(addition3.eval(env).toString(), "3.0");
	assertEquals(addition4.eval(env).toString(), "0.0");
	assertEquals(addition5.eval(env).toString(), "7.0");
	assertEquals(addition9.eval(env).toString(), "0.0");
	assertEquals(addition10.eval(env).toString(), "1.0");
	assertEquals(addition11.eval(env).toString(), "-2.0");
	assertEquals(evaluator.evaluate(addition11, env).toString(), "-2.0");
 
    }

    @Test
    public void testIntegrationSubtraction(){
	Environment env = new Environment();

	final EvaluationVisitor evaluator = new EvaluationVisitor();
	SymbolicExpression c1 = new Constant(1);
	SymbolicExpression c2 = new Constant(2);
	SymbolicExpression c3 = new Constant(10);
	SymbolicExpression c4 = new Constant(5);
	SymbolicExpression log1 = new Log(c1);
	SymbolicExpression neg1 = new Negation(c1);
	SymbolicExpression addition1 = new Addition(c4, c4);
	SymbolicExpression subtraction0 = new Subtraction (c1, c2);
	Multiplication multiplication1 = new Multiplication(c1, log1);
	Subtraction subtraction1 = new Subtraction(addition1, c2);
	Subtraction subtraction2 = new Subtraction(addition1, subtraction0);
	Subtraction subtraction3 = new Subtraction(c1, c2);
        Subtraction subtraction4 = new Subtraction(multiplication1, addition1);
	Subtraction subtraction5 = new Subtraction(subtraction2, c2);
	Subtraction subtraction6 = new Subtraction(subtraction1, c2);
        Subtraction subtraction7 = new Subtraction(c2, subtraction1);
        Subtraction subtraction8 = new Subtraction(subtraction1, c2);
	Subtraction subtraction9 = new Subtraction(c1, neg1);
	Subtraction subtraction10 = new Subtraction(c1, subtraction9);
	Subtraction subtraction11 = new Subtraction(neg1, neg1);
 
	//toString()
	assertEquals(subtraction1.toString(), "5.0+5.0-2.0");
	assertEquals(subtraction2.toString(), "5.0+5.0-1.0-2.0");
	assertEquals(subtraction3.toString(), "1.0-2.0");
	assertEquals(subtraction4.toString(), "1.0*Log(1.0)-5.0+5.0");
	assertEquals(subtraction5.toString(), "5.0+5.0-1.0-2.0-2.0");
	assertEquals(subtraction6.toString(), "5.0+5.0-2.0-2.0");
	assertEquals(subtraction9.toString(), "1.0--(1.0)");
	assertEquals(subtraction10.toString(), "1.0-1.0--(1.0)");

	//getLeft() och getRight()
	assertEquals(subtraction1.getLeft(), subtraction2.getLeft());
	assertEquals(subtraction6.getRight(), subtraction5.getRight());

        //Equals
	assertFalse(subtraction6.equals(subtraction7));
	assertTrue(subtraction6.equals(subtraction8));
	assertFalse(subtraction6.equals(subtraction1));
	assertFalse(subtraction5.equals(subtraction1));
	
	//Eval
	assertEquals(subtraction1.eval(env).toString(), "8.0");
	assertEquals(subtraction2.eval(env).toString(), "11.0");
	assertEquals(subtraction3.eval(env).toString(), "-1.0");
	assertEquals(subtraction4.eval(env).toString(), "-10.0");
	assertEquals(subtraction5.eval(env).toString(), "9.0");
	assertEquals(subtraction9.eval(env).toString(), "2.0");
	assertEquals(subtraction10.eval(env).toString(), "-1.0");
	assertEquals(subtraction11.eval(env).toString(), "0.0");
	assertEquals(evaluator.evaluate(subtraction11, env).toString(), "0.0");
    }

    @Test
    public void testIntegrationMultiplication(){

	Environment env = new Environment();
	final EvaluationVisitor evaluator = new EvaluationVisitor();
	
	SymbolicExpression c1 = new Constant(1);
	SymbolicExpression c2 = new Constant(2);
	SymbolicExpression c3 = new Constant(10);
	SymbolicExpression c4 = new Constant(5);
	SymbolicExpression log1 = new Log(c1);
	SymbolicExpression addition1 = new Addition(c4, c4);
	SymbolicExpression multiplication0 = new Multiplication (c1, c2);
	SymbolicExpression subtraction1 = new Subtraction(c1, log1);
	Multiplication multiplication1 = new Multiplication(addition1, c2);
	Multiplication multiplication2 = new Multiplication(addition1, multiplication0);
	Multiplication multiplication3 = new Multiplication(c1, c2);
        Multiplication multiplication4 = new Multiplication(subtraction1, addition1);
	Multiplication multiplication5 = new Multiplication(multiplication2, c2);
	Multiplication multiplication6 = new Multiplication(multiplication1, c2);
        Multiplication multiplication7 = new Multiplication(c2, multiplication1);
        Multiplication multiplication8 = new Multiplication(multiplication1, c2);

	//toString()
	assertEquals(multiplication1.toString(), "(5.0+5.0)*2.0");
	assertEquals(multiplication2.toString(), "(5.0+5.0)*1.0*2.0");
	assertEquals(multiplication3.toString(), "1.0*2.0");
	assertEquals(multiplication4.toString(), "(1.0-Log(1.0))*(5.0+5.0)");
	assertEquals(multiplication5.toString(), "(5.0+5.0)*1.0*2.0*2.0");
	assertEquals(multiplication6.toString(), "(5.0+5.0)*2.0*2.0");


	//getLeft() och getRight()
	assertEquals(multiplication1.getLeft(), multiplication2.getLeft());
	assertEquals(multiplication6.getRight(), multiplication5.getRight());

        //Equals
	//assertTrue(multiplication6.equals(multiplication7));
	assertTrue(multiplication6.equals(multiplication8));
	assertFalse(multiplication6.equals(multiplication1));
	assertFalse(multiplication5.equals(multiplication1));
	
	//Eval
	assertEquals(multiplication1.eval(env).toString(), "20.0");
	assertEquals(multiplication2.eval(env).toString(), "20.0");
	assertEquals(multiplication3.eval(env).toString(), "2.0");
	assertEquals(multiplication4.eval(env).toString(), "10.0");
	assertEquals(multiplication5.eval(env).toString(), "40.0");
	assertEquals(evaluator.evaluate(multiplication5, env).toString(), "40.0");
	
    }

    @Test
    public void testIntegrationDivision(){
       	Environment env = new Environment();
	final EvaluationVisitor evaluator = new EvaluationVisitor();
	
	SymbolicExpression c1 = new Constant(1);
	SymbolicExpression c2 = new Constant(2);
	SymbolicExpression c3 = new Constant(10);
	SymbolicExpression c4 = new Constant(5);
	SymbolicExpression log1 = new Log(c1);
	SymbolicExpression addition1 = new Addition(c4, c4);
	SymbolicExpression multiplication1 = new Multiplication (c1, c2);
	SymbolicExpression subtraction1 = new Subtraction(c1, log1);
	Division division1 = new Division(addition1, c2);
	Division division2 = new Division(addition1, multiplication1);
	Division division3 = new Division(c1, c2);
	Division division4 = new Division(subtraction1, addition1);
	Division division5 = new Division(division2, c2);
	Division division6 = new Division(division1, c2);
	Division division7 = new Division(c2, division1);
	Division division8 = new Division(division1, c2);

	//toString()
	assertEquals(division1.toString(), "(5.0+5.0)/2.0");
	assertEquals(division2.toString(), "(5.0+5.0)/1.0*2.0");
	assertEquals(division3.toString(), "1.0/2.0");
	assertEquals(division4.toString(), "(1.0-Log(1.0))/(5.0+5.0)");
	assertEquals(division5.toString(), "(5.0+5.0)/1.0*2.0/2.0");
	assertEquals(division6.toString(), "(5.0+5.0)/2.0/2.0");


	//getLeft() och getRight()
	assertEquals(division1.getLeft(), division2.getLeft());
	assertEquals(division6.getRight(), division5.getRight());

        //Equals
	assertFalse(division6.equals(division7));
	assertTrue(division6.equals(division8));
	assertFalse(division6.equals(division1));
	assertFalse(division5.equals(division1));
	
	//Eval
	assertEquals(division1.eval(env).toString(), "5.0");
	assertEquals(division2.eval(env).toString(), "5.0");
	assertEquals(division3.eval(env).toString(), "0.5");
	assertEquals(division4.eval(env).toString(), "0.1");
	assertEquals(division5.eval(env).toString(), "2.5");
	assertEquals(evaluator.evaluate(division5, env).toString(), "2.5"); 
	
    }

    @Test
    public void testIntegrationAssignment(){

	Environment env = new Environment();
	final EvaluationVisitor evaluator = new EvaluationVisitor();
	
	SymbolicExpression c1 = new Constant(1);
	SymbolicExpression c2 = new Constant(2);
	SymbolicExpression z1 = new Variable("z");
	SymbolicExpression z2 = new Variable ("z");
	SymbolicExpression x = new Variable ("x");
	SymbolicExpression y = new Variable ("y");
	SymbolicExpression log1 = new Log(c1);
	SymbolicExpression b = new Variable("b");	
	SymbolicExpression addition1 = new Addition(c1, c2);
	SymbolicExpression subtraction1 = new Subtraction(c1, log1);
	Assignment assignment1 = new Assignment(addition1, z1);
	Assignment assignment2 = new Assignment(addition1, z2);
	Assignment assignment3 = new Assignment(c1, x);
	Assignment assignment4 = new Assignment(subtraction1, y);
	Assignment assignment5 = new Assignment(assignment3, b);

	//toString()
	assertEquals(assignment1.toString(), "1.0+2.0=z");
	assertEquals(assignment2.toString(), "1.0+2.0=z");
	assertEquals(assignment3.toString(), "1.0=x");
	assertEquals(assignment4.toString(), "1.0-Log(1.0)=y");
	assertEquals(assignment5.toString(), "1.0=x=b");


	//getLeft() och getRight()
	assertEquals(assignment1.getLeft(), assignment2.getLeft());
	assertEquals(assignment2.getRight(), assignment1.getRight());

	assertEquals(assignment1.eval(env).toString(), "3.0");
	assertEquals(assignment2.eval(env).toString(), "3.0");
	assertEquals(assignment3.eval(env).toString(), "1.0");
	assertEquals(assignment4.eval(env).toString(), "1.0");
	assertEquals(assignment5.eval(env).toString(), "1.0");
	//	assertEquals(evaluator.evaluate(assignment5, env).toString(), "1.0");
	
	
    }

    @Test
    public void testIntegrationCos(){
      	Environment env = new Environment();
	final EvaluationVisitor evaluator = new EvaluationVisitor();
       
	SymbolicExpression c1 = new Constant(0);
	SymbolicExpression c2 = new Constant(2);
	SymbolicExpression c3 = new Negation(c2);
	SymbolicExpression c4 = new Constant(10);
	SymbolicExpression c5 = new Constant(5);
	SymbolicExpression c6 = new Constant(1);
	SymbolicExpression division1 = new Division(c4, c5);
	SymbolicExpression addition1 = new Addition(c1, c2);
	SymbolicExpression addition2 = new Addition(addition1, c3);
	SymbolicExpression subtraction1 = new Subtraction(c2,c2);
	SymbolicExpression subtraction2 = new Subtraction(division1, c2);
	Cos cos1 = new Cos(c1);
	Cos cos2 = new Cos(addition1);
	Cos cos3 = new Cos(subtraction2);
	Cos cos4 = new Cos(subtraction1);
	Cos cos5 = new Cos(new Constant(0));
	SymbolicExpression multiplication1 = new Multiplication(c6, c2);
	SymbolicExpression addition3 = new Addition(multiplication1, new Log(c6));
	SymbolicExpression subtraction3 = new Subtraction(addition3, c2);
	Cos cos6 = new Cos(subtraction3);

	assertEquals(cos2.toString(), "Cos(0.0+2.0)");
	assertEquals(cos1.toString(), "Cos(0.0)");
	assertEquals(cos3.toString(), "Cos(10.0/5.0-2.0)");
	assertEquals(cos4.toString(), "Cos(2.0-2.0)");
	
	assertEquals(cos1.getNode().getValue(), 0.0);
	assertTrue(cos1.equals(cos5));
	assertTrue(cos5.equals(cos5));
	assertFalse(cos1.equals(cos2));
        assertFalse(cos2.equals(cos1));
	assertFalse(cos1.equals(cos4));
	
	
	
	assertEquals(cos1.eval(env).toString(), "1.0");
	assertEquals(cos3.eval(env).toString(), "1.0");
	assertEquals(cos4.eval(env).toString(), "1.0");
	assertEquals(cos6.eval(env).toString(), "1.0");
	assertEquals(evaluator.evaluate(cos6, env).toString(), "1.0");
    }

    @Test
    public void testIntegrationSin(){

      	Environment env = new Environment();
	final EvaluationVisitor evaluator = new EvaluationVisitor();

	SymbolicExpression c1 = new Constant(0);
	SymbolicExpression c2 = new Constant(2);
	SymbolicExpression c3 = new Negation(c2);
	SymbolicExpression c4 = new Constant(10);
	SymbolicExpression c5 = new Constant(5);
	SymbolicExpression c6 = new Constant(1);
	SymbolicExpression division1 = new Division(c4, c5);
	SymbolicExpression addition1 = new Addition(c1, c2);
	SymbolicExpression addition2 = new Addition(addition1, c3);
	SymbolicExpression subtraction1 = new Subtraction(c2,c2);
	SymbolicExpression subtraction2 = new Subtraction(division1, c2);
	Sin sin1 = new Sin(c1);
	Sin sin2 = new Sin(addition1);
	Sin sin3 = new Sin(subtraction2);
	Sin sin4 = new Sin(subtraction1);
	Sin sin5 = new Sin(new Constant(0));
	SymbolicExpression multiplication1 = new Multiplication(c6, c2);
	SymbolicExpression addition3 = new Addition(multiplication1, new Log(c6));
	SymbolicExpression subtraction3 = new Subtraction(addition3, c2);
	Sin sin6 = new Sin(subtraction3);

	assertEquals(sin2.toString(), "Sin(0.0+2.0)");
	assertEquals(sin1.toString(), "Sin(0.0)");
	assertEquals(sin3.toString(), "Sin(10.0/5.0-2.0)");
	assertEquals(sin4.toString(), "Sin(2.0-2.0)");
	
	assertEquals(sin1.getNode().getValue(), 0.0);
	assertTrue(sin1.equals(sin5));
	assertTrue(sin5.equals(sin5));
	assertFalse(sin1.equals(sin2));
        assertFalse(sin2.equals(sin1));
	assertFalse(sin1.equals(sin4));
	
       
	assertEquals(sin1.eval(env).toString(), "0.0");
	assertEquals(sin3.eval(env).toString(), "0.0");
       	assertEquals(sin1.eval(env).toString(), "0.0");
	assertEquals(sin4.eval(env).toString(), "0.0");
	assertEquals(sin6.eval(env).toString(), "0.0");
	assertEquals(evaluator.evaluate(sin6, env).toString(), "0.0");

    }

    @Test
    public void testIntegrationExp(){
	Environment env = new Environment();
	final EvaluationVisitor evaluator = new EvaluationVisitor();
	
	SymbolicExpression c1 = new Constant(0);
	SymbolicExpression c2 = new Constant(2);
	SymbolicExpression c3 = new Negation(c2);
	SymbolicExpression c4 = new Constant(10);
	SymbolicExpression c5 = new Constant(5);
	SymbolicExpression c6 = new Constant(1);
	SymbolicExpression division1 = new Division(c4, c5);
	SymbolicExpression addition1 = new Addition(c1, c2);
	SymbolicExpression addition2 = new Addition(addition1, c3);
	SymbolicExpression subtraction1 = new Subtraction(c2,c2);
	SymbolicExpression subtraction2 = new Subtraction(division1, c2);
	Exp exp1 = new Exp(c1);
	Exp exp2 = new Exp(addition1);
	Exp exp3 = new Exp(subtraction2);
	Exp exp4 = new Exp(subtraction1);
	SymbolicExpression multiplication1 = new Multiplication(c6, c2);
	SymbolicExpression addition3 = new Addition(multiplication1, new Log(c6));
	SymbolicExpression subtraction3 = new Subtraction(addition3, c2);
	Exp exp5 = new Exp(subtraction3);

	assertEquals(exp2.toString(), "Exp(0.0+2.0)");
	assertEquals(exp1.toString(), "Exp(0.0)");
	assertEquals(exp3.toString(), "Exp(10.0/5.0-2.0)");
	assertEquals(exp4.toString(), "Exp(2.0-2.0)");
	
	assertEquals(exp1.getNode().getValue(), 0.0);

	
	assertEquals(exp1.eval(env).toString(), "1.0");
	assertEquals(exp3.eval(env).toString(), "1.0");
	assertEquals(exp4.eval(env).toString(), "1.0");
	assertEquals(exp5.eval(env).toString(), "1.0");
	assertEquals(evaluator.evaluate(exp5, env).toString(), "1.0");
	
    }
    
    @Test
    public void testIntegrationLog(){
	Environment env = new Environment();
	final EvaluationVisitor evaluator = new EvaluationVisitor();
	
	SymbolicExpression c1 = new Constant(1);
	SymbolicExpression c2 = new Constant(2);
	SymbolicExpression c3 = new Negation(c2);
	SymbolicExpression c4 = new Constant(10);
	SymbolicExpression c5 = new Constant(5);
	SymbolicExpression division1 = new Division(c4, c5);
	SymbolicExpression addition1 = new Addition(c1, c2);
	SymbolicExpression addition2 = new Addition(addition1, c3);
	SymbolicExpression subtraction1 = new Subtraction(c2,c1);
	SymbolicExpression subtraction2 = new Subtraction(division1, c1);
	Log log1 = new Log(c1);
	Log log2 = new Log(addition1);
	Log log3 = new Log(subtraction2);
	Log log4 = new Log(subtraction1);
	SymbolicExpression multiplication1 = new Multiplication(addition2, c1);
	SymbolicExpression addition3 = new Addition(multiplication1, log1);
	Log log5 = new Log(addition3);

	assertEquals(log2.toString(), "Log(1.0+2.0)");
	assertEquals(log1.toString(), "Log(1.0)");
	assertEquals(log3.toString(), "Log(10.0/5.0-1.0)");
	assertEquals(log4.toString(), "Log(2.0-1.0)");

	assertEquals(log1.getNode().getValue(), 1.0);

	assertEquals(log1.eval(env).toString(), "0.0");
	assertEquals(log3.eval(env).toString(), "0.0");
	assertEquals(log4.eval(env).toString(), "0.0");
	assertEquals(log5.eval(env).toString(), "0.0");
	assertEquals(evaluator.evaluate(log5, env).toString(), "0.0");
	
    }

    @Test
    public void testIntegrationNegation(){
	Environment env = new Environment();
	final EvaluationVisitor evaluator = new EvaluationVisitor();

	SymbolicExpression c1 = new Constant(1);
	SymbolicExpression c2 = new Constant(2);
	SymbolicExpression c3 = new Negation(c2);
	SymbolicExpression addition1 = new Addition(c1, c2);
	SymbolicExpression addition2 = new Addition(c2,c1);
	SymbolicExpression addition3 = new Addition(c1, c2);
	SymbolicExpression subtraction1 = new Subtraction(c1, c1);
	SymbolicExpression subtraction2 = new Subtraction(subtraction1, c1);
	SymbolicExpression multiplication1 = new Multiplication(c3, c3);
	Negation neg1 = new Negation(c1);
	Negation neg2 = new Negation(addition1);
	Negation neg3 = new Negation(subtraction1);
	Negation neg4 = new Negation (addition2);
	Negation neg5 = new Negation (addition3);
	Negation neg6 = new Negation (new Constant(1));
	Negation neg7 = new Negation(subtraction2);
	Negation neg8 = new Negation(multiplication1);
	SymbolicExpression multiplication2 = new Multiplication(neg7, neg1);
	SymbolicExpression subtraction3 = new Subtraction(neg1, c2);
	Negation neg9 = new Negation(multiplication2);
	Negation neg10 = new Negation(neg9);
	Negation neg11 = new Negation(subtraction3);
	Negation neg12 = new Negation(new Variable("z"));

	assertEquals(neg1.toString(), "-(1.0)");
	assertEquals(neg2.toString(), "-(1.0+2.0)");
	assertEquals(neg3.toString(), "-(1.0-1.0)");
	assertEquals(neg12.toString(), "-(z)");

	assertEquals(neg1.getNode().getValue(), 1.0);
	
	assertTrue(neg2.equals(neg4));
	assertFalse(neg2.equals(neg1));
	assertTrue(neg2.equals(neg5));
	assertTrue(neg6.equals(neg1));
	

	assertEquals(neg1.eval(env).toString(), "-1.0");
	assertEquals(neg2.eval(env).toString(), "-3.0");
	assertEquals(neg3.eval(env).toString(), "-0.0");
	assertEquals(neg4.eval(env).toString(), "-3.0");
	assertEquals(neg5.eval(env).toString(), "-3.0");
	assertEquals(neg6.eval(env).toString(), "-1.0");
	assertEquals(neg7.eval(env).toString(), "1.0");
	assertEquals(neg8.eval(env).toString(), "-4.0");
	assertEquals(neg9.eval(env).toString(), "1.0");
	assertEquals(neg10.eval(env).toString(), "-1.0");
	assertEquals(neg11.eval(env).toString(), "3.0");
	assertEquals(evaluator.evaluate(neg11, env).toString(), "3.0");
    }
    
 
    @Test
    public void testIntegrationVariable(){
	Environment env = new Environment();
	final EvaluationVisitor evaluator = new EvaluationVisitor();

	SymbolicExpression c1 = new Constant(1);
	SymbolicExpression c2 = new Constant(2);
	SymbolicExpression c3 = new Negation(c1);
	SymbolicExpression addition1 = new Addition(c1, c3);

	Variable variable1 = new Variable("a");
	Variable variable2 = new Variable("b");
	Variable variable3 = new Variable("a");
	Variable variable4 = new Variable("x");
	
	assertEquals(variable1.toString(), "a");
	assertEquals(variable2.toString(), "b");
	
	assertTrue(variable1.equals(variable3));

	assertEquals(variable1.eval(env).toString(), "a");
	Assignment assignment1 = new Assignment(c1, variable1);
	assignment1.eval(env);
	assertEquals(variable1.eval(env).toString(), "1.0");
	Addition addition2 = new Addition(addition1, variable1);
	assertEquals(addition2.eval(env).toString(), "1.0");

	// Vi assignar b och x till 2 = b = x
	Assignment assignment2 = new Assignment(c2, variable2);
	Assignment assignment3 = new Assignment(assignment2, variable4);
	assignment3.eval(env);

	//Vi kollar att b = 2 och x = 2
	assertEquals(variable4.eval(env).toString(), "2.0");
	assertEquals(variable2.eval(env).toString(), "2.0");

	//Kollar om man kan reassigna variabel som redan är assignad (Vi assignar a till 2 som redan är assignad 1)
	Assignment assignment4 = new Assignment(c2, variable1);
	assignment4.eval(env);
	assertEquals(variable1.eval(env).toString(), "2.0");

	assertEquals(evaluator.evaluate(variable1, env).toString(), "2.0");

    }
    
    @Test
    public void testIntegrationScope()
    {
	Environment env = new Environment();
	final EvaluationVisitor evaluator = new EvaluationVisitor();
	
	Constant c = new Constant(1);
	Variable v = new Variable("v");
	Variable x = new Variable("x");
	Assignment a = new Assignment(c, v);
	Assignment b = new Assignment(c, x);
	Scope s = new Scope(a);
	Scope p = new Scope(b);
	SymbolicExpression add = new Addition(s, p);    
	System.out.println(evaluator.evaluate(s, env));
	assertEquals("1.0", evaluator.evaluate(s, env).toString());
	assertEquals("2.0", evaluator.evaluate(add, env).toString());
    }
}
