package com.gcu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.data.DataAccessInterface;
import com.gcu.models.PostModel;

@RestController
@RequestMapping("/service")
public class PostRestService {
	@Autowired
	DataAccessInterface<PostModel> service;
	
	/**
	 * REST API - Find All Posts (Return a list of PostModel)
	 * @return ResponseEntity List of PostModels
	 */
	@GetMapping(path="/posts", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getPosts() {
		try
		{
			List<PostModel> posts = service.findAll();
			if (posts == null || posts.isEmpty())
				return new ResponseEntity<>(new ArrayList<PostModel>(), HttpStatus.NOT_FOUND);
			else
				return new ResponseEntity<>(posts, HttpStatus.OK);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new ArrayList<PostModel>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	/**
	 * REST API - Find a specific post by id (sid)
	 * @param sid Integer 
	 * @return ResponseEntity PostModel
	 */
	@GetMapping(path="/posts/{string_id}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getPostsById(@PathVariable("string_id") String sid) {
	  try
	  {
		  int id = Integer.parseInt(sid);
		  PostModel result = service.findById(id);
		  
		  if (result == null)
			  return new ResponseEntity<>(new PostModel(-1), HttpStatus.NOT_FOUND);
		  else
			  return new ResponseEntity<>(result, HttpStatus.OK);
	  } catch (NumberFormatException e)
	  {
		  return new ResponseEntity<>(new PostModel(-1), HttpStatus.BAD_REQUEST);
	  }
	  catch (Exception e)
	  {
		  return new ResponseEntity<>(new PostModel(-1), HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
}