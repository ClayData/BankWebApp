package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddAccount {
	
	public void openAnAccount(String accountType, int custID, int deposit) {
		String accID = "";
		switch(accountType) {
		case "salary_account":
			accID += "salacct_id";
			break;
		case "savings_account":
			accID += "savacct_id";
			break;
		case "current_account":
			accID += "curracct_id";
			break;
		}
		
		
		Connection con = null;
		int id = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Register");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@bankdatabase.cz8yphudm026.us-east-2.rds.amazonaws.com:1521:orcl", "admin", "spicymeatball");
			System.out.println("connection done");
			
			Statement stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery("select MAX(" + accID + ") from " + accountType);
			while(rs.next()) {
				id = rs.getInt(1) + 1;
			}
			
			PreparedStatement pre = con.prepareStatement("insert into " + accountType + " values(?,?,?)");
			
			pre.setInt(1, id);
			pre.setInt(2, deposit);
			pre.setInt(3, custID);
			pre.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	} 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AddAccount aa = new AddAccount();
		aa.openAnAccount("salary_account", 1, 35000);
	}

}
