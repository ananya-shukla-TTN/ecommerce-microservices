package com.ttn.consumer.controller;

import com.ttn.consumer.dto.OrderDto;
import com.ttn.consumer.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<String> placeOrder(@Valid @RequestBody OrderDto orderDto){
        orderService.createOrder(orderDto);
        return new ResponseEntity<>("Order placed successfully", HttpStatus.CREATED);
    }
}
