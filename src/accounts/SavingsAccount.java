package accounts;

import java.util.ArrayList;
import java.util.Scanner;
import database.Statement;
import database.AddAccount;
import database.BalanceCheck;
import database.TransferMoney;
import database.AccountDeposit;
import database.WithdrawMoney;

public class SavingsAccount implements Actions{

	private int balance;
	private int minBalance = 1000;
	ArrayList<Integer> statement = new ArrayList<>();
	
	public SavingsAccount() {
		
	};
	
	public SavingsAccount(int initial, int age, int cust_id) throws BelowMinBalanceException, BelowMinAgeException {
		if(initial < minBalance) {
			throw new BelowMinBalanceException("Current Account balance should be $1000");
		}
		if(age < 18) {
			throw new BelowMinAgeException("Must be over 18 years old to open Savings Account");
		}
		AddAccount aa = new AddAccount();
		aa.openAnAccount("savings_account", cust_id, initial);
	}
	
	public int getBalance(int cust_id) {
		BalanceCheck bc = new BalanceCheck();
		balance = bc.checkBalance("current_account", cust_id);
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
		this.statement.add(balance);
	}

	@Override
	public synchronized void transferFunds(int amount, int cust_id, String from, String to) {
		TransferMoney tm = new TransferMoney();
		tm.transferMoney(from, to, amount, cust_id);
		notify();
	}

	@Override
	public synchronized void withdraw(int amount, int cust_id) {
		BalanceCheck bc = new BalanceCheck();
		int checked = bc.checkBalance("savings_account", cust_id);
		if((checked - amount) < minBalance) {
			try {
				System.out.println("Account must be at or above $1000.");
				System.out.println("Currently at " + checked);
				System.out.println("Please add the necessary amount");
				Scanner sc = new Scanner(System.in);
				System.out.println("Which Account would you like to transfer from");
				System.out.println("Press 1 for salary account?");
				System.out.println("Press 2 for current account?");
				int acctnum = sc.nextInt();
				String acct = "";
				if(acctnum == 1) {
					acct = "salary_account";
				} else if (acctnum == 2) {
					acct = "current_account";
				}
				System.out.println("How much to transfer");
				int transfer = sc.nextInt();
				sc.close();
				transferFunds(transfer, cust_id, acct, "savings_account");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
		WithdrawMoney wm = new WithdrawMoney();
		wm.getMoney("savings_account", amount, cust_id);
		balance = checked - amount;
		this.statement.add(balance);
		}
	}

	@Override
	public synchronized void deposit(int amount, int cust_id) {
		AccountDeposit ad = new AccountDeposit();
		ad.depositMoney("savings_account", amount, cust_id);
		balance = balance + amount;
		this.statement.add(balance);
		notify();
	}

	@Override
	public ArrayList<Integer> getStatement(int cust_id) {
		Statement st = new Statement();
		st.retrieveStatement(cust_id, "savings_account");
		return this.statement;
	}

}
