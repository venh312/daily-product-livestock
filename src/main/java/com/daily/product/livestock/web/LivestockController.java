package com.daily.product.livestock.web;

import com.daily.product.livestock.domain.livestock.Livestock;
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
    public Mono<Page<Livestock>> findAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "8") int pageSize) {
        return livestockService.findAll(PageRequest.of(page, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Livestock>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(livestockService.findById(id));
    }
}
