package com.rodrigoschonardt.orderservice.service;

import com.rodrigoschonardt.orderservice.dto.AddOrderData;
import com.rodrigoschonardt.orderservice.model.Order;
import com.rodrigoschonardt.orderservice.model.OrderItem;
import com.rodrigoschonardt.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Stream;

@Service
public class OrderService
{
    @Autowired
    private OrderRepository repository;

    public Order placeOrder( AddOrderData orderData )
    {
        Order order = new Order();

        order.setOrderNumber( UUID.randomUUID().toString() );
        order.setOrderItems( orderData.orderItems().stream().map( OrderItem::new ).toList() );

        repository.save( order );

        return order;
    }
}
