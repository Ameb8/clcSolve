package calculator;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import tokens.Token;

public class ErrorTracker {
	private static HashMap<Token, List<String>> errorMap = new HashMap<>();
	
	public static List<String> getErrors(Token token) {
		return errorMap.get(token);
	}
	
	public static void addError(Token token, String errMsg) {
		if(!errorMap.containsKey(token))
			errorMap.put(token, new LinkedList<String>());
	
		errorMap.get(token).add(errMsg);
	}
	
	public static void reset() {
		errorMap.clear();
	}
}
