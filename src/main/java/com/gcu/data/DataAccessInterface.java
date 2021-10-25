package data;

import java.util.List;

public interface DataAccessInterface <T> {
	
	/**
	 * Generic Method to find a list of all specified items
	 * @return list of specified objects
	 */
	public List<T> findAll();
	
	/**
	 * Generic Method to find a specified object by id
	 * @param id - id to search
	 * @return specified object or null if not found
	 */
	public T findById(int id);
	
	/**
	 * Generic Method to create/store object in database
	 * @param t - specified object
	 * @return boolean - true if successfully created, false otherwise
	 */
	public boolean create(T t);
	
	/**
	 * Generic Method to update object in database
	 * @param t - specified object
	 * @return boolean - true if successfully updated, false otherwise
	 */
	public boolean update(T t);
	
	/**
	 * Generic Method to delete object from database
	 * @param id - specified item to delete/update deleted flag
	 * @return boolean - true is successfully deleted, false otherwise
	 */
	public boolean delete(String id);
}