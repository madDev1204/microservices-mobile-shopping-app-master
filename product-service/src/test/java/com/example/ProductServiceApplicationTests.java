package com.example;

//import com.example.dto.ProductRequest;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.testcontainers.containers.MongoDBContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;

//import java.math.BigDecimal;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.dto.ProductRequest;
import com.example.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
	//configuration of docker container for mongodb
//	@Container
//	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0.5");
//
//	@Autowired
//	private MockMvc mockMvc;
//	//configuring the url of the mongodb container!
//
//	@Autowired
//	private ObjectMapper objectMapper;  //to convert from json to object and visa versa
//
//	@Autowired
//	private ProductRepository productRepository;
//
//	static {
//		mongoDBContainer.start();
//	}
//	@DynamicPropertySource
//	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
//		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
//	}
//
//
//	@Test
//	void shouldCreateProduct() throws Exception {
//		ProductRequest productRequest = getProductRequest();
//		//converting product request object to JSON string bcoz content type is JSON
//		String productRequestString = objectMapper.writeValueAsString(productRequest);
//		//MOCKITO USED HERE CHECK IT OUT
//		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(productRequestString))
//				.andExpect(status().isCreated());
//	}
//
//	//write more tests!!!!!
//	private ProductRequest getProductRequest() {
//		return ProductRequest.builder()
//				.name("iPhone 12")
//				.description("iPhone 12")
//				.price(BigDecimal.valueOf(1500))
//				.build();
//	}

}
