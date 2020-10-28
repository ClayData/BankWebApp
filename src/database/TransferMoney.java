package database;

public class TransferMoney {

	public void transferMoney(String giving, String receiving, int amount, int custID) {
		WithdrawMoney wm = new WithdrawMoney();
		AccountDeposit ad = new AccountDeposit();
		
		wm.getMoney(giving, amount, custID);
		ad.depositMoney(receiving, amount, custID);
		
	}
	
	public static void main(String[] args) {

	}
}
