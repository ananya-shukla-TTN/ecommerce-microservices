package com.ttn.consumer.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderProductDto {
    @NotNull(message = "Product id cannot be null")
    private String id;

    @NotBlank(message = "Product name is required")
    private String name;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull(message = "Price cannot be null")
    private BigDecimal price;

    private Integer availableQuantity;
}