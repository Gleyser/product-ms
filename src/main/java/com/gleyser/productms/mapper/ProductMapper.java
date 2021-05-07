package com.gleyser.productms.mapper;

import com.gleyser.productms.dto.ProductDto;
import com.gleyser.productms.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toModel(ProductDto productDto);

    ProductDto toDTO(Product product);



}
