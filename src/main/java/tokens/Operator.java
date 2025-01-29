package tokens;

import java.util.Deque;
import java.util.List;
import java.util.function.BiFunction;

public class Operator extends Token {
    private byte precedence;
    private boolean isLeftAssociative;
    private BiFunction<Double, Double, Double> operation;

    public Operator(String symbol, long tokenId, byte precedence, boolean isLeftAssociative, BiFunction<Double, Double, Double> operation) {
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
    
    public byte getPrecedence() {
    	return precedence;
    }
    
    public boolean isLeftAssociative() {
    	return isLeftAssociative;
    }

    @Override
    public void toRPN(Deque<Token> operatorStack, List<Token> infixExpression) {
        while(!operatorStack.isEmpty() && operatorStack.peek() instanceof Operator) {
            Operator top = (Operator) operatorStack.peek();
            if((isLeftAssociative && precedence <= top.precedence) || (!isLeftAssociative && precedence < top.precedence)) {
            	infixExpression.add(operatorStack.pop());
            } else {
                break;
            }
        }
        
        operatorStack.push(this);
    }

    @Override
    public void evaluate(Deque<Double> result) {
        double b = result.pop();
        double a = result.pop();
        result.push(operation.apply(a, b));
    }
}

