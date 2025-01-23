package tokens;

import java.util.HashMap;

public class TokenBuilder {
	private HashMap<String, Token> TokenRegistry;
	private static long tokenNum;
	
	public TokenBuilder() {
		TokenRegistry = createRegistry();
		tokenNum = 0;
	}
	
	private HashMap<String, Token> createRegistry() {
		TokenRegistry = new HashMap<String, Token>();
		
		
	}
	
	
}
