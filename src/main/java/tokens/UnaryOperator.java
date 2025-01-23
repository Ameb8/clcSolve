package tokens;

import java.util.Deque;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class UnaryOperator extends Token {
    private String symbol;
    private byte precedence;
    private boolean isLeftAssociative;
    private Function<Double, Double> operation;

    public UnaryOperator(String symbol, byte precedence, boolean isLeftAssociative, Function<Double, Double> operation) {
        this.symbol = symbol;
        this.precedence = precedence;
        this.isLeftAssociative = isLeftAssociative;
        this.operation = operation;
    }
    
    public byte getPrecedence() {
    	return precedence;
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
    public void evaluate(Deque<Double> result) {

        result.push(operation.apply(result.pop()));
    }

}
