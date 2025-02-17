package tokens;

import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;
import calculator.ErrorTracker;

public class UnaryOperator extends Token implements Operator {
    private byte precedence;
    private boolean isLeftAssociative;
    private Function<Double, Double> operation;

    public UnaryOperator(String symbol, long tokenId, byte precedence, boolean isLeftAssociative, Function<Double, Double> operation) {
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
    	return isLeftAssociative();
    }

    @Override
    public boolean toRPN(Deque<Token> operatorStack, List<Token> infixExpression) {
    	while(!operatorStack.isEmpty() && operatorStack.peek() instanceof Operator) {
            Operator top = (Operator) operatorStack.peek();
            
            if((isLeftAssociative && precedence <= top.getPrecedence()) || (!isLeftAssociative && precedence < top.getPrecedence()))
            	infixExpression.add(operatorStack.pop());
            else 
                break;
        }
        
        operatorStack.push(this);
        return true;
    }

    @Override
    public boolean evaluate(Deque<Double> result) {
    	Double a = null;
    	double operationResult;
    	
    	try {
    		a = result.pop();
    	} catch(NoSuchElementException e) {
    		ErrorTracker.addError(this, "This Operator requires one operand");
    		return false;
    	}
    	
    	try {
    		operationResult = operation.apply(a);
    	} catch(IllegalArgumentException e) { //operand invalid for given UnaryOperator
    		ErrorTracker.addError(this, e.getMessage());
    		return false;
    	}
    	
        result.push(operationResult);
        return true;
    }

    @Override
    public String toString() {
    	return symbol;
    }
    
    @Override
    protected Token copyToken(long newId) {
    	return new UnaryOperator(symbol, newId, precedence, isLeftAssociative, operation);
    }
}
