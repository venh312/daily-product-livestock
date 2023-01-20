package com.daily.product.livestock.service;

import com.daily.product.livestock.dto.*;
import com.daily.product.livestock.repository.LivestockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.List;

/*
@Transactional(readOnly=true)
더티체킹을 건너뛰는등 여러 최적화 작업이 일어나기 때문에 성능이 향상된다.
만약 DB가 master-slave 구조로 되어있다면 추가 설정을 통해서 CUD(create, update, delete) 는 master DB 로 가게 설정하고
Read 는 slave 로 가게 설정하여 workload를 분산할 수 있다. */
@Transactional(readOnly=true)
@RequiredArgsConstructor
@Service
public class LivestockService {
    private final LivestockRepository livestockRepository;

    public Mono<List<LivestockGroupByAutonomousDto>> groupByAutonomous() {
        return livestockRepository.groupByAutonomous()
            .map(LivestockGroupByAutonomousDto::new)
            .collectList();
    }

    public Mono<List<LivestockPlaceDto>> getPlaceList(String autonomousCode, String marketTypeCode) {
        return livestockRepository.getPlaceList(autonomousCode, marketTypeCode)
            .map(LivestockPlaceDto::new)
            .collectList();
    }

    public Mono<List<LivestockGroupByPlaceDto>> groupByPlace() {
        return livestockRepository.groupByPlace()
            .map(LivestockGroupByPlaceDto::new)
            .collectList();
    }

    public Mono<List<LivestockProductInfoDto>> getPlaceProductList(String autonomousCode, String placeCode, int limit, int offset) {
        return livestockRepository.getPlaceProductList(autonomousCode, placeCode, limit, offset)
            .map(LivestockProductInfoDto::new)
            .collectList();
    }

    public Mono<List<LivestockProductDto>> groupByProduct() {
        return livestockRepository.groupByProduct()
            .map(LivestockProductDto::new)
            .collectList();
    }

    public Mono<List<LivestockProductInfoDto>> getProductInfoList(String autonomousCode, String productName, int limit, int offset) {
        return livestockRepository.getProductInfoList(autonomousCode, productName, limit, offset)
            .map(LivestockProductInfoDto::new)
            .collectList();
    }

    public Mono<List<LivestockProductInfoDto>> getPlaceOrProductList(String autonomousCode, String search, int limit, int offset) {
        return livestockRepository.getPlaceOrProductList(autonomousCode, search, limit, offset)
            .map(LivestockProductInfoDto::new)
            .collectList();
    }
}
