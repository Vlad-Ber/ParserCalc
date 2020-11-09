package org.ioopm.calculator.ast;

import java.util.*;

public class FunctionDeclaration extends SymbolicExpression{
    private String identifier;
    private LinkedList<SymbolicExpression> arguments;
    private Queue<SymbolicExpression> sequences;


    public FunctionDeclaration(String ide, LinkedList<SymbolicExpression> args, Queue<SymbolicExpression> seq){
	this.identifier = ide;
	this.arguments = args;
	this.sequences = seq;
    }

    /**
     * @brief Gets the name of the funciton
     */
    public String getIdentifier(){
	return this.identifier;
    }

    /**
     * @brief Gets the amount of arguments that the funciton takes
     */
    public int amountArguments(){
	return arguments.size();
    }

    /** 
     * @brief Gets the variable arguments of a function call
     * @return A LinkedList of SymbolicExpressions representing the arguments 
     */
    public LinkedList <SymbolicExpression> getArgs(){
	return this.arguments;
    }

    /** 
     * @brief Makes a copy of the sequences of a function declaration
     * @return A Queue of SymbolicExpressions representing sequences 
     */
    public Queue<SymbolicExpression> getSeq(){
	
	Queue<SymbolicExpression> tmp = new LinkedList<SymbolicExpression>();
	Iterator<SymbolicExpression> iterator = this.sequences.iterator();
	while(iterator.hasNext()){
	    tmp.add(iterator.next());
	}
	return tmp;          
    }

    
    @Override
    public boolean isCommand(){
	return true;
    }
    
    @Override
    public SymbolicExpression eval(Environment env){
	return new Constant(5);
    }
}
