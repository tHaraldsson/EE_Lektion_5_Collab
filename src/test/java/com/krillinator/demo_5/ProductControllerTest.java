package com.krillinator.demo_5;

import com.krillinator.demo_5.product.Product;
import com.krillinator.demo_5.product.ProductRepository;
import com.krillinator.demo_5.product.dto.ProductResponseDTO;
import com.krillinator.demo_5.product.dto.ProductValidatorDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.math.BigDecimal;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class ProductControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void clearDatabase() {
        productRepository.deleteAll().block();
    }

    @Test
    void shouldDeleteProductSuccessfully() {
        // Arrange: create product to delete
        ProductValidatorDTO dto = new ProductValidatorDTO(
                "Mango",
                "Sweet tropical fruit",
                BigDecimal.valueOf(25.0),
                false
        );

        // Save to database via API
        ProductResponseDTO created = webTestClient.post()
                .uri("/api/v1/product/create")
                .bodyValue(dto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(ProductResponseDTO.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertNotNull(created);
        Assertions.assertNotNull(created.id());

        Long id = created.id();

        // Double-check that the product exists in the DB
        Product product = productRepository.findById(id).block();
        System.out.println("Created product: " + product);

        // Act & Assert: delete it and expect HTTP 204 (No Content)
        webTestClient.delete()
                .uri("/api/v1/product/delete/{id}", id)
                .exchange()
                .expectStatus().isNoContent();

        // Verify that the product was actually deleted
        Assertions.assertNull(productRepository.findById(id).block());
    }
}
