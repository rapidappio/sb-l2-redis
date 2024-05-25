package com.rapidapp.sbl2redis;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@SpringBootApplication
public class SbL2RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbL2RedisApplication.class, args);
    }

}

@RestController
@RequestMapping("/products")
class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public Product createProduct(@RequestBody CreateProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
        return productRepository.save(product);
    }
}

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Cache(region = "products", usage = CacheConcurrencyStrategy.READ_WRITE)
class Product {
        @Id
        @GeneratedValue
        private Long id;
        private String name;
        private String description;
        private BigDecimal price;
}

@Data
class CreateProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
}

interface ProductRepository extends JpaRepository<Product, Long> {
}



