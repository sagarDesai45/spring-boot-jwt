package com.example.assignment.serviceImpl;



import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.assignment.dao.entity.Product;
import com.example.assignment.exception.ProductNotFound;
import com.example.assignment.repo.ProductRepository;
import com.example.assignment.service.AssignmentService;


@Service
public class AssignmentServiceImpl implements AssignmentService{

	@Autowired
	private ProductRepository productRepository;
	
	
	public Product insertProduct(Product product) {
		
		product.setCreatedAt(LocalDateTime.now());

		return productRepository.save(product);
//		return productRepository.findById(product.getId()).get();
		
	}


	public List<Product> findProduct(String category,int page,int size) {
		return productRepository.findByCategoryOrderByCreatedAtDesc(category,PageRequest.of(page, size));
	}
	
	public Boolean deleteProduct(Integer id) {
		
		
		
		if(id==null)
			return false;
		
		
		if(productRepository.existsById(id))
		{
			productRepository.deleteById(id);
			return true;
		}
		
		return false;
		
	}
	
	public Product updateProduct(Product product,Integer id) throws ProductNotFound {
		if(productRepository.existsById(id))
		{
			product.setId(id);
			return productRepository.save(product);
		}
		else {
			throw new ProductNotFound("Product Not Found");
		}
	}

	
}
