package com.rodrigoschonardt.orderservice.dto;

import com.rodrigoschonardt.orderservice.model.OrderItem;

import java.math.BigDecimal;

public record OrderItemDetailsData
(
    Long id,
    String skuCode,
    BigDecimal price,
    Integer quantity
)
{
    public OrderItemDetailsData( OrderItem orderItem )
    {
        this( orderItem.getId(), orderItem.getSkuCode(),
                orderItem.getPrice(), orderItem.getQuantity() );
    }
}
