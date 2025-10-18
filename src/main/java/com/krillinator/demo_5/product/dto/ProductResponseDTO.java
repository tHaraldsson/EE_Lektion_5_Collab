package com.krillinator.demo_5.product.dto;

import org.springframework.data.annotation.Id;

public record ProductResponseDTO(
        @Id Long id,
        String name,
        String description,
        boolean isOnSale
) {
}
