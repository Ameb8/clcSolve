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
		
		Token leftGrouping = new Parentheses("(", 0, true);
		tokens.put("(", leftGrouping);
		tokens.put("{", leftGrouping);
		tokens.put("[", leftGrouping);
		
		Token rightGrouping = new Parentheses(")", 0, false);
		tokens.put(")", rightGrouping);
		tokens.put("}", rightGrouping);
		tokens.put("]", rightGrouping);
		
		Token sqrRoot = new UnaryOperator("sqrt", 0, (byte) 2, false, (a) -> Math.sqrt(a));
		tokens.put("sqrt", sqrRoot);
		tokens.put("√ ", sqrRoot);
		
		
		
		System.out.println("TEST______:\n" + multiplication.toString());
		
		
		
		return tokens;
	}
}
