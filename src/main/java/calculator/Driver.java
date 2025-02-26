package calculator;

public class Driver {
	public static void main(String[] args) {
		String input = String.join(" ",  args).trim();

		if(input.equalsIgnoreCase("help")) {
			help();
		} else if(!input.isBlank()) {
			Output output = new Output();
			System.out.println(output.getResult(input));
		} else {
			System.out.println("No expression detected");
		}
	}
	
	public static void help() {
		StringBuilder help = new StringBuilder();
		
		help.append("Enter 'clc', followed by a mathematical expression to be evaluated\n");
		help.append("All basic mathematical operations are supported\n");
		help.append("In addition:\n\t'sqrt' or '√' for square root\n\t'sin', 'cos', 'tan', 'cot' for trig operations");
		help.append("\n\t'log' for base-10 log\n\t'ln' for natural log\n\t'!' for factorial");
		
		System.out.println(help.toString());
	}

}