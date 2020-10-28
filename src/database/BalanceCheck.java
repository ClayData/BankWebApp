package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BalanceCheck {
	
	public int checkBalance(String accName, int cust_id) {
		Connection con = null;
		int balance = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@bankdatabase.cz8yphudm026.us-east-2.rds.amazonaws.com:1521:orcl", "admin", "spicymeatball");
			
			Statement stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery("select balance from " + accName + " where cust_id = " + cust_id);
			
			while(rs.next()) {
				balance = rs.getInt(1);
			}
			
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
		return balance;
	}
}
