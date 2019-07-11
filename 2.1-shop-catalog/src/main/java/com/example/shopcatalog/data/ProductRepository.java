package com.example.shopcatalog.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.shopcatalog.domain.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

	Page<Product> findByCategory(String category, Pageable pageRequest);
}
