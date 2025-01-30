package tokens;

public interface Operator {
	public byte getPrecedence();
	public boolean isLeftAssociative();
}
