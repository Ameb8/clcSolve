package errors;

import java.util.HashMap;

import tokens.Token;

public class ErrorTracker {
	private HashMap<Token, Error> errorMap;
	
	public ErrorTracker() {
		this.errorMap = new HashMap<>();
	}
}
