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

    @PostMapping(path = "/auth/products")
    public Product addProduct(@RequestBody Product productObject) {
        return productService.addProduct(productObject);
    }

    @GetMapping(path = "/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(path = "/products/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping(path = "/products/search/{productName}")
    public Product getProductByName(@PathVariable String productName) {
        return productService.getProductByName(productName);
    }

    @GetMapping(path = "/products/search/category/{categoryName}")
    public List<Product> getProductsByCategory(@PathVariable String categoryName) {
        return productService.getProductsByCategory(categoryName);
    }

    @PutMapping(path="/auth/products/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestBody Product productObject) {
        return productService.updateProduct(productObject, productId);
    }

    @DeleteMapping(path="/auth/products/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }
}