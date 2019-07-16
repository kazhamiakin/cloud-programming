package com.example.shoppurchase.service;

import org.springframework.stereotype.Component;

import com.example.shoppurchase.domain.Product;

@Component
public class ProductServiceFallback implements ProductService {

	@Override
	public Product getProduct(String productId) {
		return new Product();
	}

	@Override
	public Product bookAvailability(String productId, int quantity) {
		return new Product();
	}

}
