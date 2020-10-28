package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Statement {
	
	public void retrieveStatement(int cust_id, String acct_type) {
		Connection con = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@bankdatabase.cz8yphudm026.us-east-2.rds.amazonaws.com:1521:orcl", "admin", "spicymeatball");

			PreparedStatement stm = con.prepareStatement("select * from transactions where account_type = ? and cust_id = ?");
			stm.setString(1, acct_type);
			stm.setInt(2, cust_id);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt(1) + "  " + rs.getInt(2) + "  " + rs.getString(4) + "  " + rs.getString(5));
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
	}
}
