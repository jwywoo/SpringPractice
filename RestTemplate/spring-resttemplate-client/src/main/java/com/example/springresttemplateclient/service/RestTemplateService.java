package com.example.springresttemplateclient.service;

import com.example.springresttemplateclient.dto.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.util.List;

@Slf4j
@Service
public class RestTemplateService {
    private final RestTemplate restTemplate;

    public RestTemplateService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public ItemDto getCallObject(String query) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:7070")
                .path("/api/server/get-cal-obj")
                .queryParam("query", query)
                .encode()
                .build()
                .toUri();

        log.info("uri = " + uri);
        ResponseEntity<ItemDto> responseEntity = restTemplate.getForEntity(uri, ItemDto.class);
        log.info("statusCode = " + responseEntity.getStatusCode());

        return responseEntity.getBody();
    }

    public List<ItemDto> getCallList() {
        return null;
    }

    public ItemDto postCall(String query) {
        return null;
    }

    public List<ItemDto> exchangeCall(String token) {
        return null;
    }
}
