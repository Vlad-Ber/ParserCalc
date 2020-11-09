package org.ioopm.calculator.parser;
import java.io.*;
import java.util.*;
import org.ioopm.calculator.ast.*;

/**
 * @file CalculatorParser.java
 * @author Erik Norén, Vladislav Bertilsson
 * @date 13 Dec 2019
 * @brief This class contains the means for the calculator to read inputs from the 
 * user. Converts strings inputs from the terminal into SymbolicExpression that can
 * be understood by the logic of the calculator. Implements recursive descent to form
 * this SymbolicExpression
 *
 * @see http://wrigstad.com/ioopm19/assignments/assignment3.html
 */

public class CalculatorParser {
    private StreamTokenizer st;
    private final NamedConstant constants = new NamedConstant();
    private HashMap<String, FunctionDeclaration> functions;
    
    public CalculatorParser(){
	this.st = null;
    }

    /** @brief Parses the first token from the input stream
     * @return A SymbolicExpression object
     * @throws IOException If an I/O error occurs
     */
    public SymbolicExpression parse(String s, HashMap<String, FunctionDeclaration> funcs) throws IOException {
	this.st = new StreamTokenizer(new StringReader(s));
	this.functions = funcs;
	this.st.ordinaryChar('-');
	this.st.ordinaryChar('/');
	this.st.ordinaryChar('>');
	this.st.ordinaryChar('<');
        this.st.eolIsSignificant(true);
	st.nextToken();
	return command();
    }

    
    /** @brief Checks if the token last parsed is a FunctionDeclaration
     * @return A FunctionDeclaration object
     * @throws IllegalArgumentException If the function declaration is poorly written
     * @throws IOException If an I/O error occurs
     */
    public SymbolicExpression functionDeclaration() throws IOException{
	st.nextToken();
	if(st.ttype == st.TT_WORD){
	    if(!st.sval.equals(Sin.NAME) && !st.sval.equals(Cos.NAME) && !st.sval.equals("if") && !st.sval.equals("else") && !st.sval.equals(Exp.NAME) && !st.sval.equals(Log.NAME) && !st.sval.equals("function")){
		String identifier =  st.sval;
		Queue<SymbolicExpression> sequences = new LinkedList<SymbolicExpression>();
		LinkedList <SymbolicExpression> arguments = new LinkedList<SymbolicExpression>();
		FunctionDeclaration temporary = new FunctionDeclaration(identifier, arguments, sequences);
		functions.put(identifier, temporary);
		st.nextToken();
		if(st.ttype != '('){
		    throw new IllegalArgumentException("No opening paranthesis after identifier");
		}
		else{
		    st.nextToken();
		    while(st.ttype != ')'){
			SymbolicExpression variableCheck = assignment();
			if (variableCheck.isVariable()){
			    arguments.add(variableCheck);
			    if(st.ttype == ','){
				st.nextToken();
			    }
			    else if (st.ttype == ')'){
				break;
			    }
			    else{
				throw new IllegalArgumentException("Illegal input of arguments");
			    }
			}
			else{
			    throw new IllegalArgumentException("Not a valid argument to function");
			}
		    }
		    //Sequence loop
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    CalculatorParser parser = new CalculatorParser();
		    String parserInput = br.readLine();
		    SymbolicExpression parsedSequence = parser.parse(parserInput, this.functions);
		    Environment env = new Environment();
		    
		    while(!parsedSequence.toString().equals("end")){
			//System.out.println(parsedSequence);
			
			if(parsedSequence.isCommand()){
			    throw new IllegalArgumentException("Commands not allowed as sequnces");
			}
			
			if(parsedSequence.isAssignment()){
			    Assignment assig = (Assignment) parsedSequence;
			    Variable variable = (Variable) assig.getRight();
			    SymbolicExpression lhs = assig.getLeft();
			    env.put(variable, lhs);
			}
			//
			if(parsedSequence.isConditional()){
			    Conditional cond = (Conditional) parsedSequence;
			    VariableAssignedChecker checker = new VariableAssignedChecker();
			    SymbolicExpression rightSide = cond.getRight();
			    SymbolicExpression leftSide = cond.getLeft();
			    int counter = 0;
			    while(counter != arguments.size()){
				env.put((Variable) arguments.get(counter), new Constant(0));
				counter++;
			    }
			    if(!checker.check(rightSide, env) || !checker.check(leftSide, env)){
				throw new IllegalArgumentException("");
				//break;
			    }
			
			}
			sequences.add(parsedSequence);
	       
			parserInput = br.readLine();
			parsedSequence = parser.parse(parserInput, this.functions);
		    }
		    System.out.println("Function " + identifier + " has been added.");
		    return temporary; //new FunctionDeclaration(identifier, arguments, sequences);}	    
		}
	    }
	    else{
		throw new IllegalArgumentException("Not a valid function identifier");
	    }
	}
	else{
	    throw new IllegalArgumentException("Not a valid idenfitifer for function");
	}
    }

    /** @brief Checks if the token last parsed is a Command 
     * @return A Command object, either Quit, Vars or Clear, otherwise a SymbolicExpression object
     * @throws IllegalArgumentException If brackets are missmatched or invalid character at the end of expression
     * @throws IOException If an I/O error occurs
     */
    public SymbolicExpression command() throws IOException{
	if (st.ttype == st.TT_WORD) {
	    if (st.sval.equals("quit")){
		return Quit.instance();
	    }
	    if (st.sval.equals("vars")){
		return Vars.instance();
	    }
	    if (st.sval.equals("clear")) {
		return Clear.instance();
	    }
	    if (st.sval.equals("function")) {
		return functionDeclaration();
	    }
	}
	SymbolicExpression result = assignment();
	if (st.ttype != st.TT_EOF) {
	    throw new IllegalArgumentException("Mismatched brackets/invalid character at end of expression: " + tokenToString());
	}
	return result;
    }


    
    /** @brief Checks if the token last parsed is a Conditional
     * @return A Conditional object
     * @throws IllegalArgumentException If conditional expression is poorly written
     * @throws IOException If an I/O error occurs
     */
    public SymbolicExpression conditional() throws IOException{
	String op = "";
	st.nextToken();
	SymbolicExpression lhs = expression();
	if (st.ttype != '<' && st.ttype != '>' && st.ttype != '=')
	    throw new IllegalArgumentException("Nonvalid operator!");
	else
	    {
		op = String.valueOf((char) st.ttype);
		st.nextToken();
		if (st.ttype == '=')
		    {
			op = op + "=";
		    }
		else
		    {
			if (op.equals("="))
			    {
				throw new IllegalArgumentException("Nonvalid operator");
			    }
			else st.pushBack();
		    }
		    
	    }
	st.nextToken();
	SymbolicExpression rhs = expression();
	SymbolicExpression ifScope = scope();

	if (!ifScope.isScope())
	    throw new IllegalArgumentException("If-scope is not a valid scope");
	
	if (!(st.ttype == st.TT_WORD && st.sval.equals("else")))
	    throw new IllegalArgumentException("Missing else statment");
	
	st.nextToken();
	SymbolicExpression elseScope = scope();
	if (!elseScope.isScope())
	    throw new IllegalArgumentException("Else-scope is not a valid scope");

	return new Conditional(lhs, rhs, op, ifScope, elseScope);
    }
    
    /** @brief Checks if the token last parsed is an Assignment
     * @return A Assignment object otherwise a SymbolicExpression object
     * @throws IOException If an I/O error occurs
     */
    public SymbolicExpression assignment() throws IOException{
	SymbolicExpression result = expression();
	if (st.ttype == '=') {
	    st.nextToken();
	    SymbolicExpression rightExp = primary();
	    Assignment assignment = new Assignment(result, rightExp);
	    while (st.ttype == '=') {
		st.nextToken();
		assignment = new Assignment(assignment, primary());
	    }
	    return assignment;
	}
	return result;
    }	
    
    /** @brief Checks if the token last parsed is an Addition or Subtraction 
     * @return Either an Additon object or a Subtraction object, otherwise a SymbolicExpression object
     * @throws IOException If an I/O error occurs
     */
    public SymbolicExpression expression() throws IOException{
	SymbolicExpression result = term(); 
	while (st.ttype == '+' || st.ttype == '-') {
	    int operation = st.ttype;
	    if (operation == '+') {
		st.nextToken();
		result = new Addition(result, term());
	    } else if (operation == '-') {
		st.nextToken();
		
		result = new Subtraction(result, term());
	    }	   
	}
	return result;
    }

    /** @brief Checks if the token last parsed is a Multiplication or Divison
     * @return Either a Multiplication object or a Division object, otherwise a SymbolicExpression object
     * @throws IOException If an I/O error occurs
     */
    public SymbolicExpression term() throws IOException{
	SymbolicExpression result = scope();
	while (st.ttype == '*' || st.ttype == '/') {
	    int operation = st.ttype;
	    if (operation == '*') {
		st.nextToken();
		result = new Multiplication(result, scope());
	    }
	    if (operation == '/'){
		st.nextToken();
		result = new Division(result, scope());
	    }
		
	}
	return result;
    }

    /** @brief Checks if the token last parsed is a scope
     * @return A Scope object, otherwise a SymbolicExpression object
     * @throws IllegalArgumentException If brackets are missmatched
     * @throws IOException If an I/O error occurs
     */
    public SymbolicExpression scope() throws IOException{
	SymbolicExpression result;
	if (st.ttype == '{'){
	    st.nextToken();
            result = new Scope(assignment());
	    if (st.ttype == '}') {
		st.nextToken();
		return result;
	    }
            if (st.ttype != '}') {
                throw new SyntaxErrorException("expected '}'");
            }
	}
	return factor();
    }    

    
    /** @brief Checks if the token last parsed is a left paranthesis,
     * branches to either assignment() or primary()
     * @return A SymbolicExpression object
     * @throws SyntaxErrorException If brackets are missmatched
     * @throws IOException If an I/O error occurs
     */
    public SymbolicExpression factor() throws IOException{
	SymbolicExpression result;
	if (st.ttype == '('){
	    st.nextToken();
            result = assignment();
	    if (st.ttype == ')') {
		st.nextToken();
		return result;
	    }
            if (st.ttype != ')') {
                throw new SyntaxErrorException("expected ')'");
            }
	}
	return primary();
    }    

    /** @brief Checks if the token last parsed is a Constant, Unary, FunctionCall or Variable
     * @return Either a Constant object, FunctionCall object or a Variable object, otherwise a SymbolicExpression object
     * @throws IllegalArgumentException If nonvalid binding
     * @throws IllegalArgumentException If nonvalid expression
     * @throws IOException If an I/O error occurs
     */
    public SymbolicExpression primary() throws IOException{
	if (st.ttype == st.TT_NUMBER) {
	    double value = st.nval;
	    st.nextToken();
	    return new Constant(value);
	}
	if (st.ttype == '-'){
	    return unary();
	}
	if (st.ttype == st.TT_WORD) {
	    if (st.sval.equals(Sin.NAME) || st.sval.equals(Cos.NAME) ||
		st.sval.equals(Log.NAME) || st.sval.equals(Exp.NAME)) {
		return unary();
	    }
	    if (st.sval.equals("quit") || st.sval.equals("vars") || st.sval.equals("clear") || st.sval.equals("function") ||/* st.sval.equals("if") || */st.sval.equals("else")){
		throw new IllegalArgumentException("Nonvalid identifier");
	    }
	    if (st.sval.equals("if"))
		{
		    return conditional();
		}
	    
	    Variable var = new Variable (st.sval);
	    if (this.functions.containsKey(var.toString())){
		st.nextToken(); 
		if(st.ttype != '('){
		    throw new IllegalArgumentException("Expected (");
		}
		
		Queue<SymbolicExpression> argumentValues = new LinkedList<SymbolicExpression>();
		FunctionDeclaration func = this.functions.get(var.toString());
		int amountOfArguments = func.amountArguments();
		int counter = 0;
		
		if(amountOfArguments != 0){
		    st.nextToken();
		    while(st.ttype != ')'){
			SymbolicExpression argument = assignment();
			counter++;
			if(argument instanceof Variable){
			    final NamedConstantChecker checker = new NamedConstantChecker();
			    if(checker.check(argument)){
				//argument = new Constant(constants.getNamedConstant(argument.toString()));
			    }				    
			}
			if (argument.isVariable() || argument.isConstant()){
			    argumentValues.add(argument);
			    if(st.ttype == ','){
				st.nextToken();
			    }
			    else if(st.ttype == ')' && counter == amountOfArguments){
				st.nextToken();
				return new FunctionCall (func, argumentValues);
			    }
			}
			else{throw new IllegalArgumentException("Only constants allowed as function arguments");}
		    }
		}
		if(counter != amountOfArguments){
		    throw new IllegalArgumentException("Supposed to have " + amountOfArguments + " arguments. Had: "+ counter);
		}
						    
		st.nextToken();
		if(st.ttype != ')'){
		    throw new IllegalArgumentException("Excepted closing brackets after function call");
		}
		st.nextToken();
		//Vi vill lägga till sequences för att assigna argumenten
	
		return new FunctionCall(func, argumentValues);
	    }
	
	    
	    st.nextToken();
	    return var;
	    
	}
	else throw new IllegalArgumentException("Nonvalid expression");
    }
    /** @brief Checks if the token last parsed is either of our unaries
     * @return A Unary object, either Negation, Sin, Cos, Log or Exp
     * @throws IllegalArgumentException If nonvalid expression
     * @throws IOException If an I/O error occurs
     */
    public SymbolicExpression unary() throws IOException{
	if (st.ttype == '-') {
	    st.nextToken();
	    return new Negation(scope());
	}

	if (st.sval.equals(Sin.NAME)) {
	    st.nextToken();
	    return new Sin(scope());
	}
	if (st.sval.equals(Cos.NAME)) {
	    st.nextToken();
	    return new Cos(scope());
	}
	if (st.sval.equals(Log.NAME)) {
	    st.nextToken();
	    return new Log(scope());
	}
	if (st.sval.equals(Exp.NAME)) {
	    st.nextToken();
	    return new Exp(scope());
	}
	throw new IllegalArgumentException("Expression not valid");
    }


    //debug functions
    private void debug(String s) throws IOException {
	System.out.println(s + " debug: " + tokenToString());
    }
    
    private String tokenToString() throws IOException {
	if (st.ttype == st.TT_NUMBER) {
	    return st.nval + "";
	}
	else if (st.ttype == st.TT_WORD) {
	    return st.sval;
	}
	else if(st.ttype == st.TT_EOF) {
	    return "End of file";
	}
	else {
	    char c = (char)st.ttype;
	    return c + "";
	}
    }
}
