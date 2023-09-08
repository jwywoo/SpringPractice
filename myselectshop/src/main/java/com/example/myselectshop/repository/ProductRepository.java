package com.example.myselectshop.repository;

import com.example.myselectshop.entity.Product;
import com.example.myselectshop.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByOrderByModifiedAtDesc();

    List<Product> findAllByUser(User user);
}
