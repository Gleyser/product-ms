package com.gleyser.productms.service;

import com.gleyser.productms.dto.ProductDto;
import com.gleyser.productms.entity.Product;
import com.gleyser.productms.mapper.ProductMapper;
import com.gleyser.productms.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto createProduct(ProductDto productDto){
        Product productToSave = this.productMapper.toModel(productDto);
        Product productSaved = this.productRepository.save(productToSave);
        ProductDto productReturned = this.productMapper.toDTO(productSaved);
        return productReturned;

    }





}
