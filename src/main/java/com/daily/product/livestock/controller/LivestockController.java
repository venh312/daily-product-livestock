package com.daily.product.livestock.controller;

import com.daily.product.livestock.dto.*;
import com.daily.product.livestock.service.LivestockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/livestock")
@Api(value = "생필품 농수축산물 가격 정보", tags={"livestock"})
public class LivestockController {
    private final LivestockService livestockService;

    @GetMapping("/autonomous")
    @ApiOperation(value = "[자치구] 목록 조회")
    public ResponseEntity<Mono<List<LivestockGroupByAutonomousDto>>> getAutonomous() {
        return ResponseEntity.ok(livestockService.groupByAutonomous());
    }

    @GetMapping("/autonomous/{autonomousCode}")
    @ApiOperation(value = "[자치구] 시장/마트 목록 조회")
    public ResponseEntity<Mono<List<LivestockPlaceDto>>> getPlaceList(
            @ApiParam(value = "자치구 코드")
            @PathVariable String autonomousCode) {
        return ResponseEntity.ok(livestockService.getPlaceList(autonomousCode));
    }

    @GetMapping("/place")
    @ApiOperation(value = "[시장/마트] 목록 조회")
    public ResponseEntity<Mono<List<LivestockGroupByPlaceDto>>> getPlace() {
        return ResponseEntity.ok(livestockService.groupByPlace());
    }

    @GetMapping("/place/{placeCode}")
    @ApiOperation(value = "[시장/마트] 상품 조회")
    public Mono<Page<LivestockPlaceProductDto>> getPlaceProductList(
            @ApiParam(value = "시장/마트 코드")
            @PathVariable String placeCode,
            @ApiParam(value = "정렬 컬럼 Default: checkDate")
            @RequestParam(required = false, defaultValue = "id") String sortColumn,
            @ApiParam(value = "정렬 순서 Default:DESC, 구분: DESC/ASC")
            @RequestParam(required = false, defaultValue = "DESC") String sortType,
            @ApiParam(value = "페이지 번호")
            @RequestParam(required = false, defaultValue = "0") int page,
            @ApiParam(value = "페이지 사이즈")
            @RequestParam(required = false, defaultValue = "8") int pageSize) {
        return livestockService.getPlaceProductList(placeCode, sortColumn, sortType, PageRequest.of(page, pageSize));
    }

    @GetMapping("/product")
    @ApiOperation(value = "[상품] 목록 조회")
    public ResponseEntity<Mono<List<LivestockProductDto>>> groupByProduct() {
        return ResponseEntity.ok(livestockService.groupByProduct());
    }

    @GetMapping("/product/{productCode}")
    @ApiOperation(value = "[상품] 상세 조회")
    public Mono<List<LivestockProductInfoDto>> getProductInfoList(
            @ApiParam(value = "상품 코드")
            @PathVariable String productCode) {
        return livestockService.getProductInfoList(productCode);
    }
}
