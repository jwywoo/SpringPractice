package com.example.usedproductsapi.controller;

import com.example.usedproductsapi.dto.ItemRequestDto;
import com.example.usedproductsapi.dto.ItemResponseDto;
import com.example.usedproductsapi.dto.ItemUpdateRequestDto;
import com.example.usedproductsapi.dto.StringResponseDto;
import com.example.usedproductsapi.service.ItemService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/post")
    public ItemResponseDto itemCreate(@RequestBody ItemRequestDto requestDto) {
        return itemService.itemCreate(requestDto);
    }

    @GetMapping("/posts")
    public List<ItemResponseDto> itemList() {
        return itemService.itemList();
    }

    @GetMapping("/post/{id}")
    public ItemResponseDto itemDetail(@PathVariable Long id) {
        return itemService.itemDetail(id);
    }

    @PutMapping("/post/{id}")
    public ItemResponseDto itemUpdate(@PathVariable Long id, @RequestBody ItemUpdateRequestDto requestDto) {
        return itemService.itemUpdate(id, requestDto);
    }

    @DeleteMapping("/post/{id}")
    public StringResponseDto itemDelete(@PathVariable Long id) {
        return itemService.itemDelete(id);
    }
}
