package com.rodrigoschonardt.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table( name = "orders" )
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode( of = "id" )
public class Order
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;
    private String orderNumber;
    @OneToMany( cascade = CascadeType.ALL )
    private List<OrderItem> orderItems;
}
