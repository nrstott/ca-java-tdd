package com.ca.livecoding.test.repository.mysql;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ca.livecoding.model.Item;
import com.ca.livecoding.repository.mysql.MySqlItemRepository;

@RunWith(Enclosed.class)
public class MySqlItemRepositoryTest {
	
	public static class Given_Id_Of_Existing_Item {
		private int id;
		private String sku;
		private String description;
		
		private MySqlItemRepository itemRepository;
		private Item result;
		
		@Mock
		private Connection connection;
		
		@Mock
		private PreparedStatement statement;
		
		@Mock
		private ResultSet resultSet;
		
		@Before
		public void arrange() throws SQLException {
			MockitoAnnotations.initMocks(this);
			
			id = 1;
			sku = "valid_sku";
			description = "a widget";
			
			Mockito
				.when(connection.prepareStatement(Mockito.anyString()))
				.thenReturn(statement);
			
			Mockito
				.when(statement.executeQuery())
				.thenReturn(resultSet);
			
			Mockito
				.when(resultSet.first())
				.thenReturn(true);
			
			Mockito
				.when(resultSet.getInt(1))
				.thenReturn(id);
			
			Mockito
				.when(resultSet.getString(2))
				.thenReturn(sku);
			
			Mockito
				.when(resultSet.getString(3))
				.thenReturn(description);
			
			itemRepository = new MySqlItemRepository(connection);
			
			act();
		}
		
		private void act() throws SQLException {
			result = itemRepository.find(id);
		}
		
		@Test
		public void should_have_returned_non_null_result() {
			assertNotNull("Result should not be null", result);
		}
		
		@Test
		public void should_have_correct_id() {
			assertEquals("Shoud have correct id", 
					id, result.getId());
		}
		
		@Test
		public void should_have_correct_sku() {
			assertEquals("Should have correct sku", 
					sku, result.getSku());
		}
		
		@Test
		public void should_have_correct_description() {
			assertEquals("Should have correct description", 
					description, result.getDescription());
		}
		
		@Test
		public void should_have_added_id_parameter() throws SQLException {
			Mockito.verify(statement).setInt(1, id);
		}
	}
}
