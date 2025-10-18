package com.krillinator.demo_5.product.mapper;

import com.krillinator.demo_5.product.Product;
import com.krillinator.demo_5.product.dto.ProductResponseDTO;
import com.krillinator.demo_5.product.dto.ProductValidatorDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductValidatorDTO productValidatorDTO) {
        return new Product(
                null,
                productValidatorDTO.name(),
                productValidatorDTO.description(),
                productValidatorDTO.price(),
                productValidatorDTO.isOnSale(),
                null
        );
    }
    public ProductResponseDTO toResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.id(),
                product.name(),
                product.description(),
                product.isOnSale()
        );
    }

}
