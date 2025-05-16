package com.ttn.producer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttn.producer.dto.ProductDto;
import com.ttn.producer.exception.ProductNotFoundException;
import com.ttn.producer.model.Product;
import com.ttn.producer.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final EncryptionService encryptionService;
    private final ObjectMapper objectMapper;

    public void addProduct(ProductDto productDto) {
        Product product = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .availableQuantity(productDto.getAvailableQuantity())
                .category(productDto.getCategory())
                .build();
        productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("No product exists with the id provided."));
    }

    public Map<String, String> getEncryptedProduct(Map<String, String> encryptedRequest) {
        try {
            String decryptedId = encryptionService.decryptFromConsumer(encryptedRequest);
            Product product = productRepository.findById(decryptedId)
                    .orElseThrow(() -> new ProductNotFoundException("No product exists with the id provided."));
            String productJson = objectMapper.writeValueAsString(product);
            return encryptionService.encryptForConsumer(productJson);
        } catch (Exception e) {
            throw new RuntimeException("Error processing encrypted request", e);
        }
    }

    public void updateEncryptedQuantity(Map<String, String> encryptedRequest) {
        try {
            String decryptedJson = encryptionService.decryptFromConsumer(encryptedRequest);
            Map<String, Object> requestData = objectMapper.readValue(decryptedJson, Map.class);

            String productId = (String) requestData.get("id");
            Integer quantity = Integer.parseInt(requestData.get("quantity").toString());

            Product product = getProductById(productId);
            product.setAvailableQuantity(product.getAvailableQuantity() - quantity);
            productRepository.save(product);
        } catch (Exception e) {
            throw new RuntimeException("Error processing encrypted request", e);
        }
    }
}
