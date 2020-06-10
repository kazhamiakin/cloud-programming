package com.example.shopcatalog;

import java.util.Collections;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.shopcatalog.domain.Product;
import com.example.shopcatalog.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ShopCatalogApplicationTestsMockito {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductService productService;

	private Product product;
	
	@BeforeEach
	public void tearUp() {
		product = new Product();
		product.setAvailability(10);
		product.setCategory("test");
		product.setTitle("title");
		product.setDescription("description");
		product.setPrice(100.0);
		product.setId("id");
		
		Mockito.when(productService.create(Mockito.any()))
			.thenReturn(product);
		Page<Product> page = new PageImpl<Product>(Collections.singletonList(product));
		Mockito.when(productService.getProducts(PageRequest.of(0, 20)))
			.thenReturn(page);
		Mockito.when(productService.getProductsByCategory("test", PageRequest.of(0, 20)))
			.thenReturn(page);
		Mockito.when(productService.getProductsByCategory("test2", PageRequest.of(0, 20)))
			.thenReturn(new PageImpl<Product>(Collections.emptyList()));
		
	}	
	@Test
	public void testCreate() throws Exception {

		// test creation
		RequestBuilder postRequest = MockMvcRequestBuilders.post("/api/products")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(product));
		mockMvc.perform(postRequest)
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("title")));
	
		// test list
		RequestBuilder listRequest = MockMvcRequestBuilders.get("/api/products")
			.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(listRequest)
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(1)))
			.andExpect(MockMvcResultMatchers.jsonPath("$.content[0].title", Matchers.is("title")));
		
		// test list with category
		listRequest = MockMvcRequestBuilders.get("/api/products/category/test")
			.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(listRequest)
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(1)))
			.andExpect(MockMvcResultMatchers.jsonPath("$.content[0].title", Matchers.is("title")));

		// test list with wrong category
		listRequest = MockMvcRequestBuilders.get("/api/products/category/test2")
				.contentType(MediaType.APPLICATION_JSON);
			mockMvc.perform(listRequest)
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(0)));
	}	
}
