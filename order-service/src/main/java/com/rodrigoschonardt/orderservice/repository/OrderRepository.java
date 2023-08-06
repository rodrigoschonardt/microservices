package com.rodrigoschonardt.orderservice.repository;

import com.rodrigoschonardt.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository
    extends
        JpaRepository<Order, Long>
{
}
