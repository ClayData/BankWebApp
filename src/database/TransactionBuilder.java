package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionBuilder {
	
	public void addTransaction(int amount, int cust_id, String tran_type, String acct_type) {
		Connection con = null;
		int id = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@bankdatabase.cz8yphudm026.us-east-2.rds.amazonaws.com:1521:orcl", "admin", "spicymeatball");
			
			PreparedStatement stm = con.prepareStatement("select max(transact_id) from transactions");
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				
				id = rs.getInt(1) + 1;
			}
			
			PreparedStatement pre = con.prepareStatement("insert into transactions values(?,?,?,?,?)");
			pre.setInt(1, id);
			pre.setInt(2, amount);
			pre.setInt(3, cust_id);
			pre.setString(4, tran_type.toLowerCase());
			pre.setString(5, acct_type.toLowerCase());
			pre.executeUpdate();
			
			
			
		}  catch (ClassNotFoundException e) {
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
