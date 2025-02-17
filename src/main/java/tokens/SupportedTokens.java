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

	/**
	 * Creates HashMap storing Tokens of all supported operators.
	 * Operator Tokens are stored as value
	 * valid input parsed to operator is stored as keys
	 * To add additional supported operators, simply create a new Operator implementation
	 * the lambda function determines the functionality of the operator
	 * if range of input is finite, this can be handled in the class being instantiated
	 * 
	 * @return HashMap containing all supported tokens
	 */
	public static HashMap<String, Token> getSupportedTokens() {
		HashMap<String, Token> tokens = new HashMap<>();
		
		//add binary operators
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
		
		Token division = new BinaryOperator("÷", 0, (byte) 1, true, (a, b) -> {
			if(b == 0)
				throw new IllegalArgumentException("Cannot divide by zero");
			
			return a / b;
		});
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
		
		Token sqrRoot = new UnaryOperator("sqrt", 0, (byte) 2, false, (a) -> {
			if(a < 0)
				throw new IllegalArgumentException("Square root of negative numbers is not supported");			
			return Math.sqrt(a);	
		});
		tokens.put("sqrt", sqrRoot);
		tokens.put("√", sqrRoot);
		
		Token unaryMinus = new UnaryOperator("-", 0, (byte) 2, false, (a) -> a * -1);
		tokens.put("u-", unaryMinus);
		
		Token sin = new UnaryOperator("sin", 0, (byte) 2, false, (a) -> Math.sin(a));
		tokens.put("sin", sin);
		
		Token cos = new UnaryOperator("cos", 0, (byte) 2, false, (a) -> Math.cos(a));
		tokens.put("cos", cos);
		
		Token tan = new UnaryOperator("tan", 0, (byte) 2, false, (a) -> Math.tan(a));
		tokens.put("tan", tan);
		
		Token cot = new UnaryOperator("cot", 0, (byte) 2, false, (a) -> {
			if(Math.sin(a) == 0)
				throw new IllegalArgumentException("Cotangent cannot be calculated, as the sine of "  + a + " is zero, and cotangent requires dividing by the sine of the operand");
			return Math.cos(a) / Math.sin(a);
		});
		tokens.put("cot", cot);
		
		Token ln = new UnaryOperator("ln", 0, (byte) 2, false, (a) -> {
			if(a <= 0)
				throw new IllegalArgumentException("Logarithmic operations can only be applied to positive numbers");
			return Math.log(a);
		});
		tokens.put("ln", ln);
		
		Token log10 = new UnaryOperator("log10", 0, (byte) 2, false, (a) -> {
			if(a <= 0)
				throw new IllegalArgumentException("Logarithmic operations can only be paplied to postive numbers");
			return Math.log10(a);
		});
		tokens.put("log", log10);
		
		Token factorial = new UnaryOperator("!", 0, (byte) 2, false, (a) -> {
			if(a < 1 || Math.floor(a) != a)
				throw new IllegalArgumentException("Factorial can only be applied to positive integer values");
			
			double result = 1;
			
			for(int i = 1; i <= a; i++)
				result *= i;
			
			return result;
		});
		tokens.put("!", factorial);

		return tokens;
	}
}
