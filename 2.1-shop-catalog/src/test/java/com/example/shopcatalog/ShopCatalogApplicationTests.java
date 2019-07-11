package com.example.shopcatalog;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.shopcatalog.data.ProductRepository;
import com.example.shopcatalog.domain.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShopCatalogApplicationTests {

	@Autowired
	private ProductRepository repo;
	
	@Autowired
	private MockMvc mockMvc;

	@Before
	public void tearUp() {
		repo.deleteAll();
	}
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testCreate() throws Exception {
		Product product = new Product();
		product.setAvailability(10);
		product.setCategory("test");
		product.setTitle("title");
		product.setDescription("description");
		product.setPrice(100.0);

		// test creation
		RequestBuilder postRequest = MockMvcRequestBuilders.post("/api/products")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(new ObjectMapper().writeValueAsString(product));
		mockMvc.perform(postRequest)
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("title")));
	
		// test list
		RequestBuilder listRequest = MockMvcRequestBuilders.get("/api/products")
			.contentType(MediaType.APPLICATION_JSON_UTF8);
		mockMvc.perform(listRequest)
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(1)))
			.andExpect(MockMvcResultMatchers.jsonPath("$.content[0].title", Matchers.is("title")));
		
		// test list with category
		listRequest = MockMvcRequestBuilders.get("/api/products/category/test")
			.contentType(MediaType.APPLICATION_JSON_UTF8);
		mockMvc.perform(listRequest)
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(1)))
			.andExpect(MockMvcResultMatchers.jsonPath("$.content[0].title", Matchers.is("title")));

		// test list with wrong category
		listRequest = MockMvcRequestBuilders.get("/api/products/category/test2")
				.contentType(MediaType.APPLICATION_JSON_UTF8);
			mockMvc.perform(listRequest)
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(0)));
	}	
}
