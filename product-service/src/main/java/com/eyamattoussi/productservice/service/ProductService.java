package com.eyamattoussi.productservice.service;

import com.eyamattoussi.productservice.dto.ProductRequestDto;
import com.eyamattoussi.productservice.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto getProductById(String id);
    List<ProductResponseDto> getProducts();
    ProductResponseDto addProduct(ProductRequestDto productRequestDto);
    ProductResponseDto updateProduct(String id, ProductRequestDto productRequestDto);
    void deleteProduct(String id);
}
