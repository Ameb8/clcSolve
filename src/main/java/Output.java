import java.util.LinkedList;
import java.util.List;

import errors.Error;
import errors.ErrorTracker;

import java.util.HashMap;

import tokens.InvalidInput;
import tokens.Token;

public class Output {
	private HashMap<Token, List<String>> errorTokens;
	private Evaluator e;
	
	public Output() { }
	
	public String getResult(String expression) {
		e = new Evaluator();
		e.evaluateExpression(expression);
		
		return getOutput();
	}
	
	
	private String getOutput() {
		StringBuilder output = new StringBuilder();
		
		int tokenIndex = 0;
		
		for(Token token : e.getInfix()) {
			if(ErrorTracker.getErrors(token) != null) {
				output.append("<");
				output.append(++tokenIndex);
				output.append(">");
			}
			
			output.append(token.toString());
		}
		
		if(tokenIndex > 0)
			output.append(getErrorMsg());

		if(e.getResult() != null) {
			output.append(getSolvedExpression());
			output.append("= ");
			output.append(e.getResult());
		} else {
			output.append(getSolvedExpression());
			output.append(" could not be evaluated");
		}
		
		return output.toString();
	}
	
	private String getErrorMsg() {
		StringBuilder errorMsg = new StringBuilder();
		errorMsg.append("\nERRORS:\n\n");
		int index = 0;
		
		for(Token token : e.getInfix()) {
			List<String> errs = ErrorTracker.getErrors(token);
			
			if(errs != null) {
				errorMsg.append(++index);
				errorMsg.append(". (");
				errorMsg.append(token.toString().trim());
				errorMsg.append(")\n");
				
				for(String err : errs) {
					errorMsg.append("\t");
					errorMsg.append(err);
					errorMsg.append("\n");
				}
			}
		}
		
		return errorMsg.toString();
	}
	
	private String getSolvedExpression() {
		StringBuilder solved = new StringBuilder();
		solved.append("\n\n");
		
		
		for(Token token : e.getInfix()) {
			if(!(token instanceof InvalidInput))
				solved.append(token.toString());
		}
		
		return solved.toString();
	}
}
