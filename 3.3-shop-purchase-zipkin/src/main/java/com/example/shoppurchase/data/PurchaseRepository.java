package com.example.shoppurchase.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.shoppurchase.domain.Purchase;

public interface PurchaseRepository extends MongoRepository<Purchase, String> {

	Page<Purchase> findByUserId(String userId, Pageable pageRequest);
}
