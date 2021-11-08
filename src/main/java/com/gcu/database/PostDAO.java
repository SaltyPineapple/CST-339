package com.gcu.database;

import com.gcu.data.DataAccessFindListByPostIDInterface;
import com.gcu.data.DataAccessInterface;
import com.gcu.data.DataAccessPostExtrasInterface;
import com.gcu.data.DataAccessUserExtrasInterface;
import com.gcu.models.CategoryModel;
import com.gcu.models.CommentModel;
import com.gcu.models.PostModel;
import com.gcu.models.RatingModel;
import com.gcu.models.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service
public class PostDAO implements DataAccessInterface<PostModel>, DataAccessPostExtrasInterface<PostModel>
{
	@Autowired
	private DataSource datasource;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DataAccessFindListByPostIDInterface<RatingModel> DAO_Rating;
	
	@Autowired
	private DataAccessFindListByPostIDInterface<CommentModel> DAO_Comment;
	
	@Autowired
	private DataAccessInterface<CategoryModel> DAO_Category;

	@Autowired
	private DataAccessUserExtrasInterface<UserModel> DAO_UserExtra;
    
	/**
	 * Constructor
	 * @param dataSource Auto injected data source
	 */
	public PostDAO(DataSource dataSource)
	{
		this.datasource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	/**
	 * Calculates post rating percentage for post id
	 * @param id - which post to analyze
	 * @return double - calculated average
	 */
	@Override
	public Double calculateRatingPercentage(int id)
	{
		return 5.0D; // Everyone rated this a 5-star :)
	}
	
	/**
	 * Find all posts made by everyone
	 * @return List all posts made
	 */
	@Override
	public List<PostModel> findAll() {
		String sql = "SELECT * FROM POSTS WHERE POST_DELETED_FLAG = 'N' AND POST_PRIVATE_FLAG = 'N' ";
		List<PostModel> posts = new ArrayList<PostModel>();
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
		
		try {
			SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);
			while(srs.next()) {
				//System.out.println(srs.getObject("POST_DATE").toString() + "!   --  " + format.parse(srs.getObject("POST_DATE").toString()) );
				posts.add(new PostModel(
						srs.getInt("POST_ID"),
						srs.getString("POST_TITLE"),
						srs.getString("POST_CONTENT"),
						DAO_Category.findById( srs.getInt("CATEGORY_ID") ),
						format.parse(srs.getObject("POST_DATE").toString()),
						srs.getInt("POST_AUTHOR"),
						DAO_UserExtra.findNameById( srs.getInt("POST_AUTHOR") ),
						format.parse(srs.getObject("POST_UPDATED_DATE").toString()),
						srs.getInt("POST_UPDATED_BY"),
						DAO_Rating.findListByPostID( srs.getInt("POST_ID") ),
						//null,
						srs.getString("POST_KEYWORDS"),
						DAO_Comment.findListByPostID( srs.getInt("POST_ID") )
						//null
						));	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return posts;
	}
	/**
	 * Find all posts created by user with user id (id)
	 * @param id - users id number
	 * @return List of posts
	 */
	@Override
	public List<PostModel> findAllByUserId(int id) {
		String sql = "SELECT * FROM POSTS WHERE POST_DELETED_FLAG = 'N' AND POST_AUTHOR = ?";		
		
		try {
			return jdbcTemplate.query(
	        sql, 
	        (rs, rowNum) -> new PostModel(
					rs.getInt("POST_ID"),
					rs.getString("POST_TITLE"),
					rs.getString("POST_CONTENT"),
					DAO_Category.findById( rs.getInt("CATEGORY_ID") ),
					rs.getDate("POST_DATE"),
					rs.getInt("POST_AUTHOR"),
					DAO_UserExtra.findNameById( rs.getInt("POST_AUTHOR") ),
					rs.getDate("POST_UPDATED_DATE"),
					rs.getInt("POST_UPDATED_BY"),
					DAO_Rating.findListByPostID( rs.getInt("POST_ID") ),
					//null,
					rs.getString("POST_KEYWORDS"),
					DAO_Comment.findListByPostID( rs.getInt("POST_ID") )
					//null
					),
			        new Object[]{id});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Find and return a specific post by id
	 * @param id int - post id
	 * @return PostModel valid post or null if not exist
	 */
	@SuppressWarnings("deprecation")
	@Override
	public PostModel findById(int id)
	{
		PostModel post = null;
		int results = 0;
		
		//
		// Add a check to confirm post exists before trying to call various lookups below
		//
		String sql = "SELECT COUNT(*) FROM POSTS WHERE POST_DELETED_FLAG = 'N' AND POST_ID = ?";
		try
		{
			results = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		if (results > 0)
		{
			sql = "SELECT * FROM POSTS WHERE POST_DELETED_FLAG = 'N' AND POST_ID = ?";
			try
			{
				post = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
					    new PostModel(rs.getInt("POST_ID"),
								rs.getString("POST_TITLE"),
								rs.getString("POST_CONTENT"),
								DAO_Category.findById( rs.getInt("CATEGORY_ID") ),
								rs.getDate("POST_DATE"),
								rs.getInt("POST_AUTHOR"),
								DAO_UserExtra.findNameById( rs.getInt("POST_AUTHOR") ),
								rs.getDate("POST_UPDATED_DATE"),
								rs.getInt("POST_UPDATED_BY"),
								DAO_Rating.findListByPostID( rs.getInt("POST_ID") ),
								//null,
								rs.getString("POST_KEYWORDS"),
	
								DAO_Comment.findListByPostID( rs.getInt("POST_ID") )
								//null
								));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		return post;
	}

	/**
	 * Store a post in the database
	 * @param t (model) to store in the database
	 * @return boolean true if successful, false if not
	 */
	@Override
	public boolean create(PostModel t)
	{
		String sql = "INSERT INTO POSTS(POST_ID, POST_TITLE, CATEGORY_ID, POST_CONTENT, POST_DATE, POST_AUTHOR, POST_UPDATED_DATE, POST_UPDATED_BY, POST_DELETED_FLAG, POST_KEYWORDS, POST_PRIVATE_FLAG) VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			int rows = jdbcTemplate.update(sql,
										   t.getTitle(),
										   t.getCategory().getID(),
										   t.getContent(),
										   t.getDate(),
										   t.getAuthorID(),
										   t.getUpdatedDate(),
										   t.getUpdatedBy(),
										   "N",
										   t.getKeywords(),
										   "N");
			return rows == 1 ? true : false;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Update a post in the database
	 * @param t (model) to update in the database - uses post id
	 * @return boolean true if successful, false if not
	 */
	@Override
	public boolean update(PostModel t)
	{
		String sql = "UPDATE POSTS SET POST_TITLE = ?, CATEGORY_ID = ?, POST_CONTENT = ?, POST_UPDATED_DATE = ?, POST_UPDATED_BY = ?, POST_KEYWORDS = ? WHERE POST_ID = ?";
		try {
			int rows = jdbcTemplate.update(sql,
										   t.getTitle(),
										   t.getCategory().getID(),
										   t.getContent(),
										   t.getUpdatedDate(),
										   t.getUpdatedBy(),
										   t.getKeywords(),
										   t.getID());
			return rows == 1 ? true : false;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Delete a post from the database
	 * @param id - which post to delete
	 * @return boolean true if successful, false if not 
	 */
	@Override
	public boolean delete(String id)
	{
		String sql = "UPDATE POSTS SET POST_DELETED_FLAG = 'Y' WHERE POST_ID = ?";
		try {
			return jdbcTemplate.update(sql, id) >= 1 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}