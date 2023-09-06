package com.example.springresttemplateclient.naver.controller;

import com.example.springresttemplateclient.naver.dto.ItemDto;
import com.example.springresttemplateclient.naver.service.NaverApiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NaverApiController {
    private final NaverApiService naverApiService;

    public NaverApiController(NaverApiService naverApiService) {
        this.naverApiService = naverApiService;
    }

    @GetMapping("/search")
    public List<ItemDto> searchItem(@RequestParam String query) {
        return naverApiService.searchItems(query);
    }
}
