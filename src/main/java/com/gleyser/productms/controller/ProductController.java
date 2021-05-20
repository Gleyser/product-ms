package com.gleyser.productms.controller;

import com.gleyser.productms.dto.ProductDto;
import com.gleyser.productms.exception.ProductNotFoundException;
import com.gleyser.productms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody @Valid ProductDto productDto) {
       return this.productService.createProduct(productDto);
    }

    @GetMapping
    public List<ProductDto> listAllProducts(){
        return this.productService.listAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) throws ProductNotFoundException {
        return this.productService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) throws ProductNotFoundException{
        this.productService.deletebyId(id);
    }

    @PutMapping("/{id}")
    public ProductDto updateById(@PathVariable Long id, @RequestBody ProductDto productDto) throws ProductNotFoundException{
        return this.productService.updateById(id, productDto);
    }

    @GetMapping("/search")
    public List<ProductDto> filteredProducts(@RequestParam(value="q", required=false) String nameAndDescriptionFilter,
                                            @RequestParam(value="min_price", required=false) BigDecimal min_price,
                                            @RequestParam(value="max_price", required=false) BigDecimal max_price
                                   ){

        return this.productService.filteredProducts(nameAndDescriptionFilter, min_price, max_price);

    }

}
