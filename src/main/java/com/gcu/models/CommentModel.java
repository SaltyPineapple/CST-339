package com.gcu.models;

import java.util.Date;

import javax.validation.constraints.Size;

public class CommentModel {
	private int ID;
	private int commentPostID;
	private UserModel commentBy;
	private Date commentDate;

	@Size(min=0, max=128, message="Comment cannot exceed 500 characters")
	private String commentText;
	
	/**
	 * Non-Default Constructor
	 * @param commentID - id (key) 
	 * @param commentPostID - foreign key post id
	 * @param commentBy - foreign key user model
	 * @param commentDate - date of comment
	 * @param commentText - text / content of comment
	 */
	public CommentModel(int commentID, int commentPostID, UserModel commentBy, Date commentDate, String commentText) {
		super();
		this.ID = commentID;
		this.commentPostID = commentPostID;
		this.commentBy = commentBy;
		this.commentDate = commentDate;
		this.commentText = commentText;
	}

	/**
	 * Default Constructor
	 */
	public CommentModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Get the comment id
	 * @return int comment id
	 */
	public int getCommentID() {
		return ID;
	}

	/**
	 * Set the comment id
	 * @param commentID - id (key)
	 */
	public void setCommentID(int commentID) {
		this.ID = commentID;
	}

	/**
	 * Get the post id for this comment
	 * @return  post id
	 */
	public int getCommentPostID() {
		return commentPostID;
	}

	/**
	 * Set the comment post id
	 * @param commentPostID - foreign key id of post that comment belongs
	 */
	public void setCommentPostID(int commentPostID) {
		this.commentPostID = commentPostID;
	}

	/**
	 * Get comment author id
	 * @return UserModel containing author info
	 */
	public UserModel getCommentBy() {
		return commentBy;
	}

	/**
	 * Set the comment author information
	 * @param commentBy - UserModel containing the author 
	 */
	public void setCommentBy(UserModel commentBy) {
		this.commentBy = commentBy;
	}

	/**
	 * Get the comment date
	 * @return comment date
	 */
	public Date getCommentDate() {
		return commentDate;
	}

	/**
	 * Set the date the comment was created
	 * @param commentDate - creation date of comment
	 */
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	/**
	 * Get the comment contents - text
	 * @return string containing the comment content
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * Set the contents of the comment
	 * @param commentText - content text 
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
}