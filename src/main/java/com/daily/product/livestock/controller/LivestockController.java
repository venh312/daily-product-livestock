package com.daily.product.livestock.controller;

import com.daily.product.livestock.dto.*;
import com.daily.product.livestock.service.LivestockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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

    @Operation(summary = "[자치구] 목록 조회", description = "서울시 자치구 전체 목록을 불러온다.")
    @GetMapping("/autonomous")
    public ResponseEntity<Mono<List<LivestockGroupByAutonomousDto>>> getAutonomous() {
        return ResponseEntity.ok(livestockService.groupByAutonomous());
    }

    @Operation(summary = "[자치구] 시장/마트 목록 조회", description = "자치구내에 시장 또는 마트 목록을 불러온다.")
    @GetMapping("/autonomous/{autonomousCode}/{marketTypeCode}")
    public ResponseEntity<Mono<List<LivestockPlaceDto>>> getPlaceList(
            @Parameter(description = "자치구 코드")
            @PathVariable String autonomousCode,
            @Parameter(description = "마트/시장 구분 코드 1:시장,2:마트")
            @PathVariable String marketTypeCode) {
        return ResponseEntity.ok(livestockService.getPlaceList(autonomousCode, marketTypeCode));
    }

    @Operation(summary = "[시장/마트] 목록 조회", description = "시장/마트 전체 목록을 조회한다.")
    @GetMapping("/place")
    public ResponseEntity<Mono<List<LivestockGroupByPlaceDto>>> getPlace() {
        return ResponseEntity.ok(livestockService.groupByPlace());
    }

    @Operation(summary = "[시장/마트] 상품 목록 조회", description = "자치구 > 시장/마트 > 상품 목록을 조회한다.")
    @GetMapping("/place/{autonomousCode}/{placeCode}/{page}/{pageSize}")
    public Mono<List<LivestockProductInfoDto>> getPlaceProductList(
            @Parameter(description = "자치구 코드")
            @PathVariable String autonomousCode,
            @Parameter(description = "시장/마트 코드")
            @PathVariable String placeCode,
            @Parameter(description = "페이지 번호 (Ex.페이지 번호*페이지 사이즈, 0부터 시작)")
            @PathVariable int page,
            @Parameter(description = "페이지 사이즈 (고정)")
            @PathVariable int pageSize) {
        return livestockService.getPlaceProductList(autonomousCode, placeCode, pageSize, page);
    }

    @Operation(summary = "[상품] 목록 조회", description = "전체 상품 목록을 조회한다.")
    @GetMapping("/product")
    public ResponseEntity<Mono<List<LivestockProductDto>>> groupByProduct() {
        return ResponseEntity.ok(livestockService.groupByProduct());
    }

    @Operation(summary = "[상품] 상세 조회", description = "자치구를 기준으로 입력한 상품을 조회한다.")
    @GetMapping("/product/{autonomousCode}/{productName}/{page}/{pageSize}")
    public Mono<List<LivestockProductInfoDto>> getProductInfoList(
            @Parameter(description = "자치구 코드")
            @PathVariable String autonomousCode,
            @Parameter(description = "상품 이름")
            @PathVariable String productName,
            @Parameter(description = "페이지 번호 (Ex.페이지 번호*페이지 사이즈, 0부터 시작)")
            @PathVariable int page,
            @Parameter(description = "페이지 사이즈 (고정)")
            @PathVariable int pageSize) {
        return livestockService.getProductInfoList(autonomousCode, productName, pageSize, page);
    }
    @Operation(summary = "[마트,상품] 검색 조회", description = "자치구를 기준으로 입력한 검색 내역을 조회한다.")
    @GetMapping("/search/{autonomousCode}/{search}/{page}/{pageSize}")
    public Mono<List<LivestockProductInfoDto>> getPlaceOrProductList(
            @Parameter(description = "자치구 코드")
            @PathVariable String autonomousCode,
            @Parameter(description = "검색명")
            @PathVariable String search,
            @Parameter(description = "페이지 번호 (Ex.페이지 번호*페이지 사이즈, 0부터 시작)")
            @PathVariable int page,
            @Parameter(description = "페이지 사이즈 (고정)")
            @PathVariable int pageSize) {
        return livestockService.getPlaceOrProductList(autonomousCode, search, pageSize, page);
    }
}
