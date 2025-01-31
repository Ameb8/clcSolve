import parser.Evaluator;

public class Driver {

	public static void main(String[] args) {
		System.out.println("Hello, World!");
		Evaluator evaluator = new Evaluator();
		
		//parser.evaluateExpression("2 + 23 * 13");
		//parser.evaluateExpression("2 * (12 + 22)");
		//parser.evaluateExpression("2 + 12 * 22");
		//parser.evaluateExpression("6/l3l   +22x2");
		//evaluator.evaluateExpression(" 100 + 20 * -5");
		//evaluator.evaluateExpression(" 100 + sqrt(25) * -5");
		evaluator.evaluateExpression("sqrt(sqrt(25)sqrt(25))^10");
		
	}

}
