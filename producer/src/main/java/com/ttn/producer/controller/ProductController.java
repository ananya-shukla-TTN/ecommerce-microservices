package com.ttn.producer.controller;

import com.ttn.producer.dto.ProductDto;
import com.ttn.producer.model.Product;
import com.ttn.producer.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<String> addProduct(@Valid @RequestBody ProductDto productDto){
        productService.addProduct(productDto);
        return new ResponseEntity<>("Product added successfully" , HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/secure-product")
    public ResponseEntity<Map<String, String>> getProduct(@RequestBody Map<String, String> encryptedRequest) {
        Map<String, String> encryptedResponse = productService.getEncryptedProduct(encryptedRequest);
        return ResponseEntity.ok(encryptedResponse);
    }

    @PutMapping("/update-quantity")
    public ResponseEntity<Void> updateQuantity(@RequestBody Map<String, String> encryptedRequest) {
        productService.updateEncryptedQuantity(encryptedRequest);
        return ResponseEntity.ok().build();
    }
}
