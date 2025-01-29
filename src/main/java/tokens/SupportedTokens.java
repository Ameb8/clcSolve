package tokens;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;

public class SupportedTokens {
	//private List<Token> supportedTokens;
	
	public SupportedTokens() {
		//supportedTokens = getSupportedTokens();
	}

	public static HashMap<String, Token> getSupportedTokens() {
		HashMap<String, Token> tokens = new HashMap<>();
		
		//add binary operators
		//String symbol, long tokenId, byte precedence, boolean isLeftAssociative, BiFunction<Double, Double, Double> operation
		Token addition = new Operator("+", 0, (byte) 0, true, (a, b) -> a + b);
		tokens.put("+", addition);
		
		Token subtraction = new Operator("-", 0, (byte) 0, true, (a, b) -> a - b);
		tokens.put("-", subtraction);
		
		Token multiplication = new Operator("*", 0, (byte) 1, true, (a, b) -> a * b);
		tokens.put("*", multiplication);
		tokens.put("⋅", multiplication);
		tokens.put("×", multiplication);
		tokens.put("X", multiplication);
		tokens.put("x", multiplication);
		
		Token division = new Operator("÷", 0, (byte) 1, true, (a, b) -> a / b);
		tokens.put("÷", division);
		tokens.put("/", division);
		
		System.out.println("TEST______:\n" + multiplication.toString() + "\n" + addition.toString());
		
		
		
		return tokens;
	}
}
