package accounts;

public class BelowMinAgeException extends Exception{

	public BelowMinAgeException(String errorMessage) {
		super(errorMessage);
	}
}
