package com.daily.product.livestock.domain.livestock;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LivestockRepository extends ReactiveCrudRepository<Livestock, String> {
}
