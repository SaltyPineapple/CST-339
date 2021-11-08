package com.gcu.database;

import com.gcu.data.DataAccessFindListByPostIDInterface;
import com.gcu.data.DataAccessInterface;
import com.gcu.models.RatingModel;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service
public class RatingDAO implements DataAccessInterface<RatingModel>, DataAccessFindListByPostIDInterface<RatingModel>
{
	@Autowired
	private DataSource datasource;
	private JdbcTemplate jdbcTemplate;
	private UserDAO DAO_User;

	/**
	 * Constructor
	 * @param dataSource Auto injected data source
	 */
	public RatingDAO(DataSource dataSource)
	{
		this.datasource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	/**
	 * Return a list of all the ratings in the database
	 * @return a list of RatingModel 
	 */
	@Override
	public List<RatingModel> findAll()
	{
		//RatingModel(int ratingID, int ratingPostID, UserModel ratedBy, boolean ratingValue) 

		String sql = "SELECT * FROM RATINGS";
		List<RatingModel> ratings = new ArrayList<RatingModel>();
		try
		{
			SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);
			while(srs.next())
			{
				ratings.add(new RatingModel(srs.getInt("RATING_ID"),
											srs.getInt("POST_ID"),
											DAO_User.findById( srs.getInt("RATED_BY") ),
											srs.getBoolean("RATING_VALUE")
						));		
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return ratings;
	}

	/**
	 * Find a specific rating by rating id
	 * @param id - id of the rating
	 * @return rating model or null if not exists
	 */
	@SuppressWarnings("deprecation")
	@Override
	public RatingModel findById(int id)
	{
		String sql = "SELECT * FROM RATINGS WHERE RATING_ID = ?";
		RatingModel rating = null;
		try
		{
			rating = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
			new RatingModel(rs.getInt("RATING_ID"),
							rs.getInt("POST_ID"),
							DAO_User.findById(rs.getInt("RATED_BY") ),
							rs.getBoolean("RATING_VALUE")
					));		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return rating;
	}

	/**
	 * Store a rating in the database
	 * @param t - a RatingModel
	 * @return boolean - true if successful, false if not
	 */
	@Override
	public boolean create(RatingModel t)
	{
		String sql = "INSERT INTO RATINGS (RATING_ID, POST_ID, RATED_BY, RATING_VALUE) VALUES (null, ?, ?, ?)";
		try
		{
			int rows = jdbcTemplate.update(sql,
											t.getRatingPostID(),
											t.getRatedBy().getUserID(),
											t.isRatingValue());
			return rows == 1 ? true : false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false; // Error occurred - return false
	}

	/**
	 * Update a specific rating in the database
	 * @param t - a rating model containing info/id to change
	 * @return boolean - true if successful, false if not
	 */
	@Override
	public boolean update(RatingModel t)
	{
		String sql = "UPDATE RATINGS SET RATED_BY = ?, RATING_VALUE = ? WHERE RATIING_ID = ?";
		try {
			int rows = jdbcTemplate.update(sql,
										   t.getRatedBy(),
										   t.isRatingValue(),
										   t.getRatingID());
			return rows == 1 ? true : false;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Remove a rating from the database
	 * @param id - a string containing the integer id of the rating to remove
	 * @return boolean - true if successful, false if not
	 */
	@Override
	public boolean delete(String id)
	{
		String sql = "DELETE FROM RATINGS WHERE RATING_ID = ?";		
		try {
			return jdbcTemplate.update(sql, id) >= 1 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Find a list of ratings for a specific post id
	 * @param id - post id holding the ratings
	 * @return list of RatingModel 
	 */
	@Override
	public List<RatingModel> findListByPostID(int id)
	{
		String sql = "SELECT * FROM RATINGS WHERE POST_ID = ?";		

		try
		{
			return jdbcTemplate.query(sql,
					(rs, rowNum) -> new RatingModel(
							rs.getInt("RATING_ID"),
							rs.getInt("POST_ID"),
							DAO_User.findById(rs.getInt("RATED_BY") ),
							rs.getBoolean("RATING_VALUE")),
					new Object[]{id});
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

}