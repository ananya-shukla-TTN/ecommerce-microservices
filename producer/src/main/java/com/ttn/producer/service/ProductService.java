package com.ttn.producer.service;

import com.ttn.producer.dto.ProductDto;
import com.ttn.producer.exception.ProductNotFoundException;
import com.ttn.producer.model.Product;
import com.ttn.producer.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
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

    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("No product exists with the id provided."));
    }

    public void updateProductQuantity(String id, Integer quantity) {
        Product product = getProductById(id);
        product.setAvailableQuantity(product.getAvailableQuantity() - quantity);
        productRepository.save(product);
    }
}
