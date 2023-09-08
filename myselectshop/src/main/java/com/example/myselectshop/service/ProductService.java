package com.example.myselectshop.service;

import com.example.myselectshop.dto.ProductMypriceRequestDto;
import com.example.myselectshop.dto.ProductRequestDto;
import com.example.myselectshop.dto.ProductResponseDto;
import com.example.myselectshop.entity.Product;
import com.example.myselectshop.naver.dto.ItemDto;
import com.example.myselectshop.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public static final int MIN_MY_PRICE = 100;

    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        return new ProductResponseDto(productRepository.save(new Product(requestDto)));
    }

    @Transactional
    public ProductResponseDto updateProduct(Long id, ProductMypriceRequestDto requestDto) {
        int myPrice = requestDto.getMyprice();
        if (myPrice < MIN_MY_PRICE) {
            throw new IllegalArgumentException("Got to be higher than" + MIN_MY_PRICE);
        }
        Product product = productRepository.findById(id).orElseThrow(() -> new NullPointerException("Product is not valid"));
        product.update(requestDto);
        return new ProductResponseDto(product);
    }

    public List<ProductResponseDto> productList() {
        return productRepository.findAllByOrderByModifiedAtDesc().stream().map(ProductResponseDto::new).toList();
//        List<Product> productList = productRepository.findAll();
//        List<ProductResponseDto> responseDtoList = new ArrayList<>();
//        for (Product product: productList) {
//            responseDtoList.add(new ProductResponseDto(product));
//        }
//        return responseDtoList;
    }

    @Transactional
    public void updateBySearch(Long id, ItemDto itemDto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NullPointerException("Not a valid product"));
        product.updateByItemDto(itemDto);
    }
}
