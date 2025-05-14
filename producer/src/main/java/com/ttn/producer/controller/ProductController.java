package com.ttn.producer.controller;

import com.ttn.producer.dto.ProductDto;
import com.ttn.producer.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<String> addProduct(@Valid @RequestBody ProductDto productDto){
        productService.addProduct(productDto);
        return new ResponseEntity<>("Product added successfully" , HttpStatus.CREATED);
    }

}
