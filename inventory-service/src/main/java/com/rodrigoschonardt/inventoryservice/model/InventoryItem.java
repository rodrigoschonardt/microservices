package com.rodrigoschonardt.inventoryservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table( name = "inventory_items" )
@Getter
@Setter
@EqualsAndHashCode( of = "id" )
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItem
{
    public enum Status { AVAILABLE, UNAVAILABLE }

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String skuCode;
    private Integer quantity;
}
