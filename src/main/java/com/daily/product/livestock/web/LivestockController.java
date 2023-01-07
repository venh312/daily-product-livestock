package com.daily.product.livestock.web;

import com.daily.product.livestock.domain.livestock.Livestock;
import com.daily.product.livestock.service.LivestockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/livestock")
@RequiredArgsConstructor
public class LivestockController {
    private final LivestockService livestockService;

    @GetMapping
    public ResponseEntity<Mono<List<Livestock>>> getAll() {
        return ResponseEntity.ok(livestockService.findAll().collectList());
    }
}
