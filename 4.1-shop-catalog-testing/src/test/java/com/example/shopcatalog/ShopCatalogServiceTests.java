package com.example.shopcatalog;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.shopcatalog.data.ProductRepository;
import com.example.shopcatalog.domain.Product;
import com.example.shopcatalog.service.ProductService;

@SpringBootTest
@AutoConfigureMockMvc
public class ShopCatalogServiceTests {

	@Autowired
	private ProductService service;
	@Autowired
	private ProductRepository repo;
	
	@BeforeEach
	public void tearUp() {
		repo.deleteAll();
	}
	
	@Test
	public void testService() throws Exception {
		Product product = new Product();
		product.setAvailability(10);
		product.setCategory("test");
		product.setTitle("title");
		product.setDescription("description");
		product.setPrice(100.0);

		// test create
		product = service.create(product);
		Assert.assertNotNull(product.getId());
		
		// test find
		product = service.getProduct(product.getId());
		Assert.assertNotNull(product);
		
		// test list
		Page<Product> page = service.getProducts(PageRequest.of(0, 10));
		Assert.assertEquals(1, page.getContent().size());
		
		// test list by category		
		page = service.getProductsByCategory("test", PageRequest.of(0, 10));
		Assert.assertEquals(1, page.getContent().size());
		
		// test change availability 
		service.changeAvailability(product.getId(), -1);
		product = service.getProduct(product.getId());
		Assert.assertEquals(9, (int)product.getAvailability());
		
	}	
}
