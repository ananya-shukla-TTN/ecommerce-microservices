package com.ttn.consumer.model;

import com.ttn.consumer.constant.OrderStatus;
import com.ttn.consumer.dto.ProductDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document(collection = "orders")
@Builder
public class Order {
    @Id
    private String id;

    private String customerEmail;
    private LocalDateTime dateCreated;
    private BigDecimal totalAmount;
    private OrderStatus orderStatus;
    private List<ProductDto> products;
}
