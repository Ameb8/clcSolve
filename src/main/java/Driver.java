public class Driver {

	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("Please provide an expression toe valuate");
			return;
		}
		
		String expression = String.join(" ", args);
		evaluate(expression);
	}
	
	public static void evaluate(String expression) {
		Output output = new Output();
		System.out.println(output.getResult(expression));
	}

}