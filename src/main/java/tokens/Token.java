package tokens;

import java.util.Deque;
import java.util.List;

public abstract class Token {
	protected String symbol;
	private long tokenId;
	
	/**
	 * constructor for Token abstract superclass
	 * 
	 * @param symbol used to display token
	 * @param tokenId unique identifier
	 */
	public Token(String symbol, long tokenId) {
		this.symbol = symbol;
		this.tokenId = tokenId;
	}
	
	public static Token copyNewToken(Token token, long newId) {
		return token.copyToken(newId);
	}
	
	/**
	 * creates copy of token object, with new unique tokenId
	 * 
	 * @param tokenId new unique identifier for token
	 * @return deep copy of passed Token
	 */
	protected abstract Token copyToken(long tokenId);
	
	/**
	 * provides unique identifier for Token object
	 * 
	 * @return tokens unique Id
	 */
	public long getTokenId() {
		return tokenId;
	}
	
	public void setTokenId(long id) {
		this.tokenId = id;
	}
	
	public String toString() {
		return symbol  + " ";
	}
	
	/**
	 * determines if operator following this token must be unary
	 * 
	 * @return true if following operator must be unary
	 */
	public abstract boolean preceedsUnary();
	
	
	/**
	 * handles evaluation of this token
	 * 
	 * @param stack current result of expression
	 */
	public abstract boolean evaluate(Deque<Double> result); 
	
	/**
	 * handles conversion to post fix expression
	 * 
	 * @param operatorStack Dequeue of operators
	 * @param postfixExpression Resulting expression in post fix order
	 */
	public abstract boolean toRPN(Deque<Token> operatorStack, List<Token> postfixExpression);
	
	@Override
	public boolean equals(Object other) {
		if(other == null || getClass() != other.getClass())
			return false;
		
		return tokenId == ((Token) other).getTokenId();
	}
	
	@Override
	public int hashCode() {
		return Long.hashCode(tokenId);
	}
	
}
