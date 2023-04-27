package com.eyamattoussi.productservice.controller;

import com.eyamattoussi.productservice.dto.ProductRequestDto;
import com.eyamattoussi.productservice.dto.ProductResponseDto;
import com.eyamattoussi.productservice.exception.ProductNotFoundException;
import com.eyamattoussi.productservice.service.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;

    @PostMapping(path = "/add")
    public ResponseEntity<ProductResponseDto> addProduct(@Valid @RequestBody ProductRequestDto productRequestDTO) {
        return new ResponseEntity<>(productService.addProduct(productRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable String id, @Valid @RequestBody ProductRequestDto productRequestDTO){
        try {
            return new ResponseEntity<>(productService.updateProduct(id, productRequestDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ProductNotFoundException("Product not found");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductResponseDto>> getProducts() {
        return ResponseEntity.ok().body(productService.getProducts());
    }
    @GetMapping("/getById/{productId}")
    public ResponseEntity<ProductResponseDto> getProductsById(@PathVariable String productId) {
        return ResponseEntity.ok().body(productService.getProductById(productId));
    }
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable String productId) {
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new ProductNotFoundException("User doesn't exist");
        }
    }
}


