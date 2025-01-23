package tokens;

import java.util.Deque;
import java.util.List;

public class Operand extends Token {
	private double value;

	public Operand()
	
	@Override
	public void evaluate(Deque<Double> result) {
		result.push(value);
	}

	@Override
	public void toRPN(Deque<Token> operatorStack, List<Token> infixExpression) {
		infixExpression.add(this);
	}

}
