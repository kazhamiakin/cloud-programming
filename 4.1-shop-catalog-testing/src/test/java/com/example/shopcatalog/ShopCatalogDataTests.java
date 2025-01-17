package com.example.shopcatalog;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.shopcatalog.data.ProductRepository;
import com.example.shopcatalog.domain.Product;

@DataMongoTest
public class ShopCatalogDataTests {

	@Autowired
	private ProductRepository repo;
	
	@BeforeEach
	public void tearUp() {
		repo.deleteAll();
	}
	
	@Test
	public void testData() throws Exception {
		Product product = new Product();
		product.setAvailability(10);
		product.setCategory("test");
		product.setTitle("title");
		product.setDescription("description");
		product.setPrice(100.0);

		// test create
		product = repo.save(product);
		Assert.assertNotNull(product.getId());
		
		// test find
		Optional<Product> optional = repo.findById(product.getId());
		Assert.assertTrue(optional.isPresent());
		
		// test list
		List<Product> list = repo.findAll();
		Assert.assertEquals(1, list.size());
		
		// test list by category		
		Page<Product> page = repo.findByCategory("test", PageRequest.of(0, 10));
		Assert.assertEquals(1, page.getContent().size());
		
		// test delete 
		repo.delete(product);
		optional = repo.findById(product.getId());
		Assert.assertTrue(!optional.isPresent());
	}	
}
