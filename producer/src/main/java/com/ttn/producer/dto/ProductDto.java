package com.ttn.producer.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProductDto {
    @NotBlank(message = "Product name cannot be empty.")
    @Size(max = 100, message = "Product name must be within 100 characters.")
    private String name;

    @NotNull(message = "Price cannot be null.")
    @PositiveOrZero(message = "Price must be greater than 0.")
    private BigDecimal price;

    @NotNull(message = "Available quantity cannot be null.")
    @PositiveOrZero(message = "Available quantity must be greater than 0.")
    private Integer availableQuantity;

    @NotBlank(message = "Category cannot be empty.")
    private String category;
}
