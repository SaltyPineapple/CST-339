package com.gcu.models;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostModel {
	private int ID;

	@NotNull(message="Blog Title is a required field")
	@Size(min=1, max=100, message="Blog title must be between 1 and 100 characters")
	private String title;
	
	@NotNull(message="Blog Content is a required field")
	@Size(min=1, max=65535, message="Blog content must be between 1 and 65535 characters")
	private String content;

	private CategoryModel category;
	private Date date;
	private int authorID;
	private String authorName;
	private Date updatedDate;
	private int updatedBy;
	private List<RatingModel> ratingScore;

	@Size(max=128, message="Keywords cannot exceed 128 characters")
	private String keywords;
	private List<CommentModel> comments;
	
	/**
	 * Non-Default Constructor
	 * @param iD - record key identifier
	 * @param title - title of post 
	 * @param content - content of post
	 * @param category - category the post belongs
	 * @param date - creation date
	 * @param authorID - foreign key id to user
	 * @param authorName - author name that created post
	 * @param updatedDate - most recent modification date
	 * @param updatedBy - most recent post modifier user id
	 * @param ratingScore - list of ratings associated with post
	 * @param keywords - string of keywords (#) 
	 * @param comments - list of comments users made regarding post
	 */
	public PostModel(int iD, String title, String content, CategoryModel category, Date date, int authorID, String authorName, Date updatedDate,
			int updatedBy, List<RatingModel> ratingScore, String keywords, List<CommentModel> comments) {
		super();
		ID = iD;
		this.title = title;
		this.content = content;
		this.category = category;
		this.date = date;
		this.authorID = authorID;
		this.authorName = authorName;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.ratingScore = ratingScore;
		this.keywords = keywords;
		this.comments = comments;
	}
	
	/**
	 * Non-Default Constructor (Blank)
	 * @param input - assigned to user id (typically -1)
	 */
	public PostModel(int input) {
		super();
		this.ID = input;
		this.title = "";
		this.content = "";
		this.category = null;
		this.date = null;
		this.authorID = -1;
		this.authorName = "";
		this.updatedDate = null;
		this.updatedBy = -1;
		this.ratingScore = null;
		this.keywords = "";
		this.comments = null;
	}

	/**
	 * Get the post author name
	 * @return author name
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * Set the author name of the post
	 * @param authorName - name of the post creation author
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * Default Constructor
	 */
	public PostModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Get the Post ID
	 * @return ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Set the Post ID
	 * @param iD - post id (key)
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * Get the title of the post
	 * @return post title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the title of the post
	 * @param title - title of the post
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the post category
	 * @return Category model
	 */
	public CategoryModel getCategory() {
		return category;
	}

	/**
	 * Set the post category
	 * @param category - category that post belongs
	 */
	public void setCategory(CategoryModel category) {
		this.category = category;
	}

	/**
	 * Get the creation date of the post
	 * @return creation date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Set the creation date for the post
	 * @param date - creation date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Get the post author id
	 * @return author id (user)
	 */
	public int getAuthorID() {
		return authorID;
	}

	/**
	 * Set the post author id
	 * @param author - id of the user
	 */
	public void setAuthorID(int author) {
		this.authorID = author;
	}

	/**
	 * Get the latest post modification date
	 * @return last updated date
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * Set the date at modification
	 * @param updatedDate - last post modification date
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * Get the user id for the latest post update
	 * @return author / user id
	 */
	public int getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * Set the post updated author / user id
	 * @param updatedBy - updating author id
	 */
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * Get a list of Rating Models users assigned to this post
	 * @return list of ratings
	 */
	public List<RatingModel> getRatingScore() {
		return ratingScore;
	}

	/**
	 * Set the list of ratings for this post
	 * @param ratingScore - list of RatingModel
	 */
	public void setRatingScore(List<RatingModel> ratingScore) {
		this.ratingScore = ratingScore;
	}

	/**
	 * Get the keywords for the post
	 * @return keywords
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * Set the keywords for the post
	 * @param keywords - string of keywords (#)
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * Get the list of comments for this post
	 * @return list of CommentModel
	 */
	public List<CommentModel> getComments() {
		return comments;
	}

	/**
	 * Set the list of comments
	 * @param comments - list of comments from users about post
	 */
	public void setComments(List<CommentModel> comments) {
		this.comments = comments;
	}

	/**
	 * Get the Post Content
	 * @return content of the post
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Set the content for the post
	 * @param content - text (string) for content
	 */
	public void setContent(String content) {
		this.content = content;
	}
}