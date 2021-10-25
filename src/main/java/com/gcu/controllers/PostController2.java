package com.gcu.controllers;

import java.sql.Timestamp;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.gcu.models.CategoryModel;
import com.gcu.models.CommentModel;
import com.gcu.models.BlogPostModel;
import com.gcu.models.UserModel;
import com.gcu.service.BusinessServiceInterface;

@Controller
@SessionAttributes("userData")
@RequestMapping("/post")
public class PostController2 {

	@Autowired
	private BusinessServiceInterface bservice;

	private List<CategoryModel> categories;

	/**
	 * Method to handle all post request
	 * @param model - holds list of posts
	 * @return string for the next view
	 */
	@GetMapping("/all")
	public String postsAll(Model model) {
		//load posts into some array list
		List<BlogPostModel> posts = this.bservice.getPosts();
		model.addAttribute("page_title", "All Posts");
		model.addAttribute("posts", posts);
		return "postList";
	}

	/**
	 * Method to handle my post request
	 * @param model - holds list of posts
	 * @return string for the next view
	 */
	@GetMapping("/myBlog")
	public String postsMy(Model model) {
		//if user is in session
		if(model.getAttribute("userData") != null) {
			//load posts into some array list for this user only.
			List<BlogPostModel> posts = this.bservice.getMyPosts(((UserModel) model.getAttribute("userData")).getUserID());
			model.addAttribute("page_title", "My Posts");
			model.addAttribute("posts", posts);
			return "postList";
		}
		//if no user in session, display all posts.
		return postsAll(model);
	}

	/**
	 * Method to handle single post request
	 * @param id - key field for post request
	 * @param model - holds post information to display
	 * @return string to the next view
	 */
	@GetMapping("/{id}")
	public String postSingle(@PathVariable String id, Model model) {
		//check if id is int, if not return errorView. If int then cont.
		//load the post with ID = id
		BlogPostModel post = this.bservice.findByID(Integer.parseInt(id));
		model.addAttribute("post", post);

		// Add new comment model for new entry
		CommentModel comment = new CommentModel();
		comment.setCommentPostID(post.getID());
		model.addAttribute("commentModel", comment);

		// Get list of all comments for this post
		List<CommentModel> comments = post.getComments();
		model.addAttribute("comments", comments);

		return "postView";
	}

	/**
	 * Method to delete a specified (id) post
	 * @param id - key field to delete post
	 * @param model - information sent from form
	 * @return string to the next view
	 */
	@GetMapping("/delete/{id}")
	public String deleteSingle(@PathVariable String id, Model model) {
		//load the post with ID = id
		BlogPostModel post = this.bservice.findByID(Integer.parseInt(id));
		if(((UserModel) model.getAttribute("userData")).getUserID() == post.getAuthorID()) {
			this.bservice.deletePostById(id);
		}
		return postsAll(model);
	}

	/**
	 * Method to handle request to create a new post
	 * @param model - post information obtained from view
	 * @return string to the next view
	 */
	@GetMapping("/new")
	public String postNew(Model model) {
		if(!model.containsAttribute("userData")) {
			return "index";
		}

		this.categories = this.bservice.loadCategories();
		model.addAttribute(new BlogPostModel());
		model.addAttribute("categories", categories);
		model.addAttribute("button","Create Post");
		return "postNew";
	}

	/**
	 * Method to edit a single post
	 * @param id - key field to identify post to be edited
	 * @param model - information from the view
	 * @return ModelAndView - model and view for the next view
	 */
	@GetMapping("/edit/{id}")
	public ModelAndView editSingle(@PathVariable String id, Model model) {
		ModelAndView mv = new ModelAndView();
		BlogPostModel post = this.bservice.findByID(Integer.parseInt(id));

		System.out.println("/edit Post ID: " + post.getID() + " " + post.toString() );

		if(((UserModel) model.getAttribute("userData")).getUserID() == post.getAuthorID()) {
			this.categories = this.bservice.loadCategories();
			mv.addObject(post);
			mv.addObject("button","Update Post");
			mv.addObject("categories", categories);
			mv.setViewName("postNew");
		} else {
			mv.setViewName("index");
		}
		return mv;
	}

	/**
	 * Method to commit new post to database
	 * @param BlogPostModel - post information from view
	 * @param bindingResult - check form field requirements
	 * @param model - information gathered from view
	 * @param user - UserModel current logged in user
	 * @return ModelAndView - model and view for the next view
	 */
	@PostMapping("/doPost")
	public ModelAndView doPost(@Valid BlogPostModel BlogPostModel, BindingResult bindingResult, Model model, @SessionAttribute("userData") UserModel user) {
		ModelAndView mv = new ModelAndView();
		setCategory_stringToObject(BlogPostModel, bindingResult);

		if (bindingResult.getFieldError("title") != null || bindingResult.getFieldError("content") != null || bindingResult.getFieldError("keywords") != null) {
			mv.addObject("categories", categories);
			mv.setViewName("postNew");
			return mv;
		}

		System.out.println("/doPost Post ID: " + BlogPostModel.getID() + " " + BlogPostModel.toString() );

		if(BlogPostModel.getID() == 0) { //ID only assigned once inserted into DB. if 0, new post.
			BlogPostModel.setDate(new Timestamp(System.currentTimeMillis()));
			BlogPostModel.setAuthorID(user.getUserID());
			BlogPostModel.setUpdatedDate(BlogPostModel.getDate());
			BlogPostModel.setUpdatedBy(BlogPostModel.getAuthorID());
			bservice.doPost(BlogPostModel);
		} else {					// if anything other than 0, ID has been loaded from the post
			BlogPostModel.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
			BlogPostModel.setUpdatedBy(user.getUserID());
			bservice.updatePost(BlogPostModel);
		}

		mv.addObject("newPost", BlogPostModel);
		mv.setViewName("index");
		return mv;
	}

	/**
	 * Method to handle comment entry/edit
	 * @param commentModel - information regarding comment
	 * @param bindingResult - form field requirement checking
	 * @param model - holds user information
	 * @return string - next view to send user
	 */
	@PostMapping("/doComment")
	public String doComment(@Valid CommentModel commentModel, BindingResult bindingResult, Model model) {
		if(model.getAttribute("userData") != null) {
			// Return if error or comment is empty
			System.out.println("test");
			if (bindingResult.getFieldError("commentText") != null ||
				commentModel.getCommentText() == null ||
				commentModel.getCommentText().isEmpty() ) {
				return "postView";
			}
	
			// Insert comment into Database
			// Question: What to do if insert fails?
			//
			commentModel.setCommentBy((UserModel) model.getAttribute("userData"));
			commentModel.setCommentDate(new Timestamp(System.currentTimeMillis()));
			bservice.storeCommentInDB(commentModel);			
		}
		String id = "" + commentModel.getCommentPostID();
		return postSingle(id, model);
	}

	/**
	 * Helper Method to obtain/validate category configuration for posts
	 * @param BlogPostModel - hold post information
	 * @param bindingResult - form field requirement checks
	 */
	private void setCategory_stringToObject(BlogPostModel BlogPostModel, BindingResult bindingResult) {
		//convert string selection back to object.
		String category = (String) bindingResult.getFieldValue("category"); //get the selection as string
		System.out.println("Category Selected (String):" + category);
		//search the list for a match

		if(category == null) {
			BlogPostModel.setCategory(new CategoryModel(0, "Undefined"));
		}
		else {
			for (CategoryModel name : this.categories) {
				System.out.println("Searching list of category models for category name match... ");
				System.out.println("comparing '" + name.getCategoryName()+ "' to... '" + category + "'");
				if(name.getCategoryName().equals(category)) {
					//add object back to BlogPostModel.
					BlogPostModel.setCategory(name);
					System.out.println("Match! Inserting Object " + name.toString() + " into post model " + BlogPostModel.toString());
					break;
				}
			}
		}
	}
}