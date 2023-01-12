package com.daily.product.livestock.dto;

import com.daily.product.livestock.domain.livestock.Livestock;
import lombok.Getter;

@Getter
public class LivestockPlaceProductDto {
    private String productName;
    private String standard;
    private Long price;
    private String remark;
    private String checkDate;

    public LivestockPlaceProductDto(Livestock livestock) {
        this.productName = livestock.getProductName();
        this.standard = livestock.getStandard();
        this.price = livestock.getPrice();
        this.remark = livestock.getRemarks();
        this.checkDate = livestock.getCheckDate();
    }
}
