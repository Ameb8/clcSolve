package tokens;

import java.util.Deque;
import java.util.List;

public class Parentheses extends Token {
	boolean isOpen;
	
    public Parentheses(boolean isOpen) {
        this.isOpen = isOpen;
    }
	
	@Override
	public void evaluate(Deque<Double> result) {
		System.out.println("Parentheses should not be present during evaluation.");
	}

	@Override
	public void toRPN(Deque<Token> operatorStack, List<Token> infixExpression) {
        if(isOpen) {
            operatorStack.push(this);
        } else {
            while (!operatorStack.isEmpty() && !(operatorStack.peek() instanceof Parentheses)) {
                infixExpression.add(operatorStack.pop());
            }
            operatorStack.pop(); // Discard the open parenthesis
        }
	}

}
