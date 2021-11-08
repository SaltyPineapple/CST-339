package com.gcu.database;

import com.gcu.data.DataAccessInterface;
import com.gcu.data.DataAccessUserExtrasInterface;
import com.gcu.models.UserModel;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service
public class UserDAO implements DataAccessInterface<UserModel>, DataAccessUserExtrasInterface<UserModel>
{
	@Autowired
	private DataSource datasource;
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * Constructor
	 * @param dataSource Auto injected data source
	 */
	public UserDAO(DataSource dataSource)
	{
		this.datasource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	/**
	 * Find all the users in the database
	 * @return a list of UserModel
	 */
	@Override
	public List<UserModel> findAll()
	{
		String sql = "SELECT * FROM USERS";
		List<UserModel> users = new ArrayList<UserModel>();
		try
		{
			SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);
			while(srs.next())
			{
				users.add(new UserModel(srs.getInt("USER_ID"),
										 srs.getString("USER_FIRST_NAME"),
										 srs.getString("USER_LAST_NAME"),
										 srs.getString("USER_EMAIL"),
										 srs.getString("USER_MOBILE"),
										 srs.getString("USER_PASSWORD"),
										 srs.getString("USER_BIRTHDATE"),
										 srs.getBoolean("USER_GENDER"),
										 srs.getInt("USER_ROLE_ID")));		
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return users;
	}

	/**
	 * Find a UserModel with a specific user id
	 * @param id integer value for the user id
	 * @return a filled user model or null if not exists
	 */
	@SuppressWarnings("deprecation")
	@Override
	public UserModel findById(int id)
	{
		String sql = "SELECT * FROM USERS WHERE USER_ID = ?";
		UserModel user = new UserModel();
		try
		{
			user = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
				    new UserModel(rs.getInt("USER_ID"),
								  rs.getString("USER_FIRST_NAME"),
								  rs.getString("USER_LAST_NAME"),
								  rs.getString("USER_EMAIL"),
								  rs.getString("USER_MOBILE"),
								  rs.getString("USER_PASSWORD"),
								  rs.getString("USER_BIRTHDATE"),
								  rs.getBoolean("USER_GENDER"),
								  rs.getInt("USER_ROLE_ID")));		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * Find a specific user by email address
	 * @param email to look up user
	 * @return a filled user model or null if not exists
	 */
	@SuppressWarnings("deprecation")
	@Override
	public UserModel findByEmail(String email)
	{
		String sql = "SELECT * FROM USERS WHERE USER_EMAIL = ?";
		UserModel user = new UserModel();
		try
		{
			user = jdbcTemplate.queryForObject(sql, new Object[]{email}, (rs, rowNum) ->
				    new UserModel(rs.getInt("USER_ID"),
								  rs.getString("USER_FIRST_NAME"),
								  rs.getString("USER_LAST_NAME"),
								  rs.getString("USER_EMAIL"),
								  rs.getString("USER_MOBILE"),
								  rs.getString("USER_PASSWORD"),
								  rs.getString("USER_BIRTHDATE"),
								  rs.getBoolean("USER_GENDER"),
								  rs.getInt("USER_ROLE_ID")));		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * Find user name (first, last) by user id
	 * @param id - an integer with the value to look up
	 * @return a string concatenating first, space, last
	 */
	@SuppressWarnings("deprecation")
	@Override
	public String findNameById(int id)
	{
		String sql = "SELECT USER_FIRST_NAME, USER_LAST_NAME FROM USERS WHERE USER_ID = ?";
		String name = "";
		try {
			name = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
				    new String(rs.getString("USER_FIRST_NAME") + " " + rs.getString("USER_LAST_NAME")));		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	/**
	 * Store a user in the database
	 * @param t - a UserModel containing the information to store
	 * @return boolean - true if successful, false if not
	 */
	@Override
	public boolean create(UserModel t) {
		String sql = "INSERT INTO USERS(USER_ID, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_MOBILE, USER_PASSWORD, USER_BIRTHDATE, USER_GENDER, USER_ROLE_ID) VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			int rows = jdbcTemplate.update(sql,
										   t.getFirstName(),
										   t.getLastName(),
										   t.getEmail(),
										   t.getMobile(),
										   t.getPassword(),
										   t.getBirthdate(),
										   t.getGender(),
										   t.getRole());
			return rows == 1 ? true : false;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Update a user record in the database
	 * @param t a user model containing info/id to update
	 * @return boolean - true if successful, false if not
	 */
	@Override
	public boolean update(UserModel t)
	{
		String sql = "UPDATE USERS SET USER_FIRST_NAME = ?, USER_LAST_NAME = ?, USER_EMAIL = ?, USER_MOBILE = ?, USER_PASSWORD = ?, USER_BIRTHDATE = ?, USER_GENDER = ?, USER_ROLE_ID = ? WHERE USER_ID = ?";
		try {
			int rows = jdbcTemplate.update(sql,
										   t.getFirstName(),
										   t.getLastName(),
										   t.getEmail(),
										   t.getMobile(),
										   t.getPassword(),
										   t.getBirthdate(),
										   t.getGender(),
										   t.getRole(),
										   t.getUserID());
			return rows == 1 ? true : false;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Delete a user from the database
	 * @param id - a string containing the integer of the user id to remove
	 * @return boolean - true if successful, false if not
	 */
	@Override
	public boolean delete(String id)
	{
		String sql = "DELETE FROM USERS WHERE USER_ID = ?";		
		try {
			return jdbcTemplate.update(sql, id) >= 1 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Verify that a user exists
	 * @param email - string containing email address
	 * @param password - string containing password
	 * @return boolean - true if exists, false if not
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Boolean verifyUser(String email, String password)
	{
		int results = 0;
		
		String sql = "SELECT COUNT(*) FROM USERS WHERE USER_EMAIL = ? AND USER_PASSWORD = ?";
		try
		{
			results = jdbcTemplate.queryForObject(sql, new Object[]{email, password}, Integer.class);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		if (results == 0)
		{
			System.out.printf("User does not have an account: Email=%s, Password=%s\n", email, password);
			return false;
		}
		else
		{
			System.out.printf("User has a valid account: Email=%s, Password=%s\n", email, password);
			return true;			
		}
	}

	/**
	 * Determine if a specific user has administrator rights
	 * @param user a UserModel with the information to test
	 * @return boolean - true if administrator, false if not
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Boolean isUserAdmin(UserModel user)
	{
		int results = -1;
		
		// Make assumption - if user id is zero, check database on email/password
		// Otherwise, assume model is populated and only check user role in model
		// Admin = 0, User = 1
		if (user.getUserID() > 0)
		{
			return (user.getRole() == 0);
		}
		
		String sql = "SELECT USER_ROLE_ID FROM USERS WHERE USER_EMAIL = ? AND USER_PASSWORD = ?";
		try
		{
			results = jdbcTemplate.queryForObject(sql, new Object[]{user.getEmail(), user.getPassword()}, Integer.class);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		if (results == 0)
		{
			System.out.printf("User is an administrator: Email=%s, Password=%s\n",user.getEmail(), user.getPassword());
			return true;
		}
		else
		{
			System.out.printf("User is not an administrator: Email=%s, Password=%s\n",user.getEmail(), user.getPassword());
			return false;
			
		}
	}

}