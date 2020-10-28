package accounts;

public class BelowMinBalanceException extends Exception{
	
	public BelowMinBalanceException(String errorMessage) {
		super(errorMessage);
	}
}
