package com.gcu.service;

import java.util.List;

import javax.validation.Valid;

import com.gcu.models.CategoryModel;
import com.gcu.models.CommentModel;
import com.gcu.models.PostModel;
import com.gcu.models.UserModel;

public interface BusinessServiceInterface {
	
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
	 * Method to create a post in the database
	 * @param post - model containing post information
	 * @return boolean - true if successfully stored, false otherwise
	 */
	public boolean doPost(PostModel post);
	
	/**
	 * Method to initiate bean instance
	 */
	public void init();
	
	/**
	 * Method to destroy bean instance
	 */
	public void destroy();
	
	/**
	 * Method to retrieve all posts from the database
	 * @return list of PostModel contain posts information
	 */
	public List<PostModel> getPosts();
	
	/**
	 * Method to find a specific post by id
	 * @param id - post id (key)
	 * @return PostModel if found, otherwise null
	 */
	public PostModel findByID(int id);
	
	/**
	 * Method to load all categories
	 * @return list of CategoryModel
	 */
	public List<CategoryModel> loadCategories();
	
	/**
	 * Method to ensure user input is not empty (valid)
	 * @param email - user email address
	 * @param password - user password
	 * @return boolean - true if valid, false otherwise
	 */
	public boolean inputsValid(String email, String password);
	
	/**
	 * Method to authenticate user using email and password
	 * @param email - user email address
	 * @param password - user password
	 * @return boolean - true if successfully stored, false otherwise
	 */
	public boolean authenticate(String email, String password);
	
	/**
	 * Method to find user information by email address
	 * @param email - user email address (unique)
	 * @return - UserModel if found, null otherwise
	 */
	public @Valid UserModel findByEmail(String email);
	
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
	
	/**
	 * Method to update a post into the database
	 * @param postModel - model containing post information
	 * @return boolean - true if successfully stored, false otherwise
	 */
	public boolean updatePost(PostModel postModel);
}