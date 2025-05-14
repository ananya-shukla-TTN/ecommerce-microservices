package com.ttn.consumer.service;

import com.ttn.consumer.constant.OrderStatus;
import com.ttn.consumer.dto.OrderDto;
import com.ttn.consumer.model.Order;
import com.ttn.consumer.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void createOrder(OrderDto orderDto) {
        Order order = Order.builder()
                .customerEmail(orderDto.getCustomerEmail())
                .totalAmount(orderDto.getTotalAmount())
                .products(orderDto.getProducts())
                .dateCreated(LocalDateTime.now())
                .orderStatus(OrderStatus.PENDING)
                .build();

        orderRepository.save(order);
    }
}
