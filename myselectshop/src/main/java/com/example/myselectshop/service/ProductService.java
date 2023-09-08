package com.example.myselectshop.service;

import com.example.myselectshop.dto.ProductMypriceRequestDto;
import com.example.myselectshop.dto.ProductRequestDto;
import com.example.myselectshop.dto.ProductResponseDto;
import com.example.myselectshop.entity.Product;
import com.example.myselectshop.entity.User;
import com.example.myselectshop.entity.UserRoleEnum;
import com.example.myselectshop.naver.dto.ItemDto;
import com.example.myselectshop.repository.ProductRepository;
import com.example.myselectshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public static final int MIN_MY_PRICE = 100;

    public ProductResponseDto createProduct(ProductRequestDto requestDto, User user) {
        return new ProductResponseDto(productRepository.save(new Product(requestDto, user)));
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

    public Page<ProductResponseDto> productList(User user, int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        UserRoleEnum userRoleEnum = user.getRole();
        Page<Product> productList;
        if (userRoleEnum==UserRoleEnum.USER) {
            productList = productRepository.findAllByUser(user, pageable);
        } else {
            productList = productRepository.findAll(pageable);
        }
        return productList.map(ProductResponseDto::new);
//        return productList.stream().map(ProductResponseDto::new).toList();
//        return productRepository.findAllByOrderByModifiedAtDesc().stream().map(ProductResponseDto::new).toList();
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
