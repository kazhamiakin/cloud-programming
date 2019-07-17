package com.example.shopcatalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.shopcatalog.data.ProductRepository;
import com.example.shopcatalog.domain.Product;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;
	
	@Autowired
	private MongoTemplate template;
	
	public Page<Product> getProducts(Pageable pageRequest) {
		return repo.findAll(pageRequest);
	}
	
	public Product getProduct(String productId) {
		return repo.findById(productId).orElse(null);
	}
	
	public Page<Product> getProductsByCategory(String category, Pageable pageRequest) {
		return repo.findByCategory(category, pageRequest);
	}
	
	public Product create(Product product) {
		return repo.save(product);
	}
	
	public Product changeAvailability(String productId, int change) {
		template.updateFirst(
				Query.query(Criteria.where("id").is(productId)), 
				new Update().inc("availability", change), 
				Product.class);
		return getProduct(productId);
	}
}
