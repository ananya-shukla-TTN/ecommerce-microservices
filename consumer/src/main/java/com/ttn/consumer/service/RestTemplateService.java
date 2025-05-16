package com.ttn.consumer.service;

import com.ttn.consumer.dto.OrderProductDto;
import com.ttn.consumer.exception.ProductNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RestTemplateService {

    private final RestTemplate restTemplate;
    private final EncryptionService encryptionService;
    private final ObjectMapper objectMapper;

    @Value("${producer.service.url}")
    private String producerServiceUrl;

    public OrderProductDto getProduct(String id) {
        try {
            Map<String, String> encryptedRequest = encryptionService.encryptForProducer(id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, String>> request = new HttpEntity<>(encryptedRequest, headers);

            ResponseEntity<Map<String, String>> response = restTemplate.exchange(
                    producerServiceUrl + "/secure-product",
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<Map<String, String>>() {}
            );
            String decryptedJson = encryptionService.decryptFromProducer(response.getBody());
            return objectMapper.readValue(decryptedJson, OrderProductDto.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        } catch (Exception e) {
            throw new RuntimeException("Error processing product request", e);
        }
    }

    public void updateStock(String id, Integer quantity) {
        try {
            Map<String, Object> data = Map.of("id", id, "quantity", quantity);
            Map<String, String> encryptedRequest = encryptionService.encryptForProducer(objectMapper.writeValueAsString(data));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, String>> request = new HttpEntity<>(encryptedRequest, headers);

            restTemplate.exchange(
                    producerServiceUrl + "/update-quantity",
                    HttpMethod.PUT,
                    request,
                    Void.class
            );
        } catch (HttpClientErrorException.NotFound e) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        } catch (Exception e) {
            throw new RuntimeException("Error updating stock", e);
        }
    }
}