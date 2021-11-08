package com.gcu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.gcu.data.DataAccessInterface;
import com.gcu.data.DataAccessPostExtrasInterface;
import com.gcu.models.CategoryModel;
import com.gcu.models.CommentModel;
import com.gcu.models.PostModel;
import com.gcu.models.UserModel;

public class DataAccessService implements DataAccessServiceInterface {
	
	@Autowired
	DataAccessInterface<UserModel> userDAO;

	@Autowired
	DataAccessInterface<PostModel> postDAO;
	@Autowired
	DataAccessPostExtrasInterface<PostModel> postExtrasDAO;

	@Autowired
	DataAccessInterface<CategoryModel> categoryDAO;
	
	@Autowired
	DataAccessInterface<CommentModel> commentDAO;

	
	public boolean storeUserInDB(UserModel user) {
		boolean success = userDAO.create(user);
		if(success) {
			System.out.println("User Created Success");
		} else {
			System.out.println("User Created Failure");
		}
		return success;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub	
	}

	@Override
	public boolean doPost(PostModel post) {
		return postDAO.create(post);
	}

	@Override
	public List<PostModel> getPosts() {
		return postDAO.findAll();
	}

	@Override
	public PostModel findByID(int id) {
		return postDAO.findById(id);
	}

	@Override
	public List<CategoryModel> loadCategories() {
		return categoryDAO.findAll();
	}

	@Override
	public List<PostModel> getMyPosts(int id) {
		return postExtrasDAO.findAllByUserId(id);
	}

	@Override
	public boolean deletePostById(String id) {
		return postDAO.delete(id);
	}

	@Override
	public boolean updatePost(PostModel post) {
		return postDAO.update(post);
	}

	@Override
	public boolean storeCommentInDB(CommentModel comment) {
		return commentDAO.create(comment);
	}
}