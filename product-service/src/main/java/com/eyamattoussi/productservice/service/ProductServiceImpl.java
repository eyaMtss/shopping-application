package com.eyamattoussi.productservice.service;

import com.eyamattoussi.productservice.dto.ProductRequestDto;
import com.eyamattoussi.productservice.dto.ProductResponseDto;
import com.eyamattoussi.productservice.mapper.ProductMapperImpl;
import com.eyamattoussi.productservice.model.Product;
import com.eyamattoussi.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    private ProductMapperImpl productMapper = new ProductMapperImpl();
    @Override
    public ProductResponseDto getProductById(String id) {
        return productRepository.findById(id).isPresent() ?
                productMapper.productToProductResponseDto(productRepository.findById(id).get()) : null;
    }

    @Override
    public List<ProductResponseDto> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> productMapper.productToProductResponseDto(product))
                .toList();
    }

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        return productMapper.productToProductResponseDto(productRepository.save(productMapper.productRequestDtoToProduct(productRequestDto)));
    }

    @Override
    public ProductResponseDto updateProduct(ProductRequestDto productRequestDto) {

        Product existingProduct =  productRepository.findById(productRequestDto.getId()).isPresent() ?
                productRepository.findById(productRequestDto.getId()).get() : null;
        if (existingProduct == null) {
            return null;
        }
        else {
            existingProduct.setName(productRequestDto.getName());
            existingProduct.setDescription(productRequestDto.getDescription());
            existingProduct.setPrice(productRequestDto.getPrice());
            return productMapper.productToProductResponseDto(productRepository.save(existingProduct));
        }
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
