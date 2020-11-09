package org.ioopm.calculator.ast;

import java.util.*;


public class FunctionCall extends SymbolicExpression{
    private FunctionDeclaration func;
    private LinkedList<SymbolicExpression> arguments; //Variables
    private Queue<SymbolicExpression> sequences;
    private Queue<SymbolicExpression> argumentValues; //Konstanter som skickas in

    public FunctionCall(FunctionDeclaration n, Queue<SymbolicExpression> argValues){
	this.func= n;
	this.arguments = n.getArgs();
	this.sequences = n.getSeq();
	this.argumentValues = argValues;
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }

    /** @brief Gets the queue of sequences of the function call
     * @return A Queue of SymbolicExpressions representing the sequences
     */
    public Queue<SymbolicExpression> getSequences(){
	Queue<SymbolicExpression> tmp = new LinkedList<SymbolicExpression>();
	Iterator<SymbolicExpression> iterator = this.sequences.iterator();
	while(iterator.hasNext()){
	    tmp.add(iterator.next());
	}
	return tmp;          
    }

    /**
     * @return The FunctionDecleration of the FunctionCall
     */
    public FunctionDeclaration getFunc(){
	return this.func;
    }

    /** @brief Gets the queue of argument values of the function call
     * @return A Queue of SymbolicExpressions representing argument values of the function call
     */
    public Queue<SymbolicExpression> getArgumentValues(){
	Queue<SymbolicExpression> tmp = new LinkedList<SymbolicExpression>();
	Iterator<SymbolicExpression> iterator = this.argumentValues.iterator();
	while(iterator.hasNext()){
	    tmp.add(iterator.next());
	}
	return tmp;
    }

    /**
     * @brief Replaces the sequences of a funciton
     */
    public void newSeq( Queue<SymbolicExpression> sequ){
	this.sequences = sequ;
    }

     /** @brief Gets the variable arguments of a function call
     * @return A LinkedList of SymbolicExpressions representing the arguments 
     */
    public LinkedList<SymbolicExpression> getArguments(){
	return this.arguments;
    }
    
    public SymbolicExpression eval(Environment env){
	return new Constant(0);
    }
	
}
