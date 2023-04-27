package com.eyamattoussi.productservice.mapper;

import com.eyamattoussi.productservice.dto.ProductRequestDto;
import com.eyamattoussi.productservice.dto.ProductResponseDto;
import com.eyamattoussi.productservice.model.Product;

public interface ProductMapper {
    Product productRequestDtoToProduct(ProductRequestDto productRequestDto);
    ProductResponseDto productToProductResponseDto(Product product);
}
