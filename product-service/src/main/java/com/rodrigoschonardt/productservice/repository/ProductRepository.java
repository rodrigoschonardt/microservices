package com.rodrigoschonardt.productservice.repository;

import com.rodrigoschonardt.productservice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository
    extends
        MongoRepository<Product, Long>
{
    Page<Product> findAll( Pageable page );
}
