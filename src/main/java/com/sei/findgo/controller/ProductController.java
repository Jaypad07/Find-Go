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

    @PutMapping(path="/auth/products/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestBody Product productObject) {
        return productService.updateProduct(productObject, productId);
    }

    @DeleteMapping(path="/auth/products/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }
}
