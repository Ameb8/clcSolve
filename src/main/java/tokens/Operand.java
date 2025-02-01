package tokens;

import java.util.Deque;
import java.util.List;

public class Operand extends Token {
	private double value;

	public Operand(long tokenId, double value) {
		super(String.valueOf(value), tokenId);
		this.value = value;
	}
	
	public boolean preceedsUnary() {
		return false;
	}
	
	@Override
	public boolean evaluate(Deque<Double> result) {
		result.push(value);
		return true;
	}

	@Override
	public void toRPN(Deque<Token> operatorStack, List<Token> infixExpression) {
		infixExpression.add(this);
	}
	
	@Override
	protected Token copyToken(long newId) {
		return new Operand(newId, value);
	}

}
