package tokens;

import java.util.HashMap;
import java.util.HashSet;

public class TokenBuilder {
	private HashMap<String, Token> tokenRegistry;
	private HashSet<String> tokenValidator;
	private static long tokenNum;
	
	public TokenBuilder() {
		tokenRegistry = SupportedTokens.getSupportedTokens();
		tokenValidator = getTokenValidator();
		tokenNum = 0;
		System.out.println("TokenBuilder Created");
	}
	
	/**
	 * Converts input String into Token 
	 * Returns null if argument is not token, but could be the start of one
	 * Throws InvalidArgumentException if argument is not supported token or start of token
	 * 	
	 * @param tokenVal input to be converted to Token
	 * @return Token based on String input
	 */
	public Token getToken(String tokenVal) throws IllegalArgumentException {
		//DEBUG
		System.out.println("TokenVal: " + tokenVal);
		
		try {
			double operandValue = Double.parseDouble(tokenVal);
			return new Operand(tokenNum++, operandValue);
		} catch(NumberFormatException e) {}
		
		if(tokenRegistry.containsKey(tokenVal)) {
			Token token = tokenRegistry.get(tokenVal);
			token.setTokenId(tokenNum++);
			return token;
		}
		
		if(!tokenValidator.contains(tokenVal))
			return new InvalidInput(tokenVal, tokenNum);
		
		return null;	
	}

	
	private HashSet<String> getTokenValidator() {
		HashSet<String> tokenValidator = new HashSet<>();
		StringBuilder keyBuilder = new StringBuilder();
		
		for(String tokenKey : tokenRegistry.keySet()) {
			//DEBUG
			System.out.println("\n\n\ntokenRegistry: " + tokenRegistry.toString());
			
			keyBuilder.setLength(0);
			
			while(keyBuilder.length() < tokenKey.length()) {
				keyBuilder.append(tokenKey.charAt(keyBuilder.length()));
				tokenValidator.add(keyBuilder.toString());
			}
		}
		
		return tokenValidator;
	}
	
	
}
