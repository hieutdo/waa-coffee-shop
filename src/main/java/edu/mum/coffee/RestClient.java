package edu.mum.coffee;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestClient {
    private static final String API_BASE_URL = "http://localhost:8080/api";
    private static final Logger log = LoggerFactory.getLogger(RestClient.class);
    private static final RestTemplate restTemplate = new RestTemplate();

    private static void listAllProducts() {
        log.info("------List All Products-----");
        Product[] allProducts = restTemplate.getForObject(API_BASE_URL + "/products", Product[].class);

        if (allProducts.length > 0) {
            for (Product product : allProducts) {
                log.info(product.toString());
            }
        } else {
            log.info("No users found");
        }
    }

    private static Product getOneProduct(Long id) {
        log.info("-----Get One Product-----");
        Product product = restTemplate.getForObject(API_BASE_URL + "/products/" + id, Product.class);
        if (product != null) {
            log.info(product.toString());
        } else {
            log.info("Product with id " + id + " not found");
        }
        return product;
    }

    private static Long createProduct() {
        log.info("-----Create Product-----");
        Product product = new Product("product 1", "desc", 5.2, ProductType.BREAKFAST);
        ResponseEntity<Product> responseEntity = restTemplate.postForEntity(API_BASE_URL + "/products", product, Product.class);
        log.info("Location: " + responseEntity.getHeaders().getLocation());
        return responseEntity.getBody().getId();
    }

    private static void updateProduct(Long id) {
        log.info("-----Update Product-----");
        Product product = new Product("product 2", "desc", 8.7, ProductType.DINNER);
        restTemplate.put(API_BASE_URL + "/products/" + id, product);
    }

    private static void deleteProduct(Long id) {
        log.info("-----Delete Product-----");
        restTemplate.delete(API_BASE_URL + "/products/" + id);
    }

    public static void main(String[] args) {
        listAllProducts();
        Long productId = createProduct();
        getOneProduct(productId);
        updateProduct(productId);
        listAllProducts();
        deleteProduct(productId);
        listAllProducts();
    }
}
