package com.ca.livecoding.model;

public class Item {
	private int id;
	private String sku;
	private String description;
	
	public Item(int id, String sku, String description) {
		this.id = id;
		this.setSku(sku);
		this.description = description;
	}

	public int getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		if (sku.contains(" ")) {
			throw new IllegalArgumentException("sku must not contain spaces");
		}
		this.sku = sku;
	}
}
