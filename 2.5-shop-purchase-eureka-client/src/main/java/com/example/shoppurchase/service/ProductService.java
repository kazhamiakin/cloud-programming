package com.example.shoppurchase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.shoppurchase.domain.Product;

@Service
public class ProductService {

	@Autowired
	private RestTemplate restTemplate;
	
	public Product getProduct(String productId) {
		return restTemplate.getForObject("http://shopcatalog/api/products/{productId}", Product.class, productId);
	}
	
	public Product bookAvailability(String productId, int quantity) {
		return restTemplate.exchange("http://shopcatalog/api/products/{productId}/availability/{quantity}", HttpMethod.PUT, null, Product.class, productId, -quantity).getBody();
	}
}
