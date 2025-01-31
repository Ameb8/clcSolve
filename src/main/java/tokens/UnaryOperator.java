package tokens;

import java.util.Deque;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import errors.ErrorTracker;

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
    
    public boolean preceedsUnary() {
    	return true;
    }
    
    public byte getPrecedence() {
    	return precedence;
    }
    
    public boolean isLeftAssociative() {
    	return isLeftAssociative();
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
    	if(symbol.equals("sqrt") && result.peek() < 0) {
    		ErrorTracker.addError(this, "Square root of negative numbers not supported");
    		return false;
    	}
    	
    	if((symbol.equals("ln") || symbol.equals("log10")) && result.peek() <= 0) {
    		ErrorTracker.addError(this, "Operand for logarthmic expressions must be positive");
    		return false;
    	}

    	if(symbol.equals("cot") && Math.sin(result.peek()) == 0) {
    		ErrorTracker.addError(this, "cotangent of "  + result.peek() + " is undefined, as sin of " + result.peek() + " is zero");
    	}

        result.push(operation.apply(result.pop()));
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
