package tokens;

import java.util.Deque;
import java.util.List;

abstract class Token {
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
	 * getter method for token in text form
	 * 
	 * @return symbol
	 */
	public String getSymbol() {
		return symbol;
	}
	
	/**
	 * provides unique identifier for Token object
	 * 
	 * @return tokens unique Id
	 */
	public long getTokenId() {
		return tokenId;
	}
	
	
	/**
	 * creates copy of token object, with new unique tokenId
	 * 
	 * @param other token to be copied
	 * @param tokenId new unique identifier for token
	 * @return deep copy of passed Token
	 */
	public abstract Token copyToken(Token other, long tokenId);
	
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
	 * @param infixExpression Resulting expression in postfix order
	 */
	public abstract void toRPN(Deque<Token> operatorStack, List<Token> infixExpression);
}
