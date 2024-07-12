package com.example.assignment.service;

import java.util.List;

import com.example.assignment.dao.entity.Product;
import com.example.assignment.exception.ProductNotFound;

public interface AssignmentService {

	public Product insertProduct(Product product);
	
	public List<Product> findProduct(String category,int page,int size) ;
	
	public Boolean deleteProduct(Integer id) ;
	
	public Product updateProduct(Product product,Integer id) throws ProductNotFound ;
}
