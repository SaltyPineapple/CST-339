package data;

import java.util.List;

import com.gcu.models.BlogPostModel;



public interface DataAccessPostExtrasInterface <T> {
	
	/**
	 * Method to calculate rating percentage for post specified by id
	 * @param id - post id (key)
	 * @return double - average of ratings
	 */
	public Double calculateRatingPercentage(int id);
	
	/**
	 * Method to find all posts by user id (key)
	 * @param id - user id 
	 * @return list of PostModel holding all posts by specified user
	 */
	public List<BlogPostModel> findAllByUserId(int id);
}