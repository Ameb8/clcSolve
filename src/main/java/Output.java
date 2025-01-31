import java.util.LinkedList;
import java.util.List;

import errors.Error;

import java.util.HashMap;

import tokens.InvalidInput;
import tokens.Token;

public class Output {
	private HashMap<Token, List<Error>> errorTokens;
	private List<Error> errors;
	private List<Token> infix;
	private double result;
	
	public Output(List<Token> infix) {
		errors = new LinkedList<>();
		this.infix = infix;
	}
	
	public List<Error> getErrors() {
		return errors;
	}
	
	public List<Token> getInfix() {
		return infix;
	}
	
	public String toString() {
		StringBuilder output = new StringBuilder();
		
		int tokenIndex = 0;
		
		for(Token token : infix) {
			if(token instanceof InvalidInput) {
				output.append("<");
				output.append(++tokenIndex);
				output.append(">");
			}
			
			output.append(token.toString());
		}
		
		if(tokenIndex > 0)
			displayErrors();
		
		
		return output.toString();
	}
	
	public String displayErrors() {
		StringBuilder errorMsg = new StringBuilder();
		errorMsg.append("\nERRORS:\n\n");
		int index = 0;
		
		for(Token token : infix) {
			if(errorTokens.containsKey(token)) {
				errorMsg.append(++index);
				errorMsg.append(". (");
				errorMsg.append(token.toString().trim());
				errorMsg.append(")\n");
				
				for(Error error : errorTokens.get(token)) {
					errorMsg.append(error.toString());
					errorMsg.append("\n");
				}
			}
		}
		
		return errorMsg.toString();
	}
	
	public void addError(Token token, Error error)  {
		if(errorTokens.containsKey(token)) 
			errorTokens.get(token).add(error);
		else {
			List<Error> newError = new LinkedList<>();
			newError.add(error);
			errorTokens.put(token, newError);
		}
			
	}
	
	
}
