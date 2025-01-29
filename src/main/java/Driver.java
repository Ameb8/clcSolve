import parser.Parser;

public class Driver {

	public static void main(String[] args) {
		System.out.println("Hello, World!");
		Parser parser = new Parser();
		
		//parser.evaluateExpression("2 + 23 * 13");
		//parser.evaluateExpression("2 * (12 + 22)");
		//parser.evaluateExpression("2 + 12 * 22");
		//parser.evaluateExpression("6/l3l   +22x2");
		parser.evaluateExpression("5 + sqrt(25");
		
	}

}
