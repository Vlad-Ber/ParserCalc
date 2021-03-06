package org.ioopm.calculator.testers;

import org.ioopm.calculator.ast.*;
import org.junit.Test;
import org.junit.Before;
import java.util.*;

import junit.framework.TestCase;
import static org.junit.Assert.*;

public class TestAst extends TestCase
{ 
    @Test
    public void testAstAddition()
    {
	Addition add = new Addition(new Constant(2), new Constant(1));
	assertEquals(add.isConstant(), false);
	assertEquals(add.isVariable(), false);
	assertEquals(add.isCommand(), false);

	try {add.getValue();}
	catch (IllegalArgumentException e) {assertTrue(true);}
	assertEquals(add.getName(), "+");
	assertEquals(add.getPriority(), 6);
	assertEquals(add.getLeft().getValue(), 2.0);
	assertEquals(add.getRight().getValue(), 1.0);

	assertEquals(add.toString(), "2.0+1.0");
    }

    @Test
    public void testAstSubtraction()
    {
	Subtraction sub = new Subtraction(new Constant(2), new Constant(1));
	assertEquals(sub.isConstant(), false);
	assertEquals(sub.isVariable(), false);
	assertEquals(sub.isCommand(), false);

	try {sub.getValue();}
	catch (IllegalArgumentException e) {assertTrue(true);}
	assertEquals(sub.getName(), "-");	
	assertEquals(sub.getPriority(), 6);
	assertEquals(sub.getLeft().getValue(), 2.0);
	assertEquals(sub.getRight().getValue(), 1.0);

	assertEquals(sub.toString(), "2.0-1.0");
    }

    @Test
    public void testAstMultiplication()
    {
	Multiplication mult = new Multiplication(new Constant(1), new Constant(2));
	assertEquals(mult.isConstant(), false);
	assertEquals(mult.isVariable(), false);
	assertEquals(mult.isCommand(), false);

	try {mult.getValue();}
	catch (IllegalArgumentException e) {assertTrue(true);}
      	assertEquals(mult.getName(), "*");
	assertEquals(mult.getPriority(), 9);
	assertEquals(mult.getLeft().getValue(), 1.0);
	assertEquals(mult.getRight().getValue(), 2.0);

	assertEquals(mult.toString(), "1.0*2.0");
    }

    @Test
    public void testAstDivision()
    {
	Division div = new Division(new Constant(1), new Constant(2));
	assertEquals(div.isConstant(), false);
	assertEquals(div.isVariable(), false);
	assertEquals(div.isCommand(), false);

	try {div.getValue();}
	catch (IllegalArgumentException e) {assertTrue(true);}
	assertEquals(div.getName(), "/");
	assertEquals(div.getPriority(), 9);
	assertEquals(div.getLeft().getValue(), 1.0);
	assertEquals(div.getRight().getValue(), 2.0);

	assertEquals(div.toString(), "1.0/2.0");
    }

    @Test
    public void testAstAssignment()
    {
	Assignment eq = new Assignment(new Constant(1), new Variable("v"));
	assertEquals(eq.isConstant(), false);
	assertEquals(eq.isVariable(), false);
	assertEquals(eq.isCommand(), false);

	try {eq.getValue();}
	catch (IllegalArgumentException e) {assertTrue(true);}
	assertEquals(eq.getName(), "=");
	assertEquals(eq.getPriority(), 3);
	assertEquals(eq.getLeft().getValue(), 1.0);
	assertEquals(eq.getRight().isVariable(), true);

	assertEquals(eq.toString(), "1.0=v");
    }

    @Test
    public void testAstSin(){
	Environment env = new Environment();
	
	SymbolicExpression c1 = new Constant(1);
	SymbolicExpression c2 = new Constant(2);
	Sin sin1 = new Sin(c1);
	SymbolicExpression addition = new Addition(c1, c2);
	Sin sin2 = new Sin(addition);
	Sin sin3 = new Sin(new Constant(0));

	assertEquals("Sin", sin1.getName());
	assertEquals("Sin", sin2.getName());
	
	assertFalse(sin1.isConstant());
	assertFalse(sin2.isConstant());
	
	assertFalse(sin1.isCommand());
	assertFalse(sin2.isCommand());

	assertEquals(sin1.getPriority(), 10);
	assertEquals(sin2.getPriority(), 10);
	
	assertFalse(sin1.isVariable());
	assertFalse(sin2.isVariable());
			
	assertEquals(sin1.toString(), "Sin(1.0)");
	assertEquals(sin2.toString(), "Sin(1.0+2.0)");

	assertEquals(sin1.getNode().getValue(), 1.0);

	assertEquals(sin3.eval(env).toString(), "0.0");
	
    }
	

    @Test
    public void testAstCos(){
	Environment env = new Environment();
       
	SymbolicExpression c1 = new Constant(1);
	SymbolicExpression c2 = new Constant(2);
	Cos cos1 = new Cos(c1);
	SymbolicExpression addition = new Addition(c1, c2);
	Cos cos2 = new Cos(addition);
	Cos cos3 = new Cos(new Constant(0));

	assertEquals("Cos", cos1.getName());
	assertEquals("Cos", cos2.getName());
	
	assertFalse(cos1.isConstant());
	assertFalse(cos2.isConstant());
	
	assertFalse(cos1.isCommand());
	assertFalse(cos2.isCommand());

	assertEquals(cos1.getPriority(), 10);
	assertEquals(cos2.getPriority(), 10);
	
	assertFalse(cos1.isVariable());
	assertFalse(cos2.isVariable());
		
	assertEquals(cos1.toString(), "Cos(1.0)");
	assertEquals(cos2.toString(), "Cos(1.0+2.0)");

	assertEquals(cos1.getNode().getValue(), 1.0);

	assertEquals(cos3.eval(env).toString(), "1.0");
		
	
    }

    @Test
    public void testAstExp(){
	Environment env = new Environment();
	
	SymbolicExpression c1 = new Constant(1);
	SymbolicExpression c2 = new Constant(2);
	Exp exp1 = new Exp(c1);
	SymbolicExpression addition = new Addition(c1, c2);
	Exp exp2 = new Exp(addition);
	Exp exp3 = new Exp(new Constant(0));

	assertEquals("Exp", exp1.getName());
	assertEquals("Exp", exp2.getName());
	
	assertFalse(exp1.isConstant());
	assertFalse(exp2.isConstant());
	
	assertFalse(exp1.isCommand());
	assertFalse(exp2.isCommand());

	assertEquals(exp1.getPriority(), 10);
	assertEquals(exp2.getPriority(), 10);
	
	assertFalse(exp1.isVariable());
	assertFalse(exp2.isVariable());
		
	assertEquals(exp1.toString(), "Exp(1.0)");
	assertEquals(exp2.toString(), "Exp(1.0+2.0)");

	assertEquals(exp1.getNode().getValue(), 1.0);

	assertEquals(exp3.eval(env).toString(), "1.0");
	
    }
    @Test
    public void testAstLog(){
	Environment env = new Environment();
       
	SymbolicExpression c1 = new Constant(1);
	SymbolicExpression c2 = new Constant(2);
	Log log1 = new Log(c1);
	SymbolicExpression addition = new Addition(c1, c2);
	Log log2 = new Log(addition);
	Log log3 = new Log(new Constant(1));

	assertEquals("Log", log1.getName());
	assertEquals("Log", log2.getName());
	
	assertFalse(log1.isConstant());
	assertFalse(log2.isConstant());
	
	assertFalse(log1.isCommand());
	assertFalse(log2.isCommand());

	assertEquals(log1.getPriority(), 10);
	assertEquals(log2.getPriority(), 10);
	
	assertFalse(log1.isVariable());
	assertFalse(log2.isVariable());
 	
	assertEquals(log1.toString(), "Log(1.0)");
	assertEquals(log2.toString(), "Log(1.0+2.0)");

	assertEquals(log1.getNode().getValue(), 1.0);

	
	assertEquals(log3.eval(env).toString(), "0.0");
    }

    @Test
    public void testAstNegation(){
	Environment env = new Environment();

	SymbolicExpression c1 = new Constant(1);
	SymbolicExpression c2 = new Constant(2);
	SymbolicExpression c3 = new Constant(-2);
	Negation neg1 = new Negation(c1);
	SymbolicExpression addition = new Addition(c1, c2);
	Negation neg2 = new Negation(addition);
	SymbolicExpression subtraction = new Subtraction(neg1, c2);
	Negation neg3 = new Negation(subtraction);
	Negation neg4 = new Negation(c3);

	assertEquals("-", neg1.getName());
	assertEquals("-", neg2.getName());
	
	assertFalse(neg1.isConstant());
	assertFalse(neg2.isConstant());
	
	assertFalse(neg1.isCommand());
	assertFalse(neg2.isCommand());

	assertEquals(neg1.getPriority(), 10);
	assertEquals(neg2.getPriority(), 10);
	
	assertFalse(neg1.isVariable());
	assertFalse(neg2.isVariable());

	assertEquals(neg1.toString(), "-(1.0)");
    }
    
    @Test
    public void testAstConstant()
    {
	Constant c = new Constant(2);
	assertEquals(c.isConstant(), true);
	assertEquals(c.isVariable(), false);
	assertEquals(c.isCommand(), false);

	assertEquals(c.getValue(), 2.0);
	try {c.getName();}
	catch (IllegalArgumentException e) {assertTrue(true);}
	assertEquals(c.getPriority(), 11);

	assertEquals(c.toString(), "2.0");
    }

    @Test
    public void testAstVariable()
    {
	Variable v = new Variable("v");
	assertEquals(v.isConstant(), false);
	assertEquals(v.isVariable(), true);
	assertEquals(v.isCommand(), false);

	try {v.getValue();}
	catch (IllegalArgumentException e) {assertTrue(true);}
	try {v.getName();}
	catch (IllegalArgumentException e) {assertTrue(true);}
	assertEquals(v.getPriority(), 11);

	assertEquals(v.toString(), "v");
    }

    
    @Test
    public void testAstScope()
    {
	Scope s = new Scope(new Constant(1));
	assertEquals(s.isConstant(), false);
	assertEquals(s.isVariable(), false);
	assertEquals(s.isCommand(), false);
	assertEquals(s.isScope(), true);
	
	try {s.getValue();}
	catch (IllegalArgumentException e) {assertTrue(true);}
	try {s.getName();}
	catch (IllegalArgumentException e) {assertTrue(true);}
	assertEquals(s.getPriority(), 10);
    }

    @Test
    public void testAstConditional()
    {
	Conditional c = new Conditional(new Constant(1), new Constant(2), ">", new Scope(new Constant(3)), new Scope(new Constant(4)));
	assertEquals(c.isConstant(), false);
	assertEquals(c.isVariable(), false);
	assertEquals(c.isCommand(), false);
	assertEquals(c.isNamedConstant(), false);
	
	try {c.getValue();}
	catch (IllegalArgumentException e) {assertTrue(true);}
	try {c.getName();}
	catch (IllegalArgumentException e) {assertTrue(true);}
	assertEquals(c.getPriority(), 11);

	assertTrue(c.getLeft().equals(new Constant(1)));
	assertTrue(c.getRight().equals(new Constant(2)));
	assertTrue(c.getOP().equals(">"));
    }
    
    
}


