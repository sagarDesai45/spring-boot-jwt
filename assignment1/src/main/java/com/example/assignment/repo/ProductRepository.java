package com.example.assignment.repo;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.assignment.dao.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{


	public Optional<Product> findById (Integer id);
	
	@Query("SELECT p FROM Product p WHERE p.category = :category ORDER BY p.createdAt DESC")
	public List<Product> findByCategoryOrderByCreatedAtDesc(@Param("category") String category,Pageable pageable);

}
