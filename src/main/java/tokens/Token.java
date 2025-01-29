package tokens;

import java.util.Deque;
import java.util.List;

public abstract class Token {
	private String symbol;
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
	 * creates copy of token object, with new unique tokenId
	 * 
	 * @param other token to be copied
	 * @param tokenId new unique identifier for token
	 * @return deep copy of passed Token
	 */
//	public abstract Token copyToken(Token other, long tokenId);

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
	public abstract void evaluate(Deque<Double> result); 
	
	/**
	 * handles conversion to post fix expression
	 * 
	 * @param operatorStack Dequeue of operators
	 * @param postfixExpression Resulting expression in post fix order
	 */
	public abstract void toRPN(Deque<Token> operatorStack, List<Token> postfixExpression);
	
}
