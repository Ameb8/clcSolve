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
    	
    	try {
    		a = result.pop();
    	} catch(NoSuchElementException e) {
    		ErrorTracker.addError(this, "This Operator requires one operand");
    		return false;
    	}
    	
    	if(symbol.equals("sqrt") && a  <  0) {
    		ErrorTracker.addError(this, "Square root of negative numbers not supported");
    		return false;
    	}
    	
    	if((symbol.equals("ln") || symbol.equals("log10")) && a <= 0) {
    		ErrorTracker.addError(this, "Operand for logarthmic expressions must be positive");
    		return false;
    	}

    	if(symbol.equals("cot") && Math.sin(a) == 0) {
    		ErrorTracker.addError(this, "cotangent of "  + result.peek() + " is undefined, as sin of " + result.peek() + " is zero");
    	}
    	
        result.push(operation.apply(a));
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
