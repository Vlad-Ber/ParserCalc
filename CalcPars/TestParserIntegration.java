package org.ioopm.calculator.testers;

import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.*;
import org.junit.Test;
import org.junit.Before;
import java.util.*;

import junit.framework.TestCase;
import static org.junit.Assert.*;

public class TestParserIntegration extends TestCase
{

    @Test
    public void testConstant()
    {
	HashMap<String, FunctionDeclaration> functions = new HashMap<String, FunctionDeclaration>();
	try
	    {
		CalculatorParser parser = new CalculatorParser();
		SymbolicExpression exp = new Constant(3);
		String expToString = exp.toString();
		SymbolicExpression calcExp = parser.parse(expToString, functions);
		assertTrue(exp.equals(calcExp));
	    }
	catch (Exception e){}
    }
     
    @Test
    public void testAddition()
    {
	try
	    {
		CalculatorParser parser = new CalculatorParser();
		HashMap<String, FunctionDeclaration> functions = new HashMap<String, FunctionDeclaration>();
		
		SymbolicExpression c1 = new Constant(1);
		SymbolicExpression c2 = new Constant(2);
		SymbolicExpression c3 = new Constant(10);
		SymbolicExpression c4 = new Constant(5);
		SymbolicExpression log1 = new Log(c1);
		SymbolicExpression neg1 = new Negation(c1);
		SymbolicExpression subtraction1 = new Subtraction(c4, c4);
		SymbolicExpression add0 = new Addition (c1, c2);
		Multiplication multiplication1 = new Multiplication(c1, log1);
		Addition add1 = new Addition(subtraction1, c2);
		Addition add2 = new Addition(add0, add1);
		Addition add3 = new Addition(c1, c2);
		Addition add4 = new Addition(multiplication1, subtraction1);
		Addition add5 = new Addition(add2, c2);
		Addition add6 = new Addition(add1, c2);
		Addition add7 = new Addition(c2, add1);
		Addition add8 = new Addition(add1, c2);
		Addition add9 = new Addition(c1, neg1);
		Addition add10 = new Addition(c1, add9);
		Addition add11 = new Addition(neg1, neg1);

		String add0ToString = add0.toString();
		String add1ToString = add1.toString();
		String add2ToString = add2.toString();
		String add3ToString = add3.toString();
		String add4ToString = add4.toString();
		String add5ToString = add5.toString();
		String add6ToString = add6.toString();
		String add7ToString = add7.toString();
		String add8ToString = add8.toString();
		String add9ToString = add9.toString();
		String add10ToString = add10.toString();
		String add11ToString = add11.toString();
		
		SymbolicExpression add0Exp = parser.parse(add0ToString, functions);
		SymbolicExpression add1Exp = parser.parse(add1ToString, functions);
		SymbolicExpression add2Exp = parser.parse(add2ToString, functions);
		SymbolicExpression add3Exp = parser.parse(add3ToString, functions);
		SymbolicExpression add4Exp = parser.parse(add4ToString, functions);
		SymbolicExpression add5Exp = parser.parse(add5ToString, functions);
		SymbolicExpression add6Exp = parser.parse(add6ToString, functions);
		SymbolicExpression add7Exp = parser.parse(add7ToString, functions);
		SymbolicExpression add8Exp = parser.parse(add8ToString, functions);
		SymbolicExpression add9Exp = parser.parse(add9ToString, functions);
		SymbolicExpression add10Exp = parser.parse(add10ToString, functions);
		SymbolicExpression add11Exp = parser.parse(add11ToString, functions);

		assertTrue(add0ToString.equals(add0Exp.toString()));
		assertTrue(add1ToString.equals(add1Exp.toString()));
		assertTrue(add2ToString.equals(add2Exp.toString()));
		assertTrue(add3ToString.equals(add3Exp.toString()));
		assertTrue(add4ToString.equals(add4Exp.toString()));
		assertTrue(add5ToString.equals(add5Exp.toString()));
		assertTrue(add6ToString.equals(add6Exp.toString()));
		assertTrue(add7ToString.equals(add7Exp.toString()));
		assertTrue(add8ToString.equals(add8Exp.toString()));
		assertTrue(add9ToString.equals(add9Exp.toString()));
		assertTrue(add10ToString.equals(add10Exp.toString()));
		assertTrue(add11ToString.equals(add11Exp.toString()));

	    }
	catch (Exception e) {}
    
    }
    
    @Test
    public void testSubtraction()
    {
	try
	    {
		CalculatorParser parser = new CalculatorParser();
		HashMap<String, FunctionDeclaration> functions = new HashMap<String, FunctionDeclaration>();
		
        	SymbolicExpression c1 = new Constant(1);
		SymbolicExpression c2 = new Constant(2);
		SymbolicExpression c3 = new Constant(10);
		SymbolicExpression c4 = new Constant(5);
		SymbolicExpression log1 = new Log(c1);
		SymbolicExpression neg1 = new Negation(c1);
		SymbolicExpression add1 = new Addition(c4, c4);
		SymbolicExpression sub0 = new Subtraction (c1, c2);
		Multiplication multiplication1 = new Multiplication(c1, log1);
		Subtraction sub1 = new Subtraction(add1, c2);
		Subtraction sub2 = new Subtraction(add1, sub0);
		Subtraction sub3 = new Subtraction(c1, c2);
		Subtraction sub4 = new Subtraction(multiplication1, add1);
		Subtraction sub5 = new Subtraction(sub2, c2);
		Subtraction sub6 = new Subtraction(sub1, c2);
		Subtraction sub7 = new Subtraction(c2, sub1);
		Subtraction sub8 = new Subtraction(sub1, c2);
		Subtraction sub9 = new Subtraction(c1, neg1);
		Subtraction sub10 = new Subtraction(c1, sub9);
		Subtraction sub11 = new Subtraction(neg1, neg1);

		String sub0ToString = sub0.toString();
		String sub1ToString = sub1.toString();
		String sub2ToString = sub2.toString();
		String sub3ToString = sub3.toString();
		String sub4ToString = sub4.toString();
		String sub5ToString = sub5.toString();
		String sub6ToString = sub6.toString();
		String sub7ToString = sub7.toString();
		String sub8ToString = sub8.toString();
		String sub9ToString = sub9.toString();
		String sub10ToString = sub10.toString();
		String sub11ToString = sub11.toString();
		
		SymbolicExpression sub0Exp = parser.parse(sub0ToString, functions);
		SymbolicExpression sub1Exp = parser.parse(sub1ToString, functions);
		SymbolicExpression sub2Exp = parser.parse(sub2ToString, functions);
		SymbolicExpression sub3Exp = parser.parse(sub3ToString, functions);
		SymbolicExpression sub4Exp = parser.parse(sub4ToString, functions);
		SymbolicExpression sub5Exp = parser.parse(sub5ToString, functions);
		SymbolicExpression sub6Exp = parser.parse(sub6ToString, functions);
		SymbolicExpression sub7Exp = parser.parse(sub7ToString, functions);
		SymbolicExpression sub8Exp = parser.parse(sub8ToString, functions);
		SymbolicExpression sub9Exp = parser.parse(sub9ToString, functions);
		SymbolicExpression sub10Exp = parser.parse(sub10ToString, functions);
		SymbolicExpression sub11Exp = parser.parse(sub11ToString, functions);

		assertTrue(sub0ToString.equals(sub0Exp.toString()));
		assertTrue(sub1ToString.equals(sub1Exp.toString()));
		assertTrue(sub2ToString.equals(sub2Exp.toString()));
		assertTrue(sub3ToString.equals(sub3Exp.toString()));
		assertTrue(sub4ToString.equals(sub4Exp.toString()));
		assertTrue(sub5ToString.equals(sub5Exp.toString()));
		assertTrue(sub6ToString.equals(sub6Exp.toString()));
		assertTrue(sub7ToString.equals(sub7Exp.toString()));
		assertTrue(sub8ToString.equals(sub8Exp.toString()));
		assertTrue(sub9ToString.equals(sub9Exp.toString()));
		assertTrue(sub10ToString.equals(sub10Exp.toString()));
		assertTrue(sub11ToString.equals(sub11Exp.toString()));

	    }
	catch (Exception e) {}
    
    }

    @Test
    public void testMultiplication()
    {
	try
	    {
		CalculatorParser parser = new CalculatorParser();
		HashMap<String, FunctionDeclaration> functions = new HashMap<String, FunctionDeclaration>();
		
		SymbolicExpression c1 = new Constant(1);
		SymbolicExpression c2 = new Constant(2);
		SymbolicExpression c3 = new Constant(10);
		SymbolicExpression c4 = new Constant(5);
		SymbolicExpression log1 = new Log(c1);
		SymbolicExpression add1 = new Addition(c4, c4);
		SymbolicExpression mult0 = new Multiplication (c1, c2);
		SymbolicExpression sub1 = new Subtraction(c1, log1);
		SymbolicExpression mult1 = new Multiplication(add1, c2);
		SymbolicExpression mult2 = new Multiplication(add1, mult0);
		SymbolicExpression mult3 = new Multiplication(c1, c2);
		SymbolicExpression mult4 = new Multiplication(sub1, add1);
		SymbolicExpression mult5 = new Multiplication(mult2, c2);
		SymbolicExpression mult6 = new Multiplication(mult1, c2);
		SymbolicExpression mult7 = new Multiplication(c2, mult1);
		SymbolicExpression mult8 = new Multiplication(mult1, c2);

		
		String mult0ToString = mult0.toString();
		SymbolicExpression mult0Exp = parser.parse(mult0ToString, functions);
		
		String mult1ToString = mult1.toString();
		SymbolicExpression mult1Exp = parser.parse(mult1ToString, functions);

		String mult2ToString = mult2.toString();
		SymbolicExpression mult2Exp = parser.parse(mult2ToString, functions);

		String mult3ToString = mult3.toString();
		SymbolicExpression mult3Exp = parser.parse(mult3ToString, functions);

		String mult4ToString = mult4.toString();
		SymbolicExpression mult4Exp = parser.parse(mult4ToString, functions);

		String mult5ToString = mult5.toString();
		SymbolicExpression mult5Exp = parser.parse(mult5ToString, functions);

		String mult6ToString = mult6.toString();
		SymbolicExpression mult6Exp = parser.parse(mult6ToString, functions);

		String mult7ToString = mult7.toString();
		SymbolicExpression mult7Exp = parser.parse(mult7ToString, functions);

		String mult8ToString = mult8.toString();
		SymbolicExpression mult8Exp = parser.parse(mult8ToString, functions);
		
		assertTrue(mult0ToString.equals(mult0Exp.toString()));
		assertTrue(mult1ToString.equals(mult1Exp.toString())); 
		assertTrue(mult2ToString.equals(mult2Exp.toString())); 
		assertTrue(mult3ToString.equals(mult3Exp.toString()));
		assertTrue(mult4ToString.equals(mult4Exp.toString()));
		assertTrue(mult5ToString.equals(mult5Exp.toString())); 
		assertTrue(mult6ToString.equals(mult6Exp.toString()));
		assertTrue(mult7ToString.equals(mult7Exp.toString()));
		assertTrue(mult8ToString.equals(mult8Exp.toString()));

	    }
	catch (Exception e) {}
    }

    @Test
    public void testDivision()
    {
	try
	    {
		CalculatorParser parser = new CalculatorParser();
		HashMap<String, FunctionDeclaration> functions = new HashMap<String, FunctionDeclaration>();
		
		SymbolicExpression c1 = new Constant(1);
		SymbolicExpression c2 = new Constant(2);
		SymbolicExpression c3 = new Constant(10);
		SymbolicExpression c4 = new Constant(5);
		SymbolicExpression log1 = new Log(c1);
		SymbolicExpression addition1 = new Addition(c4, c4);
		SymbolicExpression multiplication1 = new Multiplication (c1, c2);
		SymbolicExpression subtraction1 = new Subtraction(c1, log1);
		Division div1 = new Division(addition1, c2);
		SymbolicExpression div2 = new Division(addition1, multiplication1);
		Division div3 = new Division(c1, c2);
		Division div4 = new Division(subtraction1, addition1);
		Division div5 = new Division(div2, c2);
		Division div6 = new Division(div1, c2);
		Division div7 = new Division(c2, div1);
		Division div8 = new Division(div1, c2);

		String div1ToString = div1.toString();
		String div2ToString = div2.toString();
		String div3ToString = div3.toString();
		String div4ToString = div4.toString();
		String div5ToString = div5.toString();
		String div6ToString = div6.toString();
		String div7ToString = div7.toString();
		String div8ToString = div8.toString();

		SymbolicExpression div1Exp = parser.parse(div1ToString, functions);
		SymbolicExpression div2Exp = parser.parse(div2ToString, functions);
		SymbolicExpression div3Exp = parser.parse(div3ToString, functions);
		SymbolicExpression div4Exp = parser.parse(div4ToString, functions);
		SymbolicExpression div5Exp = parser.parse(div5ToString, functions);
		SymbolicExpression div6Exp = parser.parse(div6ToString, functions);
		SymbolicExpression div7Exp = parser.parse(div7ToString, functions);
		SymbolicExpression div8Exp = parser.parse(div8ToString, functions);

		assertTrue(div1ToString.equals(div1Exp.toString()));
		assertTrue(div2ToString.equals(div2Exp.toString()));
		assertTrue(div3ToString.equals(div3Exp.toString()));
		assertTrue(div4ToString.equals(div4Exp.toString()));
		assertTrue(div5ToString.equals(div5Exp.toString()));
		assertTrue(div6ToString.equals(div6Exp.toString()));
		assertTrue(div7ToString.equals(div7Exp.toString()));
		assertTrue(div8ToString.equals(div8Exp.toString()));


	    }
	catch (Exception e) {}
    
    }
    
    @Test
    public void testAssignment()
    {
	try
	    {
		CalculatorParser parser = new CalculatorParser();
		HashMap<String, FunctionDeclaration> functions = new HashMap<String, FunctionDeclaration>();

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
		
		String assignment1ToString = assignment1.toString();
		String assignment2ToString = assignment2.toString();
		String assignment3ToString = assignment3.toString();
		String assignment4ToString = assignment4.toString();
		String assignment5ToString = assignment5.toString();
      
		SymbolicExpression assignment1Exp = parser.parse(assignment1ToString, functions);
		SymbolicExpression assignment2Exp = parser.parse(assignment2ToString, functions);
		SymbolicExpression assignment3Exp = parser.parse(assignment3ToString, functions);
		SymbolicExpression assignment4Exp = parser.parse(assignment4ToString, functions);
		SymbolicExpression assignment5Exp = parser.parse(assignment5ToString, functions);

		assertTrue(assignment1ToString.equals(assignment1Exp.toString()));
		assertTrue(assignment2ToString.equals(assignment2Exp.toString()));
		assertTrue(assignment3ToString.equals(assignment3Exp.toString()));
		assertTrue(assignment4ToString.equals(assignment4Exp.toString()));
		assertTrue(assignment5ToString.equals(assignment5Exp.toString()));
	    }
	catch (Exception e) {}
    }

    @Test
    public void testSin(){
	try{
		    
	    CalculatorParser parser = new CalculatorParser();
	    HashMap<String, FunctionDeclaration> functions = new HashMap<String, FunctionDeclaration>();
	    
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
	    
	    String sin1ToString = sin1.toString();
	    SymbolicExpression sin1Exp = parser.parse(sin1ToString, functions);

	    String sin2ToString = sin2.toString();
	    SymbolicExpression sin2Exp = parser.parse(sin2ToString, functions);

	    String sin3ToString = sin3.toString();
	    SymbolicExpression sin3Exp = parser.parse(sin3ToString, functions);

	    String sin4ToString = sin4.toString();
	    SymbolicExpression sin4Exp = parser.parse(sin4ToString, functions);
	    
	    String sin5ToString = sin5.toString();
	    SymbolicExpression sin5Exp = parser.parse(sin5ToString, functions);
	    
	    String sin6ToString = sin6.toString();
	    SymbolicExpression sin6Exp = parser.parse(sin6ToString, functions);
	    
	    assertTrue(sin1ToString.equals(sin1Exp.toString()));
	    assertTrue(sin2ToString.equals(sin2Exp.toString()));
	    assertTrue(sin3ToString.equals(sin3Exp.toString()));
	    assertTrue(sin4ToString.equals(sin4Exp.toString()));
	    assertTrue(sin5ToString.equals(sin5Exp.toString()));
	    assertTrue(sin6ToString.equals(sin6Exp.toString()));
	    
	}
	catch (Exception e){}
    }

    @Test
    public void testExp(){
	try{
	    CalculatorParser parser = new CalculatorParser();
	    HashMap<String, FunctionDeclaration> functions = new HashMap<String, FunctionDeclaration>();
	    
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

	    String exp1ToString = exp1.toString();
	    SymbolicExpression exp1Exp = parser.parse(exp1ToString, functions);

	    String exp2ToString = exp2.toString();
	    SymbolicExpression exp2Exp = parser.parse(exp2ToString, functions);

	    String exp3ToString = exp3.toString();
	    SymbolicExpression exp3Exp = parser.parse(exp3ToString, functions);

	    String exp4ToString = exp4.toString();
	    SymbolicExpression exp4Exp = parser.parse(exp4ToString, functions);
	    
	    String exp5ToString = exp5.toString();
	    SymbolicExpression exp5Exp = parser.parse(exp5ToString, functions);

	    assertTrue(exp1ToString.equals(exp1Exp.toString()));
	    assertTrue(exp2ToString.equals(exp2Exp.toString()));
	    assertTrue(exp3ToString.equals(exp3Exp.toString()));
	    assertTrue(exp4ToString.equals(exp4Exp.toString()));
	    assertTrue(exp5ToString.equals(exp5Exp.toString()));
	}
	catch(Exception e){}
    }

    @Test
    public void testLog(){
	try{
	    HashMap<String, FunctionDeclaration> functions = new HashMap<String, FunctionDeclaration>();
	    CalculatorParser parser = new CalculatorParser();
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
	    

	    String log1ToString = log1.toString();
	    SymbolicExpression log1Exp = parser.parse(log1ToString, functions);

	    String log2ToString = log2.toString();
	    SymbolicExpression log2Exp = parser.parse(log2ToString, functions);

	    String log3ToString = log3.toString();
	    SymbolicExpression log3Exp = parser.parse(log3ToString, functions);

	    String log4ToString = log4.toString();
	    SymbolicExpression log4Exp = parser.parse(log4ToString, functions);
	    
	    String log5ToString = log5.toString();
	    SymbolicExpression log5Exp = parser.parse(log5ToString, functions);
	    
	    assertTrue(log1ToString.equals(log1Exp.toString()));
	    assertTrue(log2ToString.equals(log2Exp.toString()));
	    assertTrue(log3ToString.equals(log3Exp.toString()));
	    assertTrue(log4ToString.equals(log4Exp.toString()));
	    assertTrue(log5ToString.equals(log5Exp.toString()));
	}
	catch(Exception e){}
	
    }
    @Test
    public void testCos(){
	try{
	    CalculatorParser parser = new CalculatorParser();
	    HashMap<String, FunctionDeclaration> functions = new HashMap<String, FunctionDeclaration>();
	    
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

	
	    String cos1ToString = cos1.toString();
	    SymbolicExpression cos1Exp = parser.parse(cos1ToString, functions);

	    String cos2ToString = cos2.toString();
	    SymbolicExpression cos2Exp = parser.parse(cos2ToString, functions);

	    String cos3ToString = cos3.toString();
	    SymbolicExpression cos3Exp = parser.parse(cos3ToString, functions);

	    String cos4ToString = cos4.toString();
	    SymbolicExpression cos4Exp = parser.parse(cos4ToString, functions);
	    
	    String cos5ToString = cos5.toString();
	    SymbolicExpression cos5Exp = parser.parse(cos5ToString, functions);
	    
	    String cos6ToString = cos6.toString();
	    SymbolicExpression cos6Exp = parser.parse(cos6ToString, functions);
	    
	    assertTrue(cos1ToString.equals(cos1Exp.toString()));
	    assertTrue(cos2ToString.equals(cos2Exp.toString()));
	    assertTrue(cos3ToString.equals(cos3Exp.toString()));
	    assertTrue(cos4ToString.equals(cos4Exp.toString()));
	    assertTrue(cos5ToString.equals(cos5Exp.toString()));
	    assertTrue(cos6ToString.equals(cos6Exp.toString()));
	}
	catch(Exception e){}
    }
    @Test
    public void testNegation(){
	try{
	    CalculatorParser parser = new CalculatorParser();
	    HashMap<String, FunctionDeclaration> functions = new HashMap<String, FunctionDeclaration>();
	    
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

	    String neg1ToString = neg1.toString();
	    SymbolicExpression neg1Exp = parser.parse(neg1ToString, functions);

	    String neg2ToString = neg2.toString();
	    SymbolicExpression neg2Exp = parser.parse(neg2ToString, functions);

	    String neg3ToString = neg3.toString();
	    SymbolicExpression neg3Exp = parser.parse(neg3ToString, functions);

	    String neg4ToString = neg4.toString();
	    SymbolicExpression neg4Exp = parser.parse(neg4ToString, functions);
	    
	    String neg5ToString = neg5.toString();
	    SymbolicExpression neg5Exp = parser.parse(neg5ToString, functions);
	    
	    String neg6ToString = neg6.toString();
	    SymbolicExpression neg6Exp = parser.parse(neg6ToString, functions);

	    String neg7ToString = neg7.toString();
	    SymbolicExpression neg7Exp = parser.parse(neg7ToString, functions);

	    String neg8ToString = neg8.toString();
	    SymbolicExpression neg8Exp = parser.parse(neg8ToString, functions);

	    String neg9ToString = neg9.toString();
	    SymbolicExpression neg9Exp = parser.parse(neg9ToString, functions);

	    String neg10ToString = neg10.toString();
	    SymbolicExpression neg10Exp = parser.parse(neg10ToString, functions);
	    
	    String neg11ToString = neg11.toString();
	    SymbolicExpression neg11Exp = parser.parse(neg11ToString, functions);
	    
	    String neg12ToString = neg12.toString();
	    SymbolicExpression neg12Exp = parser.parse(neg12ToString, functions);
	    
	    assertTrue(neg1ToString.equals(neg1Exp.toString()));
	    assertTrue(neg2ToString.equals(neg2Exp.toString()));
	    assertTrue(neg3ToString.equals(neg3Exp.toString()));
	    assertTrue(neg4ToString.equals(neg4Exp.toString()));
	    assertTrue(neg5ToString.equals(neg5Exp.toString()));
	    assertTrue(neg6ToString.equals(neg6Exp.toString()));
	    assertTrue(neg7ToString.equals(neg7Exp.toString()));
	    assertTrue(neg9ToString.equals(neg9Exp.toString()));
	    assertTrue(neg10ToString.equals(neg10Exp.toString()));
	    assertTrue(neg11ToString.equals(neg11Exp.toString()));
	    assertTrue(neg12ToString.equals(neg12Exp.toString()));

	    
	}
	catch(Exception e){}
	
    }

    @Test
    public void testVariable(){

	try{
	    CalculatorParser parser = new CalculatorParser();
	    HashMap<String, FunctionDeclaration> functions = new HashMap<String, FunctionDeclaration>();
	    
	    SymbolicExpression c1 = new Constant(1);
	    SymbolicExpression c2 = new Constant(2);
	    SymbolicExpression c3 = new Negation(c1);
	    SymbolicExpression addition1 = new Addition(c1, c3);

	    Variable variable1 = new Variable("a");
	    Variable variable2 = new Variable("b");
	    Variable variable3 = new Variable("a");
	    Variable variable4 = new Variable("x");

	    
	    String variable1ToString = variable1.toString();
	    SymbolicExpression variable1Exp = parser.parse(variable1ToString, functions);

	    String variable2ToString = variable2.toString();
	    SymbolicExpression variable2Exp = parser.parse(variable2ToString, functions);

	    String variable3ToString = variable3.toString();
	    SymbolicExpression variable3Exp = parser.parse(variable3ToString, functions);

	    String variable4ToString = variable4.toString();
	    SymbolicExpression variable4Exp = parser.parse(variable4ToString, functions);
	    
	    assertTrue(variable1ToString.equals(variable1Exp.toString()));
	    assertTrue(variable2ToString.equals(variable2Exp.toString()));
	    assertTrue(variable3ToString.equals(variable3Exp.toString()));
	    assertTrue(variable4ToString.equals(variable4Exp.toString()));
	    
	}
	catch(Exception e){}
    }

    @Test
    public void testScopeAndConditional(){
	try{
	    CalculatorParser parser = new CalculatorParser();
	    HashMap<String, FunctionDeclaration> functions = new HashMap<String, FunctionDeclaration>();
  
	    SymbolicExpression c1 = new Constant(1);
	    SymbolicExpression c2 = new Constant(2);

	    SymbolicExpression addition1 = new Addition(c1,c2);    //3
	    SymbolicExpression subtraction1 = new Addition(c2,c2); //4
	    SymbolicExpression scope1 = new Scope(addition1);
	    SymbolicExpression scope2 = new Scope(subtraction1);
	    String op1 = "<";

	    Conditional conditional1 = new Conditional(c1,c2,op1,scope1,scope2);
	    String conditionalToString = conditional1.toString();
	    SymbolicExpression conditional1Exp = parser.parse(conditionalToString, functions);

	    assertTrue(conditionalToString.equals(conditional1Exp.toString()));
	    
	}
	catch(Exception e){}
    }
}

