package tokens;


import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;

import calculator.ErrorTracker;

public class BinaryOperator extends Token implements Operator {
    private byte precedence;
    private boolean isLeftAssociative;
    private BiFunction<Double, Double, Double> operation;

    public BinaryOperator(String symbol, long tokenId, byte precedence, boolean isLeftAssociative, BiFunction<Double, Double, Double> operation) {
    	super(symbol, tokenId);
		this.precedence = precedence;
		this.isLeftAssociative = isLeftAssociative;
		this.operation = operation;
	}
    
    @Override
    public boolean preceedsUnary() {
    	return true;
    }

    @Override
    public byte getPrecedence() {
    	return precedence;
    }
    
    @Override
    public boolean isLeftAssociative() {
    	return isLeftAssociative;
    }

    @Override
    public boolean toRPN(Deque<Token> operatorStack, List<Token> infixExpression) {
        while(!operatorStack.isEmpty() && operatorStack.peek() instanceof Operator) {
            Operator top = (Operator) operatorStack.peek();
            if((isLeftAssociative && precedence <= top.getPrecedence()) || (!isLeftAssociative && precedence < top.getPrecedence())) {
            	infixExpression.add(operatorStack.pop());
            } else {
                break;
            }
        }
        
        operatorStack.push(this);
        
        return true;
    }

    @Override
    public boolean evaluate(Deque<Double> result) {
    	double a = 0;
    	double b = 0;
    	double operationResult;
    	
    	try {
    		b = result.pop();
    		a = result.pop();
    	} catch(NoSuchElementException e) {
    		ErrorTracker.addError(this, "This operator requires two adjacent operands");
    		return false;
    	}
    		
    	try {
    		operationResult = operation.apply(a, b);
    	} catch(IllegalArgumentException e) { //operand invalid for given BinaryOperator
    		ErrorTracker.addError(this, e.getMessage());
    		return false;
    	}
    	
        result.push(operationResult);
        return true;
    }
    
    @Override
    protected Token copyToken(long newId) {
    	return new BinaryOperator(symbol, newId, precedence, isLeftAssociative, operation);
    }
}

