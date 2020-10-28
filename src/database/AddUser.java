package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddUser {

	
	public void addCustomer(String name, String gender, int salary, String password,String contact, int age) {
		Connection con = null;
		int id = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@bankdatabase.cz8yphudm026.us-east-2.rds.amazonaws.com:1521:orcl", "admin", "spicymeatball");
			
			Statement stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery("select MAX(cust_id) from customers");
			while(rs.next()) {
				id = rs.getInt(1) + 1;
			}
			
			PreparedStatement pre = con.prepareStatement("insert into customers values(?,?,?,?,?,?,?,?,?,?)");
			
			
			pre.setInt(1, id);
			pre.setString(2, name.toLowerCase());
			pre.setString(3, gender.toLowerCase());
			pre.setInt(4, salary);
			pre.setInt(5, age);
			pre.setString(6, password.toLowerCase());
			pre.setString(7, contact.toLowerCase());
			pre.setNull(8, java.sql.Types.INTEGER);
			pre.setNull(9, java.sql.Types.INTEGER);
			pre.setNull(10, java.sql.Types.INTEGER);
			
			pre.executeUpdate();
			
			
			
			while (rs.next()) {
				System.out.println(rs.getInt(1)+ " " + rs.getString(2) + " " + rs.getString(3));
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
