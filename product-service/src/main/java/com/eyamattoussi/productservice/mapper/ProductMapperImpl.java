package com.eyamattoussi.productservice.mapper;

import com.eyamattoussi.productservice.dto.ProductRequestDto;
import com.eyamattoussi.productservice.dto.ProductResponseDto;
import com.eyamattoussi.productservice.model.Product;

public class ProductMapperImpl implements ProductMapper {
    @Override
    public Product productRequestDtoToProduct(ProductRequestDto productRequestDto) {
        return Product.builder()
                .name(productRequestDto.getName())
                .description(productRequestDto.getDescription())
                .price(productRequestDto.getPrice())
                .build();
    }

    @Override
    public ProductResponseDto productToProductResponseDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
