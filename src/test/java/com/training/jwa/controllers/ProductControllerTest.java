package com.training.jwa.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.training.jwa.model.Product;

/**
 * @author Jesse
 * Save record with product id of 999
 * update same product
 * get the product by product id
 * get all products and make sure size > 0
 * delete the same record with product id of 999
 */
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class ProductControllerTest extends AbstractTest {
	
	String uri = "/product";
	int product_id = 999;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	protected void setUp() {
		super.setUp();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Order(2)
	@DisplayName("Save product to db")
	void testSaveProduct() throws Exception {
		Product product = new Product(product_id,"mockProduct", 100, 200);
		// convert product object into json format
		String prodJson = super.mapToJson(product);
		
		MvcResult mvcRS = mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(prodJson)).andReturn();
		
		int status = mvcRS.getResponse().getStatus();
		
		String content = mvcRS.getResponse().getContentAsString();
		
		assertEquals(201, status);
		assertEquals(content,"product did save");
	}
	
	@Test
	@Order(3)
	@DisplayName("Save product but it already exists")
	void TestSaveProductAlreadyExists() throws Exception {
		Product product = new Product(product_id,"mockProduct", 100, 200);
		// convert product object into json format
		String prodJson = super.mapToJson(product);
		
		MvcResult mvcRS = mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(prodJson)).andReturn();
		
		int status = mvcRS.getResponse().getStatus();
		
		String content = mvcRS.getResponse().getContentAsString();
		
		assertEquals(409, status);
		assertEquals(content,"Product already exists");
		
	}
	
	@Test
	@Order(1)
	@DisplayName("Save product price below zero")
	void testSaveProductPriceBelowZero() throws Exception {
		Product product = new Product(product_id,"mockProduct", -100, 200);
		// convert product object into json format
		String prodJson = super.mapToJson(product);
		
		MvcResult mvcRS = mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(prodJson)).andReturn();
		
		int status = mvcRS.getResponse().getStatus();
		
		String content = mvcRS.getResponse().getContentAsString();
		
		assertEquals(406, status);
		assertEquals(content,"Product price or qoh cannot be negative");
	}
	
	// changed the status number, the string content is being compared to, and from post method to put method line 82
	@Test
	@Order(4)
	@DisplayName("Update product in db")
	void testUpdateProduct() throws Exception {
		Product product = new Product(product_id,"newmockProduct", 200, 300);
		// convert product object into json format
		String prodJson = super.mapToJson(product);
		
		MvcResult mvcRS = mockMvc.perform(MockMvcRequestBuilders.put(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(prodJson)).andReturn();
		
		int status = mvcRS.getResponse().getStatus();
		
		String content = mvcRS.getResponse().getContentAsString();
		
		assertEquals(200, status);
		assertEquals(content,"Product updated succesfully");
	}

	@Test
	@Order(10)
	@DisplayName("Update product that cannot be found")
	void testUpdateProductNotFound() throws Exception {
		Product product = new Product(product_id,"newmockProduct", 200, 300);
		// convert product object into json format
		String prodJson = super.mapToJson(product);
		
		MvcResult mvcRS = mockMvc.perform(MockMvcRequestBuilders.put(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(prodJson)).andReturn();
		
		int status = mvcRS.getResponse().getStatus();
		
		String content = mvcRS.getResponse().getContentAsString();
		
		assertEquals(204, status);
		assertEquals(content,"No product to update");
	}
	
	@Test
	@Order(5)
	@DisplayName("Update product in db with price below 0")
	void testUpdateProductPriceBelowZero() throws Exception {
		Product product = new Product(product_id,"newmockProduct", -200, 300);
		// convert product object into json format
		String prodJson = super.mapToJson(product);
		
		MvcResult mvcRS = mockMvc.perform(MockMvcRequestBuilders.put(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(prodJson)).andReturn();
		
		int status = mvcRS.getResponse().getStatus();
		
		String content = mvcRS.getResponse().getContentAsString();
		
		assertEquals(406, status);
		assertEquals(content,"Product proce of QoH cannot be negative");
	}
	
	@Test
	@Order(6)
	@DisplayName("Get product by Id")
	void testGetProduct() throws Exception {
		Product product = new Product(product_id,"newmockProduct", 200, 300);
		MvcResult mvcRS = mockMvc.perform(MockMvcRequestBuilders.get(uri + "/" +product_id)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		int status = mvcRS.getResponse().getStatus();		
		String content = mvcRS.getResponse().getContentAsString();
		
		Product returnedProduct = super.mapFromJson(content, Product.class);
		
		assertEquals(200, status);
		assertEquals(product, returnedProduct);
		
	}
	
	@Test
	@Order(7)
	@DisplayName("Get All products in db")
	void testGetAllProducts() throws Exception {
		MvcResult mvcRS = mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		int status = mvcRS.getResponse().getStatus();		
		String content = mvcRS.getResponse().getContentAsString();
		
		Product[] products = super.mapFromJson(content, Product[].class);
		
		assertEquals(200, status);
		assertTrue(products.length > 0);
	}
	
	@Test
	@Order(8)
	@DisplayName("Delete product from db")
	void testDeleteProduct() throws Exception {
		MvcResult mvcRS = mockMvc.perform(MockMvcRequestBuilders.delete(uri + "/" +product_id)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		int status = mvcRS.getResponse().getStatus();		
		String content = mvcRS.getResponse().getContentAsString();
		
		assertEquals(200,status);
		assertEquals(content,"Product with id: " + product_id + " was deleted");
	}
	
	@Test
	@Order(9)
	@DisplayName("Delete product that cannot be found")
	void testDeleteNotFoundProduct() throws Exception {
		MvcResult mvcRS = mockMvc.perform(MockMvcRequestBuilders.delete(uri + "/" +product_id)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		int status = mvcRS.getResponse().getStatus();		
		String content = mvcRS.getResponse().getContentAsString();
		
		assertEquals(404,status);
		assertEquals(content,"Product was not found");
	}

}
