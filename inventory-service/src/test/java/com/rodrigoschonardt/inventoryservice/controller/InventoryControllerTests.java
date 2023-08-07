package com.rodrigoschonardt.inventoryservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigoschonardt.inventoryservice.dto.InventoryStatusData;
import com.rodrigoschonardt.inventoryservice.model.InventoryItem;
import com.rodrigoschonardt.inventoryservice.repository.InventoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Testcontainers
public class InventoryControllerTests
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private InventoryRepository repository;

    static MySQLContainer container = new MySQLContainer( "mysql:8.0.24" );

    static
    {
        container.start();
    }

    @DynamicPropertySource
    private static void setupProperties( DynamicPropertyRegistry registry )
    {
        registry.add( "spring.datasource.url", container::getJdbcUrl );
        registry.add( "spring.datasource.username", container::getUsername );
        registry.add( "spring.datasource.password", container::getPassword );
    }

    @Test
    public void shouldReturnQuantityTen() throws Exception
    {
        InventoryItem inventoryItem = new InventoryItem();

        String skuCode = "test-code";
        Integer quantity = 10;

        inventoryItem.setSkuCode( skuCode );
        inventoryItem.setQuantity( quantity );

        repository.save( inventoryItem );

        MockHttpServletResponse response = mockMvc.perform( MockMvcRequestBuilders.get( "/api/inventory-items" ).param( "sku-code", skuCode ) )
                                                  .andExpect( status().isOk() )
                                                  .andReturn()
                                                  .getResponse();

        List<InventoryStatusData> inventoryStatusData = mapper.readValue( response.getContentAsString(),  new TypeReference<List<InventoryStatusData>>(){} );

        Assertions.assertEquals( inventoryStatusData.get( 0 ).quantity(), quantity );
    }

    @Test
    public void shouldReturnQuantityZeroBecauseTheItemIsNull() throws Exception
    {
        InventoryItem inventoryItem = new InventoryItem();

        String skuCode = "test-unavailable-code";

        MockHttpServletResponse response = mockMvc.perform( MockMvcRequestBuilders.get( "/api/inventory-items" ).param( "sku-code", skuCode ) )
                                                  .andExpect( status().isOk() )
                                                  .andReturn()
                                                  .getResponse();

        List<InventoryStatusData> inventoryStatusData = mapper.readValue( response.getContentAsString(),  new TypeReference<List<InventoryStatusData>>(){} );

        Assertions.assertEquals( inventoryStatusData.get( 0 ).quantity(), 0 );
    }

    @Test
    public void shouldReturnQuantityZero() throws Exception
    {
        InventoryItem inventoryItem = new InventoryItem();

        String skuCode = "test-zero-code";
        Integer quantity = 0;

        inventoryItem.setSkuCode( skuCode );
        inventoryItem.setQuantity( quantity );

        repository.save( inventoryItem );

        MockHttpServletResponse response = mockMvc.perform( MockMvcRequestBuilders.get( "/api/inventory-items" ).param( "sku-code", skuCode ) )
                                                  .andExpect( status().isOk() )
                                                  .andReturn()
                                                  .getResponse();

        List<InventoryStatusData> inventoryStatusData = mapper.readValue( response.getContentAsString(),  new TypeReference<List<InventoryStatusData>>(){} );

        Assertions.assertEquals( inventoryStatusData.get( 0 ).quantity(), quantity );
    }
}
