package edu.mum.coffee.rest;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.exception.HttpClientErrorException;
import edu.mum.coffee.exception.HttpNotFoundErrorException;
import edu.mum.coffee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductResource {
    private ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product) throws URISyntaxException {
        if (product.getId() != null) {
            throw new HttpClientErrorException("A product to be created cannot have id attribute");
        }
        Product result = productService.save(product);
        return ResponseEntity.created(new URI("/api/products/" + result.getId())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody @Valid Product product) throws URISyntaxException {
        if (productService.getProduct(id) == null) {
            throw new HttpNotFoundErrorException("Cannot update because the product with id " + id + " does not exist");
        }
        product.setId(id);
        return ResponseEntity.ok(productService.save(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.getProduct(id);
        if (product == null) {
            throw new HttpNotFoundErrorException("Product with id " + id + " does not exist");
        }
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        if (productService.getProduct(id) == null) {
            throw new HttpNotFoundErrorException("Cannot delete because the product with id " + id + " does not exist");
        }
        productService.delete(id);
        return ResponseEntity.ok().build();
    }
}
