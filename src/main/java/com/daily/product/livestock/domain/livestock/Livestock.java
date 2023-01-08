package com.daily.product.livestock.domain.livestock;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Table("livestock")
public class Livestock {
    @Id
    private Long id;
    private Long no;
    private String placeCode;
    private String placeName;
    private String productCode;
    private String productName;
    private String standard;
    private Long price;
    private String remarks;
    private String marketTypeCode;
    private String marketTypeName;
    private String autonomousCode;
    private String autonomousName;
    private String checkDate;

}
