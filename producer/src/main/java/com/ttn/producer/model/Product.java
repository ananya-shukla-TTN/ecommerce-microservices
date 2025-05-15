package com.ttn.producer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "products")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Product {
    @Id
    private String id;

    private String name;
    private BigDecimal price;
    private Integer availableQuantity;
    private String category;
}
