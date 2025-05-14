package com.ttn.producer.service;

import com.ttn.producer.dto.ProductDto;
import com.ttn.producer.model.Product;
import com.ttn.producer.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

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
}
