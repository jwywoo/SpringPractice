package com.example.usedproductsapi.dto;

import lombok.Getter;

@Getter
public class ItemUpdateRequestDto {
    private String title;
    private String content;
    private int price;
    private String username;
}
