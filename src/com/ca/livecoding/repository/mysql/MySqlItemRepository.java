package com.ca.livecoding.repository.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ca.livecoding.model.Item;
import com.ca.livecoding.repository.ItemRepository;

public class MySqlItemRepository implements ItemRepository {
	private Connection connection;
	
	public MySqlItemRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Item find(int id) throws SQLException {
		PreparedStatement statement =
				this.connection.prepareStatement(
						"SELECT id, sku, description FROM Item WHERE id = ?");
		
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		resultSet.first();
		
		int idFromDb = resultSet.getInt(1);
		String sku = resultSet.getString(2);
		String description = resultSet.getString(3);
		
		return new Item(idFromDb, sku, description);
	}

}
