package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class CustRetriever {
	
	public int retrieveCust(String custContact, String custPassword) {
		
		int cust_id = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@bankdatabase.cz8yphudm026.us-east-2.rds.amazonaws.com:1521:orcl", "admin", "spicymeatball");
			
			
			PreparedStatement stm = con.prepareStatement("select cust_id from customers where email = ? AND password = ?");
			stm.setString(1, custContact);
			stm.setString(2, custPassword);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				cust_id = rs.getInt(1);
				return cust_id;
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			return 0;
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return cust_id;
	}
	
public int retrieveAge(String custContact) {
		
		int cust_age = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@bankdatabase.cz8yphudm026.us-east-2.rds.amazonaws.com:1521:orcl", "admin", "spicymeatball");
			
			
			PreparedStatement stm = con.prepareStatement("select age from customers where contact = ?");
			stm.setString(1, custContact);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				cust_age = rs.getInt(1);
				return cust_age;
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
		
		return cust_age;
	}
	
public int retrieveSalary(String custContact) {
	
	int salary = 0;
	Connection con = null;
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		con = DriverManager.getConnection("jdbc:oracle:thin:@bankdatabase.cz8yphudm026.us-east-2.rds.amazonaws.com:1521:orcl", "admin", "spicymeatball");
		
		
		PreparedStatement stm = con.prepareStatement("select salary from customers where contact = ?");
		stm.setString(1, custContact);
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			salary = rs.getInt(1);
			return salary;
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
	return salary;
}
}
