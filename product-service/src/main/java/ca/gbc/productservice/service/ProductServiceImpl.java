package ca.gbc.productservice.service;

import ca.gbc.productservice.dto.ProductRequest;
import ca.gbc.productservice.dto.ProductResponse;
import ca.gbc.productservice.model.Product;
import ca.gbc.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        log.debug("Creating a new product: {}", productRequest.name());

        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();

        product = productRepository.save(product);
        log.info("Product saved with ID: {}", product.getId());

        return mapToProductResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        log.debug("Fetching all products...");
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    @Override
    public String updateProduct(String id, ProductRequest productRequest) {
        log.debug("Updating product with ID: {}", id);

        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productRequest.name());
            product.setDescription(productRequest.description());
            product.setPrice(productRequest.price());

            Product updatedProduct = productRepository.save(product);
            log.info("Product updated with ID: {}", updatedProduct.getId());

            return updatedProduct.getId();
        } else {
            log.warn("Product with ID: {} not found. No updates performed.", id);
            return null;
        }
    }

    @Override
    public void deleteProduct(String id) {
        log.debug("Deleting product with ID: {}", id);

        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            log.info("Product with ID: {} deleted successfully.", id);
        } else {
            log.warn("Product with ID: {} not found. No deletion performed.", id);
        }
    }

    private ProductResponse mapToProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }
}
