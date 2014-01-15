package com.ca.livecoding.test.repository.mysql;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import com.ca.livecoding.model.Item;
import com.ca.livecoding.repository.mysql.MySqlItemRepository;

@RunWith(Enclosed.class)
public class MySqlItemRepositoryIntegrationTest {

	public static class Given_Id_Of_Existing_Item {
		private static final String DESCRIPTION = "some widget";
		private static final String SKU = "CA-ABC-123";

		private Connection connection;
		
		private MySqlItemRepository itemRepository;
		private Item result;
		
		@Before
		public void arrange() throws ClassNotFoundException, SQLException {
			Class.forName("com.mysql.jdbc.Driver");
			
			String connString = "jdbc:mysql://localhost/warehouse_test";
			connection = DriverManager
					.getConnection(connString, "root", "");
			
			Statement createTable = connection.createStatement();
			createTable.execute(
				"CREATE TABLE Item (id int not null auto_increment PRIMARY KEY," +
				"sku varchar(32) not null, description varchar(1024));");
			
			PreparedStatement statement =
					connection.prepareStatement(
							"INSERT INTO Item (sku, description) VALUES (?, ?)");
			statement.setString(1, SKU);
			statement.setString(2, DESCRIPTION);
			
			statement.execute();
			
			itemRepository = new MySqlItemRepository(connection);
			
			act();
		}
		
		private void act() throws SQLException {
			result = itemRepository.find(1);
		}
		
		@After
		public void teardown() throws SQLException {
			Statement dropTable = connection.createStatement();
			dropTable.execute("DROP TABLE Item");
			
			connection.close();
		}
		
		@Test
		public void should_have_found_correct_item() {
			assertNotNull("Should not be null", result);
			assertEquals(1, result.getId());
			assertEquals(SKU, result.getSku());
			assertEquals(DESCRIPTION, result.getDescription());
		}
	}
	
}
