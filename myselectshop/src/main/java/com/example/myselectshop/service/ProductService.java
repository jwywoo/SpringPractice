package com.example.myselectshop.service;

import com.example.myselectshop.dto.ProductMypriceRequestDto;
import com.example.myselectshop.dto.ProductRequestDto;
import com.example.myselectshop.dto.ProductResponseDto;
import com.example.myselectshop.entity.Product;
import com.example.myselectshop.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public static final int MIN_MY_PRICE = 100;

    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        return new ProductResponseDto(productRepository.save(new Product(requestDto)));
    }

    public ProductResponseDto updateProduct(Long id, ProductMypriceRequestDto requestDto) {
        int myPrice = requestDto.getMyprice();
        if (myPrice < MIN_MY_PRICE) {
            throw new IllegalArgumentException("Got to be higher than" + MIN_MY_PRICE);
        }
        Product product = productRepository.findById(id).orElseThrow(() -> new NullPointerException("Product is not valid"));
        product.update(requestDto);
        return new ProductResponseDto(product);
    }
}
