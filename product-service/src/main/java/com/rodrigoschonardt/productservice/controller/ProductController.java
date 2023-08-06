package com.rodrigoschonardt.productservice.controller;

import com.rodrigoschonardt.productservice.dto.AddProductData;
import com.rodrigoschonardt.productservice.dto.ProductDetailsData;
import com.rodrigoschonardt.productservice.model.Product;
import com.rodrigoschonardt.productservice.repository.ProductRepository;
import com.rodrigoschonardt.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.stream.Stream;

@RestController
@RequestMapping( "/api/products" )
public class ProductController
{
    @Autowired
    private ProductService service;

    @Autowired
    private ProductRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity add( @RequestBody @Valid AddProductData productData, UriComponentsBuilder uriBuilder )
    {
        Product product = service.createProduct( productData );

        return ResponseEntity.status( HttpStatus.CREATED ).body( new ProductDetailsData( product ) );
    }

    @GetMapping
    public ResponseEntity<Page<ProductDetailsData>> getProducts( Pageable page )
    {
        Page<ProductDetailsData> products = repository.findAll( page )
                                                      .map( ProductDetailsData::new );

        return ResponseEntity.ok( products );
    }
}
