package tokens;

import java.util.Deque;
import java.util.List;
import java.util.function.BiFunction;

import errors.ErrorTracker;

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

    /*public Token copyToken(Token other, long tokenId) {
    	return new Operator(other.getSymbol(), other.getTokenId(), other.getPrecedence, other.isLeftAssoicait)
    }*/
    
    @Override
    public byte getPrecedence() {
    	return precedence;
    }
    
    @Override
    public boolean isLeftAssociative() {
    	return isLeftAssociative;
    }

    @Override
    public void toRPN(Deque<Token> operatorStack, List<Token> infixExpression) {
        while(!operatorStack.isEmpty() && operatorStack.peek() instanceof Operator) {
            Operator top = (Operator) operatorStack.peek();
            if((isLeftAssociative && precedence <= top.getPrecedence()) || (!isLeftAssociative && precedence < top.getPrecedence())) {
            	infixExpression.add(operatorStack.pop());
            } else {
                break;
            }
        }
        
        operatorStack.push(this);
    }

    @Override
    public boolean evaluate(Deque<Double> result) {
        double b = result.pop();
        double a = result.pop();
        
        if(symbol.equals("÷") && b == 0) {
        	ErrorTracker.addError(this, "Cannot divide by zero");
        	return false;
        }
        
        result.push(operation.apply(a, b));
        return true;
    }
    
    @Override
    protected Token copyToken(long newId) {
    	return new BinaryOperator(symbol, newId, precedence, isLeftAssociative, operation);
    }
}

