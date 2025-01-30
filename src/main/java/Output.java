import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;

import tokens.InvalidInput;
import tokens.Token;

public class Output {
	private HashMap<Long, Error> errorTokens;
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
		StringBuilder errorList = new StringBuilder();
		errorList.append("\nERRORS:\n");
		int index = 0;
		
		for(Token token : infix) {
			if(token instanceof InvalidInput) {
				errorList.append(++index);
				errorList.append(".\t");
				errorList.append()
				
			}
		}
	}
	
	
}
