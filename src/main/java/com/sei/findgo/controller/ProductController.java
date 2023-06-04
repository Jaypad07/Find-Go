package com.sei.findgo.controller;

import com.sei.findgo.models.Product;
import com.sei.findgo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Adds a new product.
     *
     * @param productObject The product object to be added.
     * @return The added product.
     */
    @PostMapping(path = "/auth/products")
    public Product addProduct(@RequestBody Product productObject) {
        return productService.addProduct(productObject);
    }

    /**
     * Retrieves all products.
     *
     * @return A list of all available products.
     */
    @GetMapping(path = "/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param productId The ID of the product.
     * @return The product with the specified ID.
     */
    @GetMapping(path = "auth/products/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    /**
     * Retrieves a product by its name.
     *
     * @param productName The name of the product.
     * @return The product with the specified name.
     */
    @GetMapping(path = "/products/search/{productName}")
    public Product getProductByName(@PathVariable String productName) {
        return productService.getProductByName(productName);
    }

    /**
     * Retrieves all products of a specific category.
     *
     * @param categoryName The name of the category.
     * @return A list of products belonging to the specified category.
     */
    @GetMapping(path = "/products/search/category/{categoryName}")
    public List<Product> getProductsByCategory(@PathVariable String categoryName) {
        return productService.getProductsByCategory(categoryName);
    }

    /**
     * Updates a product with the specified ID.
     *
     * @param productId     The ID of the product to be updated.
     * @param productObject The updated product object.
     * @return The updated product.
     */
    @PutMapping(path = "/auth/products/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestBody Product productObject) {
        return productService.updateProduct(productObject, productId);
    }

    /**
     * Deletes a product with the specified ID.
     *
     * @param productId The ID of the product to be deleted.
     * @return The deleted product.
     */
    @DeleteMapping(path = "/auth/products/{productId}")
    public Product deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }
}