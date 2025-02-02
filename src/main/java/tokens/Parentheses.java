package tokens;

import java.util.Deque;
import java.math.BigDecimal;
import java.util.List;

import calculator.ErrorTracker;

public class Parentheses extends Token {
	boolean isOpen;
	
    public Parentheses(String symbol, long tokenId, boolean isOpen) {
        super(symbol, tokenId);    	
    	this.isOpen = isOpen;
    }
    
    @Override
    public boolean preceedsUnary() {
    	if(isOpen)
    		return true;
    	
    	return false;
    }
	
	@Override
	public boolean evaluate(Deque<Double> result) {
		ErrorTracker.addError(this, "Unclosed Parentheses");
		
		return false;
	}

	@Override
	public boolean toRPN(Deque<Token> operatorStack, List<Token> postfixExpression) {
        if(isOpen) {
            operatorStack.push(this);
        } else {
            while (!operatorStack.isEmpty() && !(operatorStack.peek() instanceof Parentheses))
                postfixExpression.add(operatorStack.pop());
            
            
            if(operatorStack.isEmpty()) {
            	ErrorTracker.addError(this, "Unopened Parentheses");	
            	return false;
            }
            
            operatorStack.pop(); // Discard the open parenthesis
        }
        
        return true;
	}
	
	@Override
	protected Token copyToken(long newId) {
		return new Parentheses(symbol, newId, isOpen);
	}

}
