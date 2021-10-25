package data;

import java.util.List;

public interface DataAccessFindListByPostIDInterface <T>
{
	/**
	 * Generic Method to find a list of items by post id (key)
	 * @param id - user id, post id, etc....
	 * @return list of specified object
	 */
	public List<T> findListByPostID(int id);
}