package com.gcu.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.database.UserDAO;
import com.gcu.models.UserModel;

public class SecurityBusinessService implements SecurityBusinessServiceInterface {

	@Autowired
	private UserDAO userDAO;
	
	public boolean inputsValid(String username, String password) {
		if (!username.trim().isEmpty() && !password.trim().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean authenticate(String username, String password) {
		return userDAO.verifyUser(username, password);
	}

	@Override
	public void init() {
		System.out.println("SBS Init");
	}

	@Override
	public void destroy() {
		System.out.println("SBS Destroy");
	}

	@Override
	public UserModel findByEmail(String email) {
		return userDAO.findByEmail(email);
	}

}