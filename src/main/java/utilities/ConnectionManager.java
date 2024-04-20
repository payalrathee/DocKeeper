package utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

	private static Connection con;
	
	public static Connection provideConnecton() {
		
		if(con == null) {
			
			try {
				Class.forName(DBInfo.DB_DRIVER);
				con = DriverManager.getConnection(DBInfo.DB_URL, DBInfo.DB_USER, DBInfo.DB_PASS);
			} catch(Exception e) {
				System.out.println(e);
			}
		}
		
		return con;
	}
	
	public static void closeConnection() {
		try {
			con.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
