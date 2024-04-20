package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Doc;
import beans.User;
import utilities.ConnectionManager;

public class DocDao {
	Connection con;
	
	public DocDao() {
		con = ConnectionManager.provideConnecton();
	}
	
	public Doc getDoc(int id) {
		Doc doc = null;
		
		try {
			PreparedStatement p = con.prepareStatement("select * from doc where id = ?;");
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			
			if(rs.next()) {
				doc = new Doc(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		ConnectionManager.closeConnection();
		return doc;
	}
	
	public ArrayList<Doc> getAllDOcs(int userId) {
		ArrayList<Doc> docs = null;
		try {
			PreparedStatement p = con.prepareStatement("select * from doc where user_id = ?");
			p.setInt(1, userId);
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				docs.add(new Doc(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		ConnectionManager.closeConnection();
		return docs;
	}
	
	public boolean updateDoc(Doc doc) {
		boolean isUpdated = false;
		
		try {
			PreparedStatement p = con.prepareStatement("update doc set name =?, url=?, note=?, user_id=? where id=?");
			p.setString(1, doc.getName());
			p.setString(2, doc.getUrl());
			p.setString(3, doc.getNote());
			p.setInt(4, doc.getUserId());
			p.setInt(5, doc.getId());
			
			int r = p.executeUpdate();
			
			if(r>0) {
				isUpdated = true;
			}
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		ConnectionManager.closeConnection();
		return isUpdated;
	}
	
	public int insertDoc(Doc doc) {
		int id = -1;
		
		try {
			PreparedStatement p = con.prepareStatement("insert into doc values(null, ?,?,?,?);");
			p.setString(1, doc.getName());
			p.setString(2, doc.getUrl());
			p.setString(3, doc.getName());
			p.setInt(4, doc.getUserId());
			
			int r = p.executeUpdate();
			if(r > 0) {
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery("select max(id) from doc");
				if(rs.next()) {
					id = rs.getInt(1);
				}
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		ConnectionManager.closeConnection();
		return id;
	}
	
	public boolean deleteDoc(int id) {
		boolean isDeleted = false;
		try {
			PreparedStatement p = con.prepareStatement("delete from doc where id = ?");
			p.setInt(1, id);
			int r = p.executeUpdate();
			if(r>0) {
				isDeleted = true;
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		ConnectionManager.closeConnection();
		return isDeleted;
	}
}
