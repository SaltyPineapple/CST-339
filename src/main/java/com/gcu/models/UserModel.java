package com.gcu.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserModel {
	private int ID;

	@NotNull(message="First Name is a required field")
	@Size(min=1, max=16, message="First Name must be between 1 and 16 characters")
	private String firstName;	

	@NotNull(message="Last Name is a required field")
	@Size(min=1, max=16, message="Last Name must be between 1 and 16 characters")
	private String lastName;
	
	@NotNull(message="E-mail is a required field")
	@Size(min=1, max=32, message="E-mail must be between 1 and 32 characters")
	private String email;

	@NotNull(message="Mobile is a required field")
	private String mobile;
	
	@NotNull(message="Password is a required field")
	@Size(min=1, max=32, message="Password must be between 1 and 32 characters")
	private String password;

	@NotNull(message="Birthdate is a required field")
	private String birthdate;

	@NotNull(message="Gender is a required field")
	private boolean gender;
	private int role;
	
	/**
	 * Non-Default Constructor
	 * @param userID - user id (key) 
	 * @param firstName - user first name
	 * @param lastName - user last name
	 * @param email - user email address
	 * @param mobile - user phone
	 * @param password - user password
	 * @param birthdate - user date of birth
	 * @param gender - user gender
	 * @param role - role assigned to user
	 */
	public UserModel(int userID, String firstName, String lastName, String email, String mobile, String password,
			String birthdate, boolean gender, int role) {
		super();
		this.ID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.birthdate = birthdate;
		this.gender = gender;
		this.role = role;
	}
	
	/**
	 * Default Constructor
	 */
	public UserModel() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Get the user id
	 * @return - int id (key)
	 */
	public int getUserID() {
		return ID;
	}
	
	/**
	 * Set the user id - related to database field
	 * @param userID - id (key)
	 */
	public void setUserID(int userID) {
		this.ID = userID;
	}
	
	/**
	 * Get the user first name
	 * @return - string user first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set the user first name
	 * @param firstName - string user first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Get the user last name
	 * @return - string user last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Set the user last name
	 * @param lastName - user last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Get the user email address
	 * @return - string user email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Set the user email address
	 * @param email - user email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Get the user phone number
	 * @return string - phone number
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * Set the user phone number
	 * @param mobile - user phone
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * Get user birth date
	 * @return birth date as string
	 */
	public String getBirthdate() {
		return birthdate;
	}
	
	/**
	 * Set user birth date
	 * @param birthdate - date of birth
	 */
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	
	/**
	 * Get the user gender
	 * @return boolean gender  (Male=0, Female=1)
	 */
	public boolean getGender() {
		return gender;
	}
	
	/**
	 * Set user gender
	 * @param gender - boolean (Male=0, Female=1)
	 */
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	
	/**
	 * Get the user role
	 * @return role - role value assigned to user
	 */
	public int getRole() {
		return role;
	}
	
	/**
	 * Set the user role
	 * @param role - assign a value to user role
	 */
	public void setRole(int role) {
		this.role = role;
	}
	
	/**
	 * Get the user password
	 * @return password string
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * Set the user password
	 * @param password - user password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}