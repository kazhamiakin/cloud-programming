package com.example.shoppurchase.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.shoppurchase.domain.Product;

@FeignClient("shopcatalog")
public interface ProductService {

	@GetMapping("/api/products/{productId}")
	public Product getProduct(@PathVariable String productId);
	
	@PutMapping("/api/products/{productId}/availability/{quantity}")
	public Product bookAvailability(
			@PathVariable String productId, 
			@PathVariable int quantity);
}
