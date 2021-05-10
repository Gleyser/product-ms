package com.gleyser.productms.service;

import com.gleyser.productms.dto.ProductDto;
import com.gleyser.productms.entity.Product;
import com.gleyser.productms.exception.ProductNotFoundException;
import com.gleyser.productms.mapper.ProductMapper;
import com.gleyser.productms.repository.ProductRepository;
import com.gleyser.productms.repository.ProductSpecificationRepository;
import com.gleyser.productms.Specification.MaxPriceSpecification;
import com.gleyser.productms.Specification.NameAndDescriptionSpecification;
import com.gleyser.productms.Specification.MinPriceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;
    private final ProductSpecificationRepository productSpecificationRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductSpecificationRepository productSpecificationRepository) {

        this.productRepository = productRepository;
        this.productSpecificationRepository = productSpecificationRepository;
    }

    public ProductDto createProduct(ProductDto productDto){
        Product productToSave = this.productMapper.toModel(productDto);
        Product productSaved = this.productRepository.save(productToSave);
        ProductDto productReturned = this.productMapper.toDTO(productSaved);
        return productReturned;

    }

    public List<ProductDto> listAllProducts() {
        List<Product> products = this.productRepository.findAll();
        return products.stream().map(this.productMapper::toDTO).collect(Collectors.toList());
    }

    public ProductDto getById(Long id) throws ProductNotFoundException {
        Product product = verifyIfProductExists(id);
        return this.productMapper.toDTO(product);
    }

    public void deletebyId(Long id) throws ProductNotFoundException {
        verifyIfProductExists(id);
        this.productRepository.deleteById(id);
    }

    public ProductDto updateById(Long id, ProductDto productDto) throws ProductNotFoundException {
        verifyIfProductExists(id);
        productDto.setId(id);
        Product productToUpdate = this.productMapper.toModel(productDto);
        Product updatedProduct = this.productRepository.save(productToUpdate);
        ProductDto productReturned = this.productMapper.toDTO(updatedProduct);
        return productReturned;
    }

    public List<ProductDto> filteredProducts(String nameAndDescriptionFilter, BigDecimal min_price, BigDecimal max_price){
        Specification<Product> specification = Specification
                .where(NameAndDescriptionSpecification.nameAndDescription(nameAndDescriptionFilter))
                .and(MinPriceSpecification.minPrice(min_price))
                .and(MaxPriceSpecification.maxPrice(max_price));

        List<Product> products = this.productSpecificationRepository.findAll(specification);
        return products.stream().map(this.productMapper::toDTO).collect(Collectors.toList());

    }

    private Product verifyIfProductExists(Long id) throws ProductNotFoundException {
        return this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }



}
