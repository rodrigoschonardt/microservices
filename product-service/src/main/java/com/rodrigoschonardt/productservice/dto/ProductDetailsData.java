package com.rodrigoschonardt.productservice.dto;

import com.rodrigoschonardt.productservice.model.Product;

import java.math.BigDecimal;

public record ProductDetailsData
(
    String id,
    String name,
    String description,
    BigDecimal price
)
{
    public ProductDetailsData( Product product )
    {
        this( product.getId(), product.getName(),
                product.getDescription(), product.getPrice() );
    }
}
