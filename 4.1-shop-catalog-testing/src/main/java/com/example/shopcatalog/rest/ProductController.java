package com.example.shopcatalog.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopcatalog.domain.Product;
import com.example.shopcatalog.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping("/api/products")
	public @ResponseBody Page<Product> listProducts(Pageable pageRequest) {
		return service.getProducts(pageRequest);
	}
	
	@GetMapping("/api/products/{productId}")
	public @ResponseBody Product getProduct(@PathVariable String productId) {
		return service.getProduct(productId);
	}
	
	@GetMapping("/api/products/category/{category}")
	public @ResponseBody Page<Product> listProducts(@PathVariable String category, Pageable pageRequest) {
		return service.getProductsByCategory(category, pageRequest);
	}
	
	@PostMapping("/api/products")
	public @ResponseBody Product create(@RequestBody Product product) {
		Product res = service.create(product);
		return res;
	}
	
	@PutMapping("/api/products/{productId}/availability/{value}")
	public @ResponseBody Product changeAvailability(@PathVariable String productId, @PathVariable int value) {
		return service.changeAvailability(productId, value);
	}
}
