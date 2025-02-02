package tokens;

import java.util.Deque;
import java.util.List;

public class InvalidInput extends Token {
	public InvalidInput(String symbol, long tokenId) {
		super(symbol, tokenId);
	}
	
	@Override
	public boolean preceedsUnary() {
		return false;
	}

	@Override
	public boolean evaluate(Deque<Double> result) { return true;}

	@Override
	public boolean toRPN(Deque<Token> operatorStack, List<Token> postfixExpression) { return true;}
	
	@Override
	protected Token copyToken(long newId) {
		return new InvalidInput(symbol, newId);
	}
	
	
}
