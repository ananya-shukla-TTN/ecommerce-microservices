package com.ttn.consumer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductDto {
    private String name;
    private Integer quantity;
    private BigDecimal price;
}