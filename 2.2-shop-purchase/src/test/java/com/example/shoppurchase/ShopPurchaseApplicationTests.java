package com.example.shoppurchase;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.shoppurchase.data.PurchaseRepository;
import com.example.shoppurchase.domain.Product;
import com.example.shoppurchase.domain.PurchaseRequest;
import com.example.shoppurchase.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShopPurchaseApplicationTests {

	@Autowired
	private PurchaseRepository repo;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;
	
	@Before
	@After
	public void tearUp() {
		repo.deleteAll();
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testPurchase() throws Exception {
		Product product = new Product();
		product.setAvailability(10);
		product.setCategory("test");
		product.setTitle("title");
		product.setDescription("description");
		product.setPrice(100.0);
		product.setId("1");

		Mockito.when(productService.getProduct("1")).thenReturn(product);
		Mockito.when(productService.bookAvailability("1", 1)).thenReturn(product);
		
		PurchaseRequest pr = new PurchaseRequest();
		pr.setCount(1);
		pr.setProductId("1");
		
		// test creation
		RequestBuilder postRequest = MockMvcRequestBuilders.post("/api/purchases/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(new ObjectMapper().writeValueAsString(pr));
		
		mockMvc.perform(postRequest)
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.jsonPath("$.quantity", Matchers.is(1)));

		// test list
		RequestBuilder listRequest = MockMvcRequestBuilders.get("/api/purchases/1")
			.contentType(MediaType.APPLICATION_JSON_UTF8);
		mockMvc.perform(listRequest)
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(1)));

	}
	
}
