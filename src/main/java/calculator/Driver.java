package calculator;
import java.util.Scanner;

public class Driver {
	private static String savePath;

	public static void main(String[] args) {
		System.out.println("Welcome to clcSolve");
		Scanner s = new Scanner(System.in);
		
		while(run(s));
		
		s.close();
		
	}
	
	public static boolean run(Scanner s) {
		System.out.println("input 'help' for instructions or 'exit' to terminate application");
		System.out.println("Enter a mathematical Expression:");
		String input = s.nextLine().trim();
		
		if(input.equalsIgnoreCase("exit"))
			return false;
		if(input.equalsIgnoreCase("help"))
			help();
		if(input.equalsIgnoreCase("graph"));
			graph(input, s)
		else if(!input.isBlank())
			evaluate(input);
		
		return true;
		
	}
	
	public static void evaluate(String expression) {
		Output output = new Output();
		System.out.println(output.getResult(expression));
	}
	
	public static void help() {
		StringBuilder help = new StringBuilder();
		
		help.append("Enter a mathematical expression to be evaluated\n");
		help.append("All basic mathematical operations are supported\n");
		help.append("In addition:\n\t'sqrt' or '√' for square root\n\t'sin', 'cos', 'tan', 'cot' for trig operations");
		help.append("\n\t'log' for base-10 log\n\t'ln' for natural log\n\t'!' for factorial");
		
		System.out.println(help.toString());
	}
	
	public static void graph(String input, Scanner s) {
		if(savePath == null || savePath.isBlank())
			System.out.println("Please enter filepath to save graph");
		else
			System.out.println("Enter filepath to save graph, or save in " + savePath);
		if(!s.nextLine().isEmpty())
			savePath = s.nextLine();
			
		
		//if(savePath == null || savePath.isBlank())
		
	}

}