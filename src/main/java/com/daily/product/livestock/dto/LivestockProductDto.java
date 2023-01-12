package com.daily.product.livestock.dto;

import com.daily.product.livestock.domain.livestock.Livestock;
import lombok.Getter;

@Getter
public class LivestockProductDto {
    private String productCode;
    private String productName;

    public LivestockProductDto(Livestock livestock) {
        this.productCode = livestock.getProductCode();
        this.productName = livestock.getProductName();
    }
}
