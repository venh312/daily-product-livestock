package com.daily.product.livestock.service;

import com.daily.product.livestock.domain.livestock.Livestock;
import com.daily.product.livestock.domain.livestock.LivestockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Service
public class LivestockService {
    private final LivestockRepository livestockRepository;

    public Flux<Livestock> findAll() {
        return livestockRepository.findAll();
    }

}
