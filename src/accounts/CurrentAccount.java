package accounts;

import java.util.ArrayList;
import java.util.Scanner;

import database.BalanceCheck;
import database.Statement;
import database.AddAccount;
import database.TransferMoney;
import database.AccountDeposit;
import database.WithdrawMoney;

public class CurrentAccount implements Actions {

	private int balance;
	private int minBalance = 1500;
	ArrayList<Integer> statement = new ArrayList<>();
	
	public CurrentAccount() {
		
	}
	
	public CurrentAccount(int initial, int salary, int age, int cust_id) throws BelowMinBalanceException, BelowMinAgeException, BelowMinSalaryException {
		if(initial < minBalance) {
			throw new BelowMinBalanceException("Current Account balance should be $1500");
		}
		if(salary < 5000) {
			throw new BelowMinSalaryException("Salary is below the acceptable threshold");
		}
		if(age < 25) {
			throw new BelowMinAgeException("Must be over 25 years old to open Savings Account");
		}
		AddAccount aa = new AddAccount();
		aa.openAnAccount("current_account", cust_id, initial);
	}
	
	public int getBalance(int cust_id) {
		BalanceCheck bc = new BalanceCheck();
		balance = bc.checkBalance("current_account", cust_id);
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public synchronized void transferFunds(int amount, int cust_id,  String from, String to) {
		// TODO Auto-generated method stub
		TransferMoney tm = new TransferMoney();
		tm.transferMoney(from, to, amount, cust_id);
		notify();
	}

	@Override
	public synchronized void withdraw(int amount, int cust_id) {
		BalanceCheck bc = new BalanceCheck();
		int checked = bc.checkBalance("current_account", cust_id);
		if((checked - amount) < minBalance) {
			try {
				System.out.println("Account must be at or above $1500.");
				System.out.println("Currently at " + checked);
				System.out.println("Please add the necessary amount");
				Scanner sc = new Scanner(System.in);
				System.out.println("Which Account would you like to transfer from");
				System.out.println("Press 1 for savings account?");
				System.out.println("Press 2 for salary account?");
				int acctnum = sc.nextInt();
				String acct = "";
				if(acctnum == 1) {
					acct = "savings_account";
				} else if (acctnum == 2) {
					acct = "salary_account";
				}
				System.out.println("How much to transfer");
				int transfer = sc.nextInt();
				sc.close();
				transferFunds(transfer, cust_id, acct, "current_account");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
		WithdrawMoney wm = new WithdrawMoney();
		wm.getMoney("current_account", amount, cust_id);
		balance = balance - amount;
		this.statement.add(balance);
		}
	}
	
	@Override
	public synchronized void deposit(int amount, int cust_id) {
		AccountDeposit ad = new AccountDeposit();
		ad.depositMoney("current_account", amount, cust_id);
		balance = balance + amount;
		this.statement.add(balance);
		notify();
	}

	@Override
	public ArrayList<Integer> getStatement(int cust_id) {
		Statement st = new Statement();
		st.retrieveStatement(cust_id, "current_account");
		return this.statement;
	}
	
}
