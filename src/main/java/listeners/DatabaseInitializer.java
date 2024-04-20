package listeners;

import java.sql.Connection;
import java.sql.Statement;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import utilities.ConnectionManager;

/**
 * Application Lifecycle Listener implementation class InitDatabase
 *
 */
public class DatabaseInitializer implements ServletContextListener {
	
	private Connection con;

    public DatabaseInitializer() {
        con = ConnectionManager.provideConnecton();
    }

    @Override
	public void contextDestroyed(ServletContextEvent sce) {
    	
		try {
			
			Statement s = con.createStatement();
			
//			s.executeUpdate("DROP TABLE IF EXISTS doc;");
//			s.executeUpdate("DROP TABLE IF EXISTS user;");
			
		} catch(Exception e) {
			ConnectionManager.closeConnection();
			System.out.println(e);
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		try {
					
			Statement s = con.createStatement();
			
//			s.executeUpdate("DROP TABLE IF EXISTS doc;");
//			s.executeUpdate("DROP TABLE IF EXISTS user;");
//			s.executeUpdate("CREATE TABLE user(id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(100) NOT NULL UNIQUE, password VARCHAR(8) NOT NULL, fname VARCHAR(100), lname VARCHAR(100), email VARCHAR(500) UNIQUE, phone VARCHAR(12) UNIQUE, dp VARCHAR(1000) default 'https://t4.ftcdn.net/jpg/05/89/93/27/360_F_589932782_vQAEAZhHnq1QCGu5ikwrYaQD0Mmurm0N.jpg');");
//			s.executeUpdate("CREATE TABLE doc(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), url VARCHAR(500) NOT NULL UNIQUE, note VARCHAR(1000), user_id INT, FOREIGN KEY(user_id) REFERENCES user(id));");
			
		} catch(Exception e) {
			
			System.out.println(e);
		}
	}
	
}
