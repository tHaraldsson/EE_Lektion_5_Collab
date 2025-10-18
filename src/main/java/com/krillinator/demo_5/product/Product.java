package com.krillinator.demo_5.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Instant;

@Table("products")
public record Product(
        @Id Long id,
        String name,
        String description,
        BigDecimal price,
        @Column("is_on_sale") boolean isOnSale,
        @Column("created_at") Instant createdAt
) {
}
