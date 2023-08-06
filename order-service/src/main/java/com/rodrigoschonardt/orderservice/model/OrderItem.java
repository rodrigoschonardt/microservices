package com.rodrigoschonardt.orderservice.model;

import com.rodrigoschonardt.orderservice.dto.AddOrderData;
import com.rodrigoschonardt.orderservice.dto.AddOrderItemData;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table( name = "items" )
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode( of = "id" )
public class OrderItem
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

    public OrderItem( AddOrderItemData orderItemData )
    {
        this.quantity = orderItemData.quantity();
        this.skuCode = orderItemData.skuCode();
        this.price = orderItemData.price();
    }
}
