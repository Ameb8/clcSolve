package parser;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

import errors.ErrorTracker;

import java.util.Deque;

import tokens.*;

public class Evaluator {
	private TokenBuilder builder;
	private List<Token> displayExpression;
	private ErrorTracker errors;
	
	public Evaluator() {
		builder = new TokenBuilder();
		errors = new ErrorTracker();
		System.out.println("ParserCreated");
	}
	
	public void evaluateExpression(String expression) {
		List<Token> infixExpression = parseExpression(expression);
		System.out.println("Tokenized Infix\t" + infixExpression.toString());
		List<Token> postfixExpression = convertPostfix(infixExpression);
		System.out.println("Tokenized Postfix:\t" +  postfixExpression.toString());
		double result = evaluate(postfixExpression);
		System.out.println("Result:\t" + result);
	}
	
	public List<Token> parseExpression(String expression) {
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
			//DEBUG
			//else
				//System.out.println(thisToken.toString());
			
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
			
			thisToken = null;
				
			
		}
		
		return tokenList;
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
			
		
	
	
	public List<Token> convertPostfix(List<Token> infixExpression) {
		Deque<Token> operatorStack = new ArrayDeque<>();
		List<Token> postfixExpression = new LinkedList<>();
		
		//DEBUG
		//System.out.println("\n\n\nCONVERT TO POSTFIX: [" + infixExpression.toString() + "]");
		
		for(Token token: infixExpression) {
			//System.out.println("Token: {" + token + "}");
			
			if(!(token instanceof InvalidInput))
				token.toRPN(operatorStack, postfixExpression);
			
			//System.out.println("op stack:\n" + operatorStack.toString());
			//System.out.println("postfix list:\n" + postfixExpression.toString() + "\n");
		}
		
		while(!operatorStack.isEmpty())
			postfixExpression.addLast(operatorStack.pop());
		
		return postfixExpression;
		
	}
	
	public Double evaluate(List<Token> postfixExpression) {
		Deque<Double> evaluator = new ArrayDeque<>();
		
		for(Token token : postfixExpression) {
			System.out.println(evaluator.toString());
			
			token.evaluate(evaluator);
		}	
		return evaluator.pop();
	}
	
}
