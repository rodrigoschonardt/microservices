package com.rodrigoschonardt.orderservice.service;

import com.rodrigoschonardt.orderservice.dto.AddOrderData;
import com.rodrigoschonardt.orderservice.dto.InventoryStatusData;
import com.rodrigoschonardt.orderservice.model.Order;
import com.rodrigoschonardt.orderservice.model.OrderItem;
import com.rodrigoschonardt.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class OrderService
{
    @Autowired
    private OrderRepository repository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Order placeOrder( AddOrderData orderData )
    {
        Order order = new Order();

        order.setOrderNumber( UUID.randomUUID().toString() );
        order.setOrderItems( orderData.orderItems().stream().map( OrderItem::new ).toList() );

        List<String> codes = order.getOrderItems().stream().map( OrderItem::getSkuCode ).toList();

        List<InventoryStatusData> response = webClientBuilder.build().get()
                                 .uri( "http://inventory-service/api/inventory-items",
                                            uriBuilder -> uriBuilder.queryParam("sku-code", codes).build() )
                                 .retrieve()
                                 .bodyToMono( new ParameterizedTypeReference<List<InventoryStatusData>>() {} )
                                 .block();

        boolean allAvailable = response.stream().allMatch( l -> l.quantity() > 0 );

        if ( allAvailable )
        {
            repository.save( order );
            return order;
        }

        throw new IllegalArgumentException( "Product is not in stock!" );
    }
}
