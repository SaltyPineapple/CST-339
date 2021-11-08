package com.gcu.models;

public class RatingModel {
	private int ID;
	private int ratingPostID;
	private UserModel ratedBy;
	private boolean ratingValue;
	
	/**
	 * @param ratingID - id (key)
	 * @param ratingPostID - foreign key - post id rating belongs
	 * @param ratedBy - user model containing user that created rating
	 * @param ratingValue - value / rate assigned by rating user
	 */
	public RatingModel(int ratingID, int ratingPostID, UserModel ratedBy, boolean ratingValue) {
		super();
		this.ID = ratingID;
		this.ratingPostID = ratingPostID;
		this.ratedBy = ratedBy;
		this.ratingValue = ratingValue;
	}

	/**
	 * Get the rating ID
	 * @return ID
	 */
	public int getRatingID() {
		return ID;
	}

	/**
	 * Set the rating ID
	 * @param ratingID - key field
	 */
	public void setRatingID(int ratingID) {
		this.ID = ratingID;
	}

	/**
	 * Get the post id this rating is assigned
	 * @return post id
	 */
	public int getRatingPostID() {
		return ratingPostID;
	}

	/**
	 * Set the post id this rating is assigned
	 * @param ratingPostID - post id 
	 */
	public void setRatingPostID(int ratingPostID) {
		this.ratingPostID = ratingPostID;
	}

	/**
	 * Get the user model that created this rating
	 * @return user model
	 */
	public UserModel getRatedBy() {
		return ratedBy;
	}

	/**
	 * Set the user model that created this rating
	 * @param ratedBy - User model 
	 */
	public void setRatedBy(UserModel ratedBy) {
		this.ratedBy = ratedBy;
	}

	/**
	 * Check the rating value
	 * @return boolean (rated/not rated)
	 */
	public boolean isRatingValue() {
		return ratingValue;
	}

	/**
	 * Set the rating value
	 * @param ratingValue - boolean (rated/not rated)
	 */
	public void setRatingValue(boolean ratingValue) {
		this.ratingValue = ratingValue;
	}
}