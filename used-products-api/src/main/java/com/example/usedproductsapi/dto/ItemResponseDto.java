package com.example.usedproductsapi.dto;

import com.example.usedproductsapi.entity.Item;
import lombok.Getter;

@Getter
public class ItemResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final int price;
    private final String username;

    public ItemResponseDto(Item item) {
        this.id = item.getId();
        this.title = item.getTitle();
        this.content = item.getContent();
        this.price = item.getPrice();
        this.username = item.getUsername();
    }
}
