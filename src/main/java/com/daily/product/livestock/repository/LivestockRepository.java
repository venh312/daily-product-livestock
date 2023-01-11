package com.daily.product.livestock.repository;

import com.daily.product.livestock.domain.livestock.Livestock;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

public interface LivestockRepository extends ReactiveSortingRepository<Livestock, Long> {
    Flux<Livestock> findAllBy(Pageable pageable);
}
