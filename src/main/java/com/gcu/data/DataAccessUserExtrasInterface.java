package com.gcu.data;

import com.gcu.models.UserModel;

public interface DataAccessUserExtrasInterface <T>
{
	/**
	 * Method to verify user is valid (in database) by email/password
	 * @param email - user email
	 * @param password - user password
	 * @return boolean - true on successful verification, false otherwise
	 */
	public Boolean verifyUser(String email, String password);
	
	/**
	 * Method to ascertain if user has administrative rights
	 * @param t - generic (user model)
	 * @return boolean - true is admin, false otherwise
	 */
	public Boolean isUserAdmin(T t);
	
	/**
	 * Method to find user model by email (unique/user id)
	 * @param email - user email address
	 * @return UserModel if exists, null otherwise
	 */
	public UserModel findByEmail(String email);
	
	/**
	 * Return user name(first space last) specified by id
	 * @param id - user id (key)
	 * @return string - concatenated first space last
	 */
	public String findNameById(int id);
}