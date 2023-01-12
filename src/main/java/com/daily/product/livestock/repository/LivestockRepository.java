package com.daily.product.livestock.repository;

import com.daily.product.livestock.domain.livestock.Livestock;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

public interface LivestockRepository extends ReactiveSortingRepository<Livestock, Long> {
    @Query("SELECT autonomous_code, autonomous_name FROM livestock GROUP BY autonomous_code")
    Flux<Livestock> groupByAutonomous();
    @Query("SELECT place_code, place_name FROM livestock WHERE autonomous_code = :autonomousCode GROUP BY place_code")
    Flux<Livestock> findByAutonomousCode(@Param("autonomousCode") String autonomousCode);
    @Query("SELECT place_code, place_name FROM livestock GROUP BY place_code")
    Flux<Livestock> groupByPlace();
    Flux<Livestock> findByPlaceCode(String placeCode, Pageable pageable);
    @Query("SELECT product_code, product_name FROM livestock GROUP BY product_code")
    Flux<Livestock> groupByProduct();

    /* 커버링 인덱스 (커버링 인덱스는 NoOffset 기법과 더불어 페이징 조회 성능을 향상시키는 가장 보편적인 방법)
    @Query("SELECT l.id,l.place_code,l.place_name,l.product_code,l.product_name,l.standard,l.autonomous_code,l.autonomous_name,l.check_date,GROUP_CONCAT(distinct l.price) price, GROUP_CONCAT(distinct l.remarks) \n" +
        "FROM livestock l -- WHERE product_code = '13' \n" +
        "JOIN (SELECT id FROM livestock ORDER BY id LIMIT 100000, 10) AS temp\n" +
        "ON temp.id = l.id\n" +
        "group by place_code,standard,check_date \n" +
        "ORDER BY id DESC")
     */
    @Query("SELECT place_code, place_name, product_code, product_name, standard, " +
            "price, remarks, autonomous_code, autonomous_name, check_date " +
            "FROM livestock " +
            "WHERE product_code = :productCode " +
            "GROUP BY place_code, check_date " +
            "ORDER BY check_date DESC")
    Flux<Livestock> getProductInfoList(@Param("productCode") String productCode);
}
