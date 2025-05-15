package com.ttn.consumer.service;

import com.ttn.consumer.dto.OrderProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RestTemplateService {
    private final RestTemplate restTemplate;

    @Value("${producer.service.url}")
    private String producerServiceUrl;

    public OrderProductDto getProduct(String id){
        return restTemplate.getForObject(producerServiceUrl + "/" + id, OrderProductDto.class);
    }
}
