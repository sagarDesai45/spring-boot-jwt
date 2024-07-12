package com.example.assignment.request.entity;
import com.example.assignment.request.entity.*;

public class ProductData {

	private String name;
	private String description;
	private String brand;
	private Tags tags[];
	private String category;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public Tags[] getTags() {
		return tags;
	}
	public void setTags(Tags[] tags) {
		this.tags = tags;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
