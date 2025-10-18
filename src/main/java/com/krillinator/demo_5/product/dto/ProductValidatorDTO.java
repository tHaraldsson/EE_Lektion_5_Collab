package com.krillinator.demo_5.product.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductValidatorDTO(
        @Size(min = 2, max = 255, message = "Name must be a valid length of 2-255 chars")
        @NotBlank(message = "Name cannot contain pure whitespaces")
        String name,

        @Size(min = 2, max = 255, message = "Name must be a valid length of 2-255 chars")
        @NotBlank(message = "Description cannot contain pure whitespaces")
        String description,

        @PositiveOrZero(message = "Price must be positive or zero")
        @Digits(integer = 8, fraction = 2, message = "Maximum of 8 digits followed by 2 fractions is allowed")
        BigDecimal price,

        @NotNull
        boolean isOnSale
) {
}
