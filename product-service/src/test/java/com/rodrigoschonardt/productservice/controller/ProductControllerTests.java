package com.rodrigoschonardt.productservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigoschonardt.productservice.dto.AddProductData;
import com.rodrigoschonardt.productservice.repository.ProductRepository;
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
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class ProductControllerTests
{
	static MongoDBContainer container = new MongoDBContainer( "mongo:4.4.2" );

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private ProductRepository repository;

	static
	{
		container.start();
	}

	@DynamicPropertySource
	static void setupProperties( DynamicPropertyRegistry properties )
	{
		properties.add( "spring.data.mongodb.uri", ()-> container.getReplicaSetUrl( "embedded" ) );
	}

	@Test
	void shouldCreateProduct() throws Exception
	{
		AddProductData data = new AddProductData( "test", "test description", new BigDecimal( 10 ) );

		mockMvc.perform( MockMvcRequestBuilders.post( "/api/products" )
											   .contentType( MediaType.APPLICATION_JSON )
											   .content( mapper.writeValueAsString( data ) ) ).andExpect( status().isCreated() );

		Assertions.assertFalse( repository.findAll().isEmpty() );
	}
}
