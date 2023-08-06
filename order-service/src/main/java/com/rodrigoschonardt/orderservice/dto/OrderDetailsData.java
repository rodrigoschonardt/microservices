package com.rodrigoschonardt.orderservice.dto;

import com.rodrigoschonardt.orderservice.model.Order;

import java.util.List;

public record OrderDetailsData
(
    Long id,
    String orderNumber,
    List<OrderItemDetailsData> orderItems
)
{
    public OrderDetailsData( Order order )
    {
        this( order.getId(), order.getOrderNumber(),
                order.getOrderItems().stream().map( OrderItemDetailsData::new ).toList() );
    }
}
