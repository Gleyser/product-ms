package com.gleyser.productms.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class ProductDto{

    private Long id;

    @NotEmpty(message = "Attention: field name is required.")
    private String name;

    @NotEmpty(message = "Attention: field description is required.")
    private String description;

    @NotNull(message = "Attention: field price is required.")
    @Positive(message = "Attention: field price must be positive.")
    private BigDecimal price;

}
