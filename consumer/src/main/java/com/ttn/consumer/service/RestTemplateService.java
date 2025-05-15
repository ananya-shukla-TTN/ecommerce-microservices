package com.ttn.consumer.service;

import com.ttn.consumer.dto.OrderProductDto;
import com.ttn.consumer.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RestTemplateService {
    private final RestTemplate restTemplate;

    @Value("${producer.service.url}")
    private String producerServiceUrl;

    public OrderProductDto getProduct(String id){
        try {
            return restTemplate.getForObject(producerServiceUrl + "/" + id, OrderProductDto.class);
        }  catch (HttpClientErrorException.NotFound e) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }
    }

    public void updateStock(String id, Integer quantity){
        try{
            restTemplate.put(producerServiceUrl +
                    "/" + id +
                    "/update-quantity?quantity=" + quantity, null);
        }
        catch (HttpClientErrorException.NotFound e) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }
    }
}
