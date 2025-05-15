package com.ttn.consumer.service;

import com.ttn.consumer.constant.OrderStatus;
import com.ttn.consumer.dto.OrderDto;
import com.ttn.consumer.dto.OrderProductDto;
import com.ttn.consumer.exception.InsufficientStockException;
import com.ttn.consumer.model.Order;
import com.ttn.consumer.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestTemplateService restTemplateService;

    public void createOrder(OrderDto orderDto) {
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderProductDto orderItem : orderDto.getProducts()) {
           OrderProductDto product = restTemplateService.getProduct(orderItem.getId());

            if (product.getAvailableQuantity() < orderItem.getQuantity()) {
                throw new InsufficientStockException("Insufficient stock for product: " + product.getName());
            }

            BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
            totalAmount = totalAmount.add(itemTotal);

            restTemplateService.updateStock(orderItem.getId(), orderItem.getQuantity());
        }

        Order order = Order.builder()
                .customerEmail(orderDto.getCustomerEmail())
                .totalAmount(totalAmount)
                .products(orderDto.getProducts())
                .dateCreated(LocalDateTime.now())
                .orderStatus(OrderStatus.PENDING)
                .build();

        orderRepository.save(order);
    }
}
