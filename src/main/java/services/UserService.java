package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.User;
import dao.UserDao;
import utilities.ConnectionManager;

public class UserService {
	
	private UserDao userDao;
	
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public User validate(String login, String password) throws Exception {
		User user = null;
			
		user = userDao.getUserByUsername(login);
		
		if(user == null) {
			user = userDao.getUserByEmail(login);
		}
		
		if(user == null) {
			throw new Exception("UserNotFound");
		}
		
		if(!user.getPassword().equals(password)) {
			throw new Exception("InvalidPassword");
		}
			
		return user;
	}
	
	public User addUser(User user) throws Exception {
		
		if(userDao.getUserByUsername(user.getUsername()) != null) {
			throw new Exception("UsernameAlreadyExists");
		}
		
		if(userDao.getUserByEmail(user.getEmail()) != null) {
			throw new Exception("EmailAlreadyExists");
		}
		
		int id = userDao.insertUser(user);
		
		if(id < 0) {
			throw new Exception("UserNotInserted");
		}
		
		user = userDao.getUser(id);
		return user;
	}

}
