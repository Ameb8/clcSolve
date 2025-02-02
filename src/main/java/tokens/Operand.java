package tokens;

import java.util.Deque;
import java.util.List;

public class Operand extends Token {
	private double value;

	public Operand(long tokenId, double value) {
		super(String.valueOf(value), tokenId);
		this.value = value;
	}
	
	@Override
	public boolean preceedsUnary() {
		return false;
	}
	
	@Override
	public boolean evaluate(Deque<Double> result) {
		result.push(value);
		return true;
	}

	@Override
	public boolean toRPN(Deque<Token> operatorStack, List<Token> infixExpression) {
		infixExpression.add(this);
		return true;
	}
	
	@Override
	protected Token copyToken(long newId) {
		return new Operand(newId, value);
	}

}
