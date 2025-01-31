package tokens;

import java.util.Deque;
import java.util.List;

public class InvalidInput extends Token {
	public InvalidInput(String symbol, long tokenId) {
		super(symbol, tokenId);
	}
	
	public boolean preceedsUnary() {
		return false;
	}

	@Override
	public void evaluate(Deque<Double> result) { }

	@Override
	public void toRPN(Deque<Token> operatorStack, List<Token> postfixExpression) { }
	
	@Override
	protected Token copyToken(long newId) {
		return new InvalidInput(symbol, newId);
	}
	
	
}
