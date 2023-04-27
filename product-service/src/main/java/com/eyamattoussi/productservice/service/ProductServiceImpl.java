package com.eyamattoussi.productservice.service;

import com.eyamattoussi.productservice.dto.ProductRequestDto;
import com.eyamattoussi.productservice.dto.ProductResponseDto;
import com.eyamattoussi.productservice.mapper.ProductMapperImpl;
import com.eyamattoussi.productservice.model.Product;
import com.eyamattoussi.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    private ProductMapperImpl productMapper = new ProductMapperImpl();
    @Override
    public ProductResponseDto getProductById(String id) {
        log.info("Getting a product");
        return productRepository.findById(id).isPresent() ?
                productMapper.productToProductResponseDto(productRepository.findById(id).get()) : null;
    }

    @Override
    public List<ProductResponseDto> getProducts() {
        log.info("Getting all products ...");
        return productRepository.findAll()
                .stream()
                .map(product -> productMapper.productToProductResponseDto(product))
                .toList();
    }

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        log.info("Saving new product ...");
        return productMapper.productToProductResponseDto(productRepository.save(productMapper.productRequestDtoToProduct(productRequestDto)));
    }

    @Override
    public ProductResponseDto updateProduct(String id, ProductRequestDto productRequestDto) {
        log.info("Updating ...");
        Product existingProduct =  productRepository.findById(id).isPresent() ?
                productRepository.findById(id).get() : null;
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
        log.info("Deleting ...");
        productRepository.deleteById(id);
    }
}
