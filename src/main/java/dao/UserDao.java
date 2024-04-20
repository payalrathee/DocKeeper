package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import beans.User;
import utilities.ConnectionManager;

public class UserDao {

	private Connection con;
	
	public UserDao() {
		con = ConnectionManager.provideConnecton();
	}
	
	public User getUser(int id) {
		User user = null;
		try {
			PreparedStatement p = con.prepareStatement("select * from user where id = ?;");
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			
			if(rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
			}
			
		} catch (Exception e) {
			
			System.out.println(e);
		}
		
		return user;
	}
	
	public int insertUser(User user) {
		int id = -1;
		try {
			PreparedStatement p = con.prepareStatement("insert into user values(null, ?,?,?,?,?,?,?);");
		
			p.setString(1, user.getUsername());
			p.setString(2, user.getPassword());
			p.setString(3, user.getFname());
			p.setString(4, user.getLname());
			p.setString(5, user.getEmail());
			p.setString(6,user.getPhone());
			p.setString(7, user.getDp());
			
			int r = p.executeUpdate();
			
			if(r > 0) {
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery("select max(id) from user");
				if(rs.next()) {
					id = rs.getInt(1);
				}
			}
			
		} catch (Exception e) {
			
			System.out.println(e);
		}
		
		return id;
	}
	
	public boolean updateUser(User user) {
		boolean updated = false;
		try {
			
			PreparedStatement p = con.prepareStatement("update user set username=?, fname=?, lname=?, email=?, phone=? dp=? where id=?");
			p.setString(1, user.getUsername());
			p.setString(2, user.getFname());
			p.setString(3, user.getLname());
			p.setString(4, user.getEmail());
			p.setString(5,user.getPhone());
			p.setString(6, user.getDp());
			p.setInt(7, user.getId());
			
			int r = p.executeUpdate();
			
			if(r>0) {
				updated = true;
			}
			
		} catch(Exception e) {
			
			System.out.println(e);
		}
		
		return updated;
	}
	
	public boolean deleteUser(int id) {
		boolean deleted = false;
		
		try {
			
			PreparedStatement p = con.prepareStatement("delete from user where id=?");
			p.setInt(1, id);
			
			int r = p.executeUpdate();
			
			if(r>0) {
				deleted = true;
			}
			
		} catch(Exception e) {
			
			System.out.println(e);
		}
		
		return deleted;
	}
	
	public User getUserByUsername(String username) {
		User user = null;
		
		try {
			
			PreparedStatement p = con.prepareStatement("select * from user where username=?");
			p.setString(1, username);
			
			ResultSet rs = p.executeQuery();
			
			if(rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
			}
			
		} catch(Exception e) {
			
			System.out.println(e);
		}
		
		return user;
	}
	
	public User getUserByEmail(String email) {
		User user = null;
		
		try {
			
			PreparedStatement p = con.prepareStatement("select * from user where email=?");
			p.setString(1, email);
			
			ResultSet rs = p.executeQuery();
			if(rs.next() ) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
			}
			
		} catch(Exception e) {
			
			System.out.println(e);
		}
		
		return user;
	}
	
}
