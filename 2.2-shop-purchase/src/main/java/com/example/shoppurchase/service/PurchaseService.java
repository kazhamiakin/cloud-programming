package com.example.shoppurchase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.shoppurchase.data.PurchaseRepository;
import com.example.shoppurchase.domain.Product;
import com.example.shoppurchase.domain.Purchase;
import com.example.shoppurchase.domain.PurchaseRequest;

@Service
public class PurchaseService {

	@Autowired
	private PurchaseRepository repo;
	
	@Autowired
	private ProductService productService;
	
	public Page<Purchase> getUserPurchases(String userId, Pageable pageRequest) {
		return repo.findByUserId(userId, pageRequest);
	}
	
	public Purchase getUserPurchase(String purchaseId) {
		return repo.findById(purchaseId).orElse(null);
	}
	
	public Purchase buy(String userId, PurchaseRequest request) {
		Purchase purchase = new Purchase();
		
		Product product = productService.getProduct(request.getProductId());
		if (product != null) {
			purchase.setProductId(request.getProductId());
			purchase.setQuantity(request.getCount());
			purchase.setUserId(userId);
			purchase.setProductTitle(product.getTitle());
			purchase.setProductCategory(product.getCategory());
			purchase.setPrice(product.getPrice());
			
			productService.bookAvailability(request.getProductId(), request.getCount());
			return repo.save(purchase);			
		}
		return purchase;
	}
}
