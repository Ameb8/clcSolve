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
		Token addition = new BinaryOperator("+", 0, (byte) 0, true, (a, b) -> a + b);
		tokens.put("+", addition);
		
		Token subtraction = new BinaryOperator("-", 0, (byte) 0, true, (a, b) -> a - b);
		tokens.put("-", subtraction);
		
		Token multiplication = new BinaryOperator("*", 0, (byte) 1, true, (a, b) -> a * b);
		tokens.put("*", multiplication);
		tokens.put("⋅", multiplication);
		tokens.put("×", multiplication);
		tokens.put("X", multiplication);
		tokens.put("x", multiplication);
		
		Token division = new BinaryOperator("÷", 0, (byte) 1, true, (a, b) -> a / b);
		tokens.put("÷", division);
		tokens.put("/", division);
		
		Token exponent =  new BinaryOperator("^", 0, (byte) 2, true, (a, b) -> Math.pow(a, b));
		tokens.put("^", exponent);
		
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
		
		Token unaryMinus = new UnaryOperator("-", 0, (byte) 2, false, (a) -> a * -1);
		tokens.put("u-", unaryMinus);
		
		Token sin = new UnaryOperator("sin", 0, (byte) 2, false, (a) -> Math.sin(a));
		tokens.put("sin", sin);
		
		Token cos = new UnaryOperator("cos", 0, (byte) 2, false, (a) -> Math.cos(a));
		tokens.put("cos", cos);
		
		Token tan = new UnaryOperator("tan", 0, (byte) 2, false, (a) -> Math.tan(a));
		tokens.put("tan", tan);
		
		//Token factorial = new UnaryOperator("!", 0, (byte) 2, false, (a) -> Math.factorial(a));
		
		
		
		
		
		//System.out.println("TEST______:\n" + multiplication.toString());
		
		
		
		return tokens;
	}
}
