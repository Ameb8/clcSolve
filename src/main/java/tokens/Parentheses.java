package tokens;

import java.util.Deque;
import java.util.List;

public class Parentheses extends Token {
	boolean isOpen;
	
    public Parentheses(String symbol, long tokenId, boolean isOpen) {
        super(symbol, tokenId);    	
    	this.isOpen = isOpen;
    }
    
    public boolean preceedsUnary() {
    	if(isOpen)
    		return true;
    	
    	return false;
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
