package com.gleyser.productms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Attention: field name is required.")
    private String name;

    @Column(nullable = false)
    @NotEmpty(message = "Attention: field description is required.")
    private String description;

    @Column(nullable = false, precision = 20, scale = 2)
    @NotNull(message = "Attention: field price is required.")
    @Positive(message = "Attention: field price must be positive.")
    private BigDecimal price;
}
