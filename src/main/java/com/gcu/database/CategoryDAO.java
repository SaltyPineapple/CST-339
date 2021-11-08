package com.gcu.database;

import com.gcu.data.DataAccessInterface;
import com.gcu.models.CategoryModel;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service
public class CategoryDAO implements DataAccessInterface<CategoryModel>
{
	@Autowired
	private DataSource datasource;
	private JdbcTemplate jdbcTemplate;

	/**
	 * Constructor
	 * @param dataSource Auto injected data source
	 */
	public CategoryDAO(DataSource dataSource)
	{
		this.datasource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	/**
	 * Return a list of all the category in the database
	 * @return a list of CategoryModel 
	 */
	@Override
	public List<CategoryModel> findAll()
	{
		// CategoryModel(int iD, String categoryName)
		
		String sql = "SELECT * FROM CATEGORIES";
		List<CategoryModel> categories = new ArrayList<CategoryModel>();
		try
		{
			SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);
			while(srs.next())
			{
				categories.add(new CategoryModel(srs.getInt("CATEGORY_ID"),
												 srs.getString("CATEGORY_NAME")	));		
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return categories;
	}

	/**
	 * Find a specific category by category id
	 * @param id - id of the category
	 * @return category model or null if not exists
	 */
	@SuppressWarnings("deprecation")
	@Override
	public CategoryModel findById(int id)
	{
		String sql = "SELECT * FROM CATEGORIES WHERE CATEGORY_ID = ?";
		CategoryModel category = null;
		try
		{
			category = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
				    new CategoryModel(rs.getInt("CATEGORY_ID"),
							 		  rs.getString("CATEGORY_NAME")
							 		  ));		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return category;
	}

	/**
	 * Store a category in the database
	 * @param t - a CategoryModel
	 * @return boolean - true if successful, false if not
	 */
	@Override
	public boolean create(CategoryModel t)
	{
		String sql = "INSERT INTO CATEGORIES (CATEGORY_ID, CATEGORY_NAME) VALUES (null, ?)";
		try
		{
			int rows = jdbcTemplate.update(sql, t.getCategoryName());
			return rows == 1 ? true : false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false; // Error occurred - return false
	}

	/**
	 * Update a specific category in the database
	 * @param t - a category model containing info/id to change
	 * @return boolean - true if successful, false if not
	 */
	@Override
	public boolean update(CategoryModel t)
	{
		String sql = "UPDATE CATEGORIES SET CATEGORY_NAME = ? WHERE CATEGORY_ID = ?";
		try {
			int rows = jdbcTemplate.update(sql,
										   t.getCategoryName(),
										   t.getID());
			return rows == 1 ? true : false;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Remove a category from the database
	 * @param id - a string containing the integer id of the category to remove
	 * @return boolean - true if successful, false if not
	 */
	@Override
	public boolean delete(String id)
	{
		String sql = "DELETE FROM CATEGORIES WHERE CATEGORY_ID = ?";		
		try {
			return jdbcTemplate.update(sql, id) >= 1 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

}