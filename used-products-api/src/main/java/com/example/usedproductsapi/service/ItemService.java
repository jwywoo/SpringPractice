package com.example.usedproductsapi.service;

import com.example.usedproductsapi.dto.ItemRequestDto;
import com.example.usedproductsapi.dto.ItemResponseDto;
import com.example.usedproductsapi.dto.ItemUpdateRequestDto;
import com.example.usedproductsapi.dto.StringResponseDto;
import com.example.usedproductsapi.entity.Item;
import com.example.usedproductsapi.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemResponseDto itemCreate(ItemRequestDto requestDto) {
        Item newItem = new Item(requestDto);
        return new ItemResponseDto(itemRepository.save(newItem));
    }

    public List<ItemResponseDto> itemList() {
        return itemRepository.findAllByOrderByIdDesc().stream().map(ItemResponseDto::new).toList();
    }

    public ItemResponseDto itemDetail(Long id) {
        return new ItemResponseDto(findById(id));
    }

    @Transactional
    public ItemResponseDto itemUpdate(Long id, ItemUpdateRequestDto requestDto) {
        Item item = findById(id);
        System.out.println(item.getTitle());
        item.update(requestDto);
        System.out.println(item.getTitle());
        return new ItemResponseDto(item);
    }

    public StringResponseDto itemDelete(Long id) {
        Item item = findById(id);
        itemRepository.delete(item);
        return new StringResponseDto("삭제성공");
    }

    private Item findById(Long id) {
        return itemRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid item")
        );
    }
}
