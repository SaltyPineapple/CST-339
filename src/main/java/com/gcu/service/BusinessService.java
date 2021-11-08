package com.gcu.service;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gcu.models.CategoryModel;
import com.gcu.models.CommentModel;
import com.gcu.models.PostModel;
import com.gcu.models.BlogPostModel;
import com.gcu.models.UserModel;

@Service
public class BusinessService implements BusinessServiceInterface, UserDetailsService {
	
	@Autowired
	private DataAccessServiceInterface DAO;

	@Autowired
	private SecurityBusinessServiceInterface security;
	
	public boolean storeUserInDB(UserModel user) {
		return DAO.storeUserInDB(user);
	}
	
	@Override
	public void init() {
		System.out.println("BS INIT");
	}

	@Override
	public void destroy() {
		System.out.println("BS DESTROY");
	}

	@Override
	public boolean doPost(BlogPostModel post) {
		return DAO.doPost(post);
	}

	@Override
	public List<BlogPostModel> getPosts() {
		return DAO.getPosts();
	}

	@Override
	public BlogPostModel findByID(int id) {
		return DAO.findByID(id);
	}

	@Override
	public List<CategoryModel> loadCategories() {
		return DAO.loadCategories();
	}

	@Override
	public boolean inputsValid(String email, String password) {
		return security.inputsValid(email, password);
	}

	@Override
	public boolean authenticate(String email, String password) {
		return security.authenticate(email, password);
	}

	@Override
	public @Valid UserModel findByEmail(String email) {
		return security.findByEmail(email);
	}

	@Override
	public List<BlogPostModel> getMyPosts(int id) {
		return DAO.getMyPosts(id);
	}

	@Override
	public boolean deletePostById(String id) {
		return DAO.deletePostById(id);
		
	}

	@Override
	public boolean updatePost(BlogPostModel post) {
		return DAO.updatePost(post);
	}

	@Override
	public boolean storeCommentInDB(CommentModel comment) {
		return DAO.storeCommentInDB(comment);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel user = this.findByEmail(username);
		System.out.println(String.format("You entered email of %s and password of %s", user.getEmail(), user.getPassword()));
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		return new User(user.getEmail(), user.getPassword(), authorities);
	}

	@Override
	public boolean doPost(PostModel post) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PostModel findByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePost(PostModel postModel) {
		// TODO Auto-generated method stub
		return false;
	}
}