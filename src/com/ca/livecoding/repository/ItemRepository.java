package com.ca.livecoding.repository;

import java.sql.SQLException;

import com.ca.livecoding.model.Item;

public interface ItemRepository {
	/*
	 * Find an {@link Item} by 
	 * unique identifier.
	 * 
	 * @param int id  Unique identifier
	 * @return The Item if found, <code>null</code>
	 *         if not found.
	 */
	Item find(int id) throws SQLException;
}
