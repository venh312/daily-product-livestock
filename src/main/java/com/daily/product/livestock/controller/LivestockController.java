package com.daily.product.livestock.controller;

import com.daily.product.livestock.dto.*;
import com.daily.product.livestock.service.LivestockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.List;

@RestController
@RequestMapping("/livestock")
@RequiredArgsConstructor
@Tag(name = "생필품 농수축산물", description = "생필품 농수축산물 API 명세서")
public class LivestockController {
    private final LivestockService livestockService;

    @Operation(summary = "[자치구] 목록 조회")
    @GetMapping("/autonomous")
    public ResponseEntity<Mono<List<LivestockGroupByAutonomousDto>>> getAutonomous() {
        return ResponseEntity.ok(livestockService.groupByAutonomous());
    }

    @Operation(summary = "[자치구] 시장/마트 목록 조회")
    @GetMapping("/autonomous/{autonomousCode}")
    public ResponseEntity<Mono<List<LivestockPlaceDto>>> getPlaceList(
            @Parameter(description = "자치구 코드")
            @PathVariable String autonomousCode) {
        return ResponseEntity.ok(livestockService.getPlaceList(autonomousCode));
    }

    @Operation(summary = "[시장/마트] 목록 조회")
    @GetMapping("/place")
    public ResponseEntity<Mono<List<LivestockGroupByPlaceDto>>> getPlace() {
        return ResponseEntity.ok(livestockService.groupByPlace());
    }

    @Operation(summary = "[시장/마트] 상품 조회")
    @GetMapping("/place/{placeCode}")
    public Mono<Page<LivestockPlaceProductDto>> getPlaceProductList(
            @Parameter(description = "시장/마트 코드")
            @PathVariable String placeCode,
            @Parameter(description = "정렬 컬럼 Default: checkDate")
            @RequestParam(required = false, defaultValue = "id") String sortColumn,
            @Parameter(description = "정렬 순서 Default:DESC (DESC:내림차순, ASC:오름차순)")
            @RequestParam(required = false, defaultValue = "DESC") String sortType,
            @Parameter(description = "페이지 번호 (0:1페이지, 1:2페이지)")
            @RequestParam(required = false, defaultValue = "0") int page,
            @Parameter(description = "페이지 사이즈")
            @RequestParam(required = false, defaultValue = "8") int pageSize) {
        return livestockService.getPlaceProductList(placeCode, sortColumn, sortType, PageRequest.of(page, pageSize));
    }

    @Operation(summary = "[상품] 목록 조회")
    @GetMapping("/product")
    public ResponseEntity<Mono<List<LivestockProductDto>>> groupByProduct() {
        return ResponseEntity.ok(livestockService.groupByProduct());
    }

    @Operation(summary = "[상품] 상세 조회")
    @GetMapping("/product/{productCode}")
    public Mono<List<LivestockProductInfoDto>> getProductInfoList(
            @Parameter(description = "상품 코드")
            @PathVariable String productCode) {
        return livestockService.getProductInfoList(productCode);
    }
}
