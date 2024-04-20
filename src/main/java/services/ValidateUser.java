package services;

import java.util.HashMap;

import beans.User;


public class ValidateUser {
	
	public HashMap<String, String> validateUser(User user) {
		HashMap<String, String> errors = new HashMap<String, String>();
		
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
		String phoneRegex = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
		String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$";
		
		
		if(user.getUsername() != null) {
			user.setUsername(user.getUsername().trim());
			if(user.getUsername().isEmpty()) {
				errors.put("username", "Username can't be empty.");
			}
		}
		
		if(user.getEmail() != null) {
			user.setEmail(user.getEmail().trim());
			if(!user.getEmail().matches(emailRegex)) {
				errors.put("email", "Invalid email");
			}
		}
		
		if(user.getPassword() != null) {
			user.setPassword(user.getPassword().trim());
			if(!user.getPassword().matches(passwordRegex)) {
				errors.put("password", "Password must be 8 characters long, must contain an uppercase letter, a lowercase letter, a digit and a special character");
			}
		}
		
		if(user.getFname() != null) {
			user.setFname(user.getFname().trim());
		}
		
		if(user.getLname() != null) {
			user.setLname(user.getLname().trim());
		}
		
		if(user.getPhone() != null) {
			user.setPhone(user.getPhone().trim());
			if(!user.getPhone().matches(phoneRegex)) {
				errors.put("phone", "Invalid phone number");
			}
			
		}
		
		return errors;
	}

}
