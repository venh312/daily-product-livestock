package com.daily.product.livestock.service;

import com.daily.product.livestock.domain.livestock.Livestock;
import com.daily.product.livestock.domain.livestock.LivestockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

/*
@Transactional(readOnly=true) 를 하면 더티체킹을 건너뛰는등 여러 최적화 작업이 일어나기 때문에 성능이 향상됩니다.
만약 DB가 master-slave 구조로 되어있다면 추가 설정을 통해서 CUD(create, update, delete) 는 master DB 로 가게 설정하고
Read 는 slave 로 가게 설정하여 workload를 분산할 수 있습니다
*/
@Transactional(readOnly=true)
@RequiredArgsConstructor
@Service
public class LivestockService {
    private final LivestockRepository livestockRepository;


    public Mono<Page<Livestock>> findAll(PageRequest pageRequest) {
        return livestockRepository.findAllBy(pageRequest.withSort(Sort.by("checkDate").descending()))
            .collectList()
            .zipWith(livestockRepository.count())
            .map(t -> new PageImpl<>(t.getT1(), pageRequest, t.getT2()));
    }

    public Mono<Livestock> findById(Long id) {
        return livestockRepository.findById(id);
    }
}
