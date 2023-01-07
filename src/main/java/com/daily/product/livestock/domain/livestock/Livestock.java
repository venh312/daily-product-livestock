package com.daily.product.livestock.domain.livestock;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Table("livestock")
public class Livestock {
    @Id
    private Long id;
    private Long no;
    private String name;
}
