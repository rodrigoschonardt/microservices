package com.rodrigoschonardt.orderservice.controller;

import com.rodrigoschonardt.orderservice.dto.AddOrderData;
import com.rodrigoschonardt.orderservice.dto.OrderDetailsData;
import com.rodrigoschonardt.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping( "/api/orders" )
public class OrderController
{
    @Autowired
    private OrderService service;

    @PostMapping
    @Transactional
    @CircuitBreaker( name = "inventory", fallbackMethod = "fallBackMethod" )
    @TimeLimiter( name = "inventory", fallbackMethod = "fallBackTimeoutMethod" )
    @Retry( name = "inventory" )
    public CompletableFuture<ResponseEntity> add(@RequestBody @Valid AddOrderData orderData )
    {
        return CompletableFuture.supplyAsync( () ->
        {
            return ResponseEntity.status( HttpStatus.CREATED ).body( new OrderDetailsData( service.placeOrder( orderData ) ) );
        } );
    }

    public CompletableFuture<ResponseEntity> fallBackMethod( AddOrderData orderData, RuntimeException exception )
    {
        return CompletableFuture.supplyAsync( () -> ResponseEntity.badRequest().body( "Something went wrong, try again later!" ) );
    }

    public CompletableFuture<ResponseEntity> fallBackTimeoutMethod( AddOrderData orderData, TimeoutException exception )
    {
        return CompletableFuture.supplyAsync( () -> ResponseEntity.status( HttpStatus.REQUEST_TIMEOUT ).body( "Something went wrong, try again later!" ) );
    }
}
