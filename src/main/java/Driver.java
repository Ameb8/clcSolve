import parser.Parser;

public class Driver {

	public static void main(String[] args) {
		System.out.println("Hello, World!");
		Parser parser = new Parser();
		
		parser.evaluateExpression("2 + 23 * 13");
		parser.evaluateExpression("2 * (12 + 22)");

	}

}
