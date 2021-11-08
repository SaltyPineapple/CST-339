package com.gcu.database;

import com.gcu.data.DataAccessFindListByPostIDInterface;
import com.gcu.data.DataAccessInterface;
import com.gcu.models.CommentModel;
import com.gcu.models.UserModel;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;


@Service
public class CommentDAO implements DataAccessInterface<CommentModel>, DataAccessFindListByPostIDInterface<CommentModel>
{
	@Autowired
	private DataSource datasource;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private DataAccessInterface<UserModel> DAO_User;

	/**
	 * Constructor
	 * @param dataSource Auto injected data source
	 */
	public CommentDAO(DataSource dataSource)
	{
		this.datasource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	/**
	 * Return a list of all comments in the database
	 * @return list of comments
	 */
	@Override
	public List<CommentModel> findAll()
	{
		// CommentModel(int commentID, int commentPostID, UserModel commentBy, Date commentDate, String commentText)
		
		String sql = "SELECT * FROM COMMENTS WHERE COMMENT_DELETED_FLAG = 'N'";
		List<CommentModel> comments = new ArrayList<CommentModel>();
		try
		{
			SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);
			while(srs.next())
			{
				comments.add(new CommentModel(srs.getInt("COMMENT_ID"),
											  srs.getInt("POST_ID"),
											  DAO_User.findById( srs.getInt("COMMENT_BY") ),
											  srs.getDate("COMMENT_DATE"),
											  srs.getString("COMMENT_TEXT") ));		
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return comments;
	}

	/**
	 * Find a specific comment by id
	 * @param id - the comment to find
	 * @return comment model
	 */
	@SuppressWarnings("deprecation")
	@Override
	public CommentModel findById(int id)
	{
		String sql = "SELECT * FROM COMMENTS WHERE COMMENT_DELETED_FLAG = 'N' AND COMMENT_ID = ?";
		CommentModel comment = null;
		try
		{
			comment = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
			new CommentModel(rs.getInt("COMMENT_ID"),
					rs.getInt("POST_ID"),
					DAO_User.findById( rs.getInt("COMMENT_BY") ),
					rs.getDate("COMMENT_DATE"),
					rs.getString("COMMENT_TEXT")
					));		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return comment;
	}

	/**
	 * Store a comment in the database
	 * @param t - a CommentModel to insert
	 * @return boolean - true if successful, false if not
	 */
	@Override
	public boolean create(CommentModel t)
	{
		String sql = "INSERT INTO COMMENTS (COMMENT_ID, POST_ID, COMMENT_TEXT, COMMENT_DATE, COMMENT_BY, COMMENT_DELETED_FLAG) VALUES (null,?,?,?,?,?)";
		try
		{
			int rows = jdbcTemplate.update(sql,
											t.getCommentPostID(),
											t.getCommentText(),
											t.getCommentDate(),
											t.getCommentBy().getUserID(),
											"N");
			return rows == 1 ? true : false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false; // Error occurred - return false
	}

	/**
	 * Update a comment in the database
	 * @param t - the comment model containing the info/id to update
	 * @return boolean - true if successful, false if not
	 */
	@Override
	public boolean update(CommentModel t)
	{
		String sql = "UPDATE COMMENTS SET COMMENT_TEXT = ?, COMMENT_DATE = ?, COMMENT_BY = ? WHERE COMMENT_ID = ?";
		try {
			int rows = jdbcTemplate.update(sql,
										   t.getCommentText(),
										   t.getCommentDate(),
										   t.getCommentBy(),
										   t.getCommentID());
			return rows == 1 ? true : false;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Delete a comment from the database - set delete flag = Y
	 * @param id - integer comment id to delete
	 * @return boolean - true if successful, false if not
	 */
	@Override
	public boolean delete(String id)
	{
		String sql = "UPDATE COMMENTS SET COMMENT_DELETED_FLAG = 'Y' WHERE COMMENT_ID = ?";
		try {
			return jdbcTemplate.update(sql, id) >= 1 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Find a list of comments for a specific post id
	 * @param id - post id holding the comments
	 * @return list of CommentModel 
	 */
	@Override
	public List<CommentModel> findListByPostID(int id)
	{
		String sql = "SELECT * FROM COMMENTS WHERE COMMENT_DELETED_FLAG = 'N' AND POST_ID = ?";		

		try
		{
			return jdbcTemplate.query(sql,
					(rs, rowNum) -> new CommentModel(
							rs.getInt("COMMENT_ID"),
							rs.getInt("POST_ID"),
							DAO_User.findById( rs.getInt("COMMENT_BY") ),
							rs.getDate("COMMENT_DATE"),
							rs.getString("COMMENT_TEXT")),
					new Object[]{id});
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

}