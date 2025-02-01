

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

import errors.ErrorTracker;

import java.util.Deque;

import tokens.*;

public class Evaluator {
	private TokenBuilder builder;
	private Double result;
	
	private List<Token> infix;
	
	
	public Evaluator() {
		builder = new TokenBuilder();
		this.result = null;
	}
	
	public List<Token> getInfix() {
		return this.infix;
	}
	
	public Double getResult() {
		return result;
	}
	
	public void evaluateExpression(String expression) { 
		parseExpression(expression);
		List<Token> postfixExpression = convertPostfix(infix);

		result = null;
		
		
		try {
			result = evaluate(postfixExpression);
		} catch(IllegalArgumentException e) { }
	}
	
	private void parseExpression(String expression) {
		List<Token> tokenList = new LinkedList<>();
		Token prevToken = null;
		Token thisToken = null;
		StringBuilder current = new StringBuilder();
		
		int nextChar = -1;
		
		while(nextChar < expression.length() - 1) {
			nextChar++;
			
			if(expression.charAt(nextChar) == ' ')
				continue;
			
			current.append(expression.charAt(nextChar));
			
			if(Character.isDigit(expression.charAt(nextChar))) {
				while(nextChar + 1 < expression.length() && Character.isDigit(expression.charAt(nextChar + 1)) || expression.charAt(nextChar) == '.') {
					current.append(expression.charAt(++nextChar));
					
				}
			}
			
			thisToken = builder.getToken(current.toString().trim());
			
			if(thisToken == null)
				continue;
			
			//handle binary vs. unary '-'
			if(thisToken.toString().trim().equals("-") && (prevToken == null || prevToken.preceedsUnary()))
				thisToken = builder.getToken("u-");
			
			//add multiplication if parentheses
			if(multiplyParentheses(prevToken, thisToken))
				tokenList.add(builder.getToken("*"));
			
			tokenList.addLast(thisToken);
			current.setLength(0);
			
			if(!(thisToken instanceof InvalidInput))
				prevToken = thisToken;
			else
				ErrorTracker.addError(thisToken, "Character not recognized");
			thisToken = null;
				
			
		}
		
		this.infix = tokenList;
	}
	
	/**
	 * determines if implicit multiplication present in expression
	 * ex.: 2(2 + 2) = 2 * (2 + 2)
	 * 
	 * @param prevToken previously parsed Token
	 * @param thisToken current Token
	 * @return true if multiplication required
	 */
	private boolean multiplyParentheses(Token prevToken, Token thisToken) {
		if(thisToken instanceof Parentheses && thisToken.preceedsUnary()) {
			if(prevToken != null) {
				if(prevToken instanceof Operand)
					return true;
			
				if(prevToken instanceof Parentheses && !prevToken.preceedsUnary())
					return true;
			}
		}
		
		if(prevToken instanceof Parentheses && !prevToken.preceedsUnary()) {
			if(thisToken instanceof Operand)
				return true;
			
			if(thisToken instanceof UnaryOperator)
				return true;
		}
		
		if(prevToken instanceof Operand && thisToken instanceof UnaryOperator)
			return true;
		
		return false;
		
	}
			
		
	
	
	private List<Token> convertPostfix(List<Token> infixExpression) {
		Deque<Token> operatorStack = new ArrayDeque<>();
		List<Token> postfixExpression = new LinkedList<>();
		
		for(Token token: infixExpression) {
			if(!(token instanceof InvalidInput))
				token.toRPN(operatorStack, postfixExpression);
		}
		
		while(!operatorStack.isEmpty())
			postfixExpression.addLast(operatorStack.pop());
		
		return postfixExpression;
	}
	
	private Double evaluate(List<Token> postfixExpression) throws IllegalArgumentException {
		Deque<Double> evaluator = new ArrayDeque<>();
		
		for(Token token : postfixExpression) {
			boolean valid = token.evaluate(evaluator);
			
			if(!valid) {
				throw new IllegalArgumentException();
			}
		}	
		return evaluator.pop();
	}
	
}
