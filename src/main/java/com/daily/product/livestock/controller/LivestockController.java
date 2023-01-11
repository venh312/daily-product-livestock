package com.daily.product.livestock.controller;

import com.daily.product.livestock.dto.LivestockResultDto;
import com.daily.product.livestock.service.LivestockService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/livestock")
@RequiredArgsConstructor
public class LivestockController {
    private final LivestockService livestockService;

    @GetMapping
    public Mono<Page<LivestockResultDto>> findAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "6") int pageSize) {
        return livestockService.findAll(PageRequest.of(page, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<LivestockResultDto>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(livestockService.findById(id));
    }
}
