package com.example.jpaadvance.repository;

import com.example.jpaadvance.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
