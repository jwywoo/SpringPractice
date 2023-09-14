package com.example.usedproductsapi.repository;

import com.example.usedproductsapi.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
