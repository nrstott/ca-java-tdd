package com.ca.livecoding.test.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.Enclosed;

import com.ca.livecoding.model.Item;

@RunWith(Enclosed.class)
public class ItemTest {

	public static class Constructor {
		private int id;
		private String sku;
		private String description;
		
		private Item item;
		
		@Before
		public void arrange() {
			id = 12;
			sku = "ABC-123";
			description = "Widget";
			
			act();
		}
		
		public void act() {
			item = new Item(id, sku, description);
		}
		
		@Test
		public void given_id_should_getId() {
			assertEquals("Should get correct id",
					id, item.getId());
		}
		
		@Test
		public void given_description_should_getDescription() {
			assertEquals("Should get correct description",
					description, item.getDescription());
		}
		
		@Test
		public void given_sku_should_getSku() {
			assertEquals("Should get correct sku",
					sku, item.getSku());
		}
	}

	public static class Constructor_Given_Invalid_Sku {
		private String invalidSku;
		
		private Item item;
		
		@Rule
		public ExpectedException expectedException = 
			ExpectedException.none();
		
		@Before
		public void arrange() {
			invalidSku = "INVALID SKU WITH SPACES";
			
			expectedException.expect(IllegalArgumentException.class);
		}
		
		@Test
		public void should_throw_IllegalArgumentException() {
			item = new Item(1, invalidSku, "some description");
		}
	}
	
	public static class Given_setSku {
		private String sku;
		
		private Item item;
		
		@Before
		public void arrange() {
			sku = "CA-WIDGET-ABC";
			item = new Item(1, "ABC123", "widget");
			
			act();
		}
		
		private void act() {
			item.setSku(sku);
		}
		
		@Test
		public void should_getSku() {
			assertEquals("Should get correct sku",
					sku, item.getSku());
		}
	}
	
	public static class Given_Invalid_Sku_setSku {
		private String invalidSku;
		private Item item;
		
		@Rule
		public ExpectedException expectedException = 
			ExpectedException.none();
		
		@Before
		public void arrange() {
			invalidSku = "THIS IS A SKU WITH SPACES";
			item = new Item(1, "somesku", "some description");
			
			expectedException.expect(IllegalArgumentException.class);
		}
		
		@Test
		public void should_throw_IllegalArgumentException() {
			item.setSku(invalidSku);
		}
	}
}
