package com.rodrigoschonardt.productservice.service;

import com.rodrigoschonardt.productservice.dto.AddProductData;
import com.rodrigoschonardt.productservice.model.Product;
import com.rodrigoschonardt.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductService
{
    @Autowired
    private ProductRepository repository;

    public Product createProduct( AddProductData productData )
    {
        Product product = Product.builder()
                                 .name( productData.name() )
                                 .description( productData.description() )
                                 .price( productData.price() )
                                 .build();

        repository.save( product );

        log.info( "Product saved: {}", product );

        return product;
    }
}
