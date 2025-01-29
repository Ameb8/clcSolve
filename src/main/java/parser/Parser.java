package parser;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Deque;

import tokens.*;

public class Parser {
	private TokenBuilder builder;
	
	public Parser() {
		builder = new TokenBuilder();
		System.out.println("ParserCreated");
	}
	
	public void evaluateExpression(String expression) {
		List<Token> infixExpression = parseExpression(expression);
		System.out.println(infixExpression.toString());
		List<Token> postfixExpression = convertPostfix(infixExpression);
		System.out.println(postfixExpression.toString());
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
			
			//handle binary vs. unary '-'
			if(thisToken.toString().equals("-") && (prevToken == null || prevToken.preceedsUnary()))
				thisToken = builder.getToken("u-");
			
			tokenList.addLast(thisToken);
			current.setLength(0);
			
			if(!(thisToken instanceof InvalidInput))
				prevToken = thisToken;
			
			thisToken = null;
				
			
		}
		
		return tokenList;
	}
	
	public List<Token> convertPostfix(List<Token> infixExpression) {
		Deque<Token> operatorStack = new ArrayDeque<>();
		List<Token> postfixExpression = new LinkedList<>();
		
		for(Token token: infixExpression) {
			if(!(token instanceof InvalidInput)) {
				token.toRPN(operatorStack, postfixExpression);
			}
		}
		
		return postfixExpression;
		
	}
	
	public Double evaluate()
	
}
