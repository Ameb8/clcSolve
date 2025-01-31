package errors;

public class Error {
	private String message;
	private long tokenId;
	
	public Error(String message, long tokenId) {
		this.message = message;
		this.tokenId = tokenId;
	}
	
	@Override
	public String toString() {
		return message;
	}
	
	
}
