package accounts;

public class BelowMinSalaryException extends Exception {
	public BelowMinSalaryException(String errorMessage) {
		super(errorMessage);
	}
}
