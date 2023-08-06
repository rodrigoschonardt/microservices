package com.rodrigoschonardt.orderservice.controller;

import com.rodrigoschonardt.orderservice.dto.AddOrderData;
import com.rodrigoschonardt.orderservice.dto.OrderDetailsData;
import com.rodrigoschonardt.orderservice.model.Order;
import com.rodrigoschonardt.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/orders" )
public class OrderController
{
    @Autowired
    private OrderService service;

    @PostMapping
    @Transactional
    public ResponseEntity add( @RequestBody @Valid AddOrderData orderData )
    {
        Order order = service.placeOrder( orderData );

        return ResponseEntity.status( HttpStatus.CREATED ).body( new OrderDetailsData( order ) );
    }
}
