package com.krillinator.demo_5.product;

import com.krillinator.demo_5.product.dto.ProductValidatorDTO;
import com.krillinator.demo_5.product.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    // GET
    public Flux<Product> getAllProducts() {

        return productRepository.findAll()
                .doOnNext(product -> logger.info("Product found: {}", product))
                .doOnComplete(() -> logger.info("Fetching all products successfully without errors"))
                .doOnError(throwable -> logger.error("Problem in finding Products: {}", throwable.getMessage()));
    }

    // CREATE
    public Mono<Product> createNewProduct(ProductValidatorDTO productValidatorDTO) {

        return productRepository.save(productMapper.toEntity(productValidatorDTO))
                .doOnSuccess(product -> logger.info("product saved with id: {}", product.id()))
                .doOnError(throwable -> logger.error("Error on saving product: {}", throwable.getMessage()));
    }

    // DELETE
    public Mono<Void> deleteProductById(Long id) {

        return productRepository.deleteById(id)
                .doOnSuccess(product -> logger.info("Succesfully deleted product with id {}", id))
                .doOnError(throwable -> logger.error("Something went wrong deleting product by id {}", throwable.getMessage()));
    }
}
