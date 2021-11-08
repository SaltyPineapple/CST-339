package com.gcu.service;

import java.util.List;

import com.gcu.models.CategoryModel;
import com.gcu.models.CommentModel;
import com.gcu.models.PostModel;
import com.gcu.models.UserModel;

public interface DataAccessServiceInterface {
	
	/**
	 * Method to store user information in the database
	 * @param user - user information
	 * @return boolean - true if successfully stored, false otherwise
	 */
	public boolean storeUserInDB(UserModel user);
	
	/**
	 * Method to store comment in the database
	 * @param comment - comment information 
	 * @return  boolean - true if successfully stored, false otherwise
	 */
	public boolean storeCommentInDB(CommentModel comment);
	
	/**
	 * Method to initiate bean instance
	 */
	public void init();
	
	/**
	 * Method to destroy bean instance
	 */
	public void destroy();
	
	/**
	 * Method to create a post in the database
	 * @param post - model containing post information
	 * @return boolean - true if successfully stored, false otherwise
	 */
	public boolean doPost(PostModel post);
	
	/**
	 * Method to update a post into the database
	 * @param post - model containing post information
	 * @return boolean - true if successfully stored, false otherwise
	 */
	public boolean updatePost(PostModel post);
	
	/**
	 * Method to retrieve all posts from the database
	 * @return list of PostModel contain posts information
	 */
	public List<PostModel> getPosts();
	
	/**
	 * Method to find specific post by post id
	 * @param id - post id (key)
	 * @return PostModel containing post information
	 */
	public PostModel findByID(int id);
	
	/**
	 * Method to return all categories
	 * @return list of categories
	 */
	public List<CategoryModel> loadCategories();
	
	/**
	 * Method to retrieve all posts by specific user id
	 * @param id - user id
	 * @return list of PostModel
	 */
	public List<PostModel> getMyPosts(int id);
	
	/**
	 * Method to delete post specified by post id
	 * @param id - post id
	 * @return boolean - true if successfully stored, false otherwise
	 */
	public boolean deletePostById(String id);
}