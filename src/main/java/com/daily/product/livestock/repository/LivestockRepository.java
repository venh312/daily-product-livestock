package com.daily.product.livestock.repository;

import com.daily.product.livestock.domain.livestock.Livestock;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

public interface LivestockRepository extends ReactiveSortingRepository<Livestock, Long> {
    @Query("SELECT autonomous_code, autonomous_name FROM livestock GROUP BY autonomous_code, autonomous_name")
    Flux<Livestock> groupByAutonomous();
    @Query("SELECT place_code, place_name FROM livestock WHERE autonomous_code = :autonomousCode AND market_type_code = :marketTypeCode GROUP BY place_code, place_name")
    Flux<Livestock> getPlaceList(@Param("autonomousCode") String autonomousCode, @Param("marketTypeCode") String marketTypeCode);
    @Query("SELECT place_code, place_name FROM livestock GROUP BY place_code, place_name")
    Flux<Livestock> groupByPlace();

    @Query("SELECT ANY_VALUE(place_code) place_code, ANY_VALUE(place_name) place_name, product_code, ANY_VALUE(product_name) product_name, standard, " +
            "AVG(price) price, remarks, ANY_VALUE(autonomous_code) autonomous_code, ANY_VALUE(autonomous_name) autonomous_name, check_date " +
            "FROM livestock " +
            "WHERE autonomous_code = :autonomousCode AND place_code = :placeCode " +
            "GROUP BY product_code, standard, remarks, check_date " +
            "ORDER BY check_date DESC, price ASC LIMIT :limit OFFSET :offset")
    Flux<Livestock> getPlaceProductList(@Param("autonomousCode") String autonomousCode, @Param("placeCode") String placeCode, @Param("limit") int limit, @Param("offset") int offset);

    @Query("SELECT product_code, product_name FROM livestock GROUP BY product_code, product_name")
    Flux<Livestock> groupByProduct();

    @Query("SELECT place_code, ANY_VALUE(place_name) place_name, ANY_VALUE(product_code) product_code, ANY_VALUE(product_name) product_name, standard, " +
            "AVG(price) price, remarks, ANY_VALUE(autonomous_code) autonomous_code, ANY_VALUE(autonomous_name) autonomous_name, check_date " +
            "FROM livestock " +
            "WHERE autonomous_code = :autonomousCode AND product_name LIKE CONCAT(:productName,'%') " +
            "GROUP BY place_code, standard, remarks, check_date " +
            "ORDER BY check_date DESC, price ASC LIMIT :limit OFFSET :offset")
    Flux<Livestock> getProductInfoList(@Param("autonomousCode") String autonomousCode, @Param("productName") String productName, @Param("limit") int limit, @Param("offset") int offset);

    @Query("SELECT place_code, ANY_VALUE(place_name) place_name, ANY_VALUE(product_code) product_code, ANY_VALUE(product_name) product_name, standard, " +
            "AVG(price) price, remarks, ANY_VALUE(autonomous_code) autonomous_code, ANY_VALUE(autonomous_name) autonomous_name, check_date " +
            "FROM livestock " +
            "WHERE autonomous_code = :autonomousCode AND (place_name LIKE CONCAT('%',:search,'%') OR product_name LIKE CONCAT('%',:search,'%')) " +
            "GROUP BY place_code, standard, remarks, check_date " +
            "ORDER BY check_date DESC, price ASC LIMIT :limit OFFSET :offset")
    Flux<Livestock> getPlaceOrProductList(@Param("autonomousCode") String autonomousCode, @Param("search") String search, @Param("limit") int limit, @Param("offset") int offset);
}
