package com.sei.findgo.service;

import com.sei.findgo.exceptions.InformationNotFoundException;
import com.sei.findgo.exceptions.NoAuthorizationException;
import com.sei.findgo.exceptions.ProductNotFoundException;
import com.sei.findgo.models.Product;
import com.sei.findgo.models.User;
import com.sei.findgo.repository.ProductRepository;
import com.sei.findgo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Product addProduct(Product productObject) {
        Optional<User> user = Optional.ofNullable(UserService.getCurrentLoggedInUser());
        if (user.isPresent() && user.get().getRole().equals("Manager") || user.isPresent() && user.get().getRole().equals("Admin")) {
            return productRepository.save(productObject);
        } else throw new NoAuthorizationException("User not authorized to add product.");
    }

    public List<Product> getAllProducts() {
            List<Product> productList = productRepository.findAll();
            if (productList.size() == 0) {
                throw new InformationNotFoundException("No products found.");
            } else return productList;
        }

    public Product getProductById(Long productId) {
        Optional<User> user = Optional.ofNullable(UserService.getCurrentLoggedInUser());
        if (user.isPresent()) {
            Optional<Product> product = productRepository.findById(productId);
            if (product.isPresent()) {
                return product.get();
            } else throw new ProductNotFoundException("Product with id " + productId + " not found.");
        } else throw new InformationNotFoundException("user not found");
    }

    public Product updateProduct(Product productObject, Long productId) {
        Optional<User> user = Optional.ofNullable(UserService.getCurrentLoggedInUser());
        if (user.isPresent() && user.get().getRole().equals("Manager") || user.isPresent() && user.get().getRole().equals("Admin")) {
            Optional<Product> product = productRepository.findById(productId);
            if (product.isPresent()) {
                Product existingProduct = product.get();
                existingProduct.setProductName(productObject.getProductName());
                existingProduct.setDescription(productObject.getDescription());
                existingProduct.setCategory(productObject.getCategory());
                existingProduct.setPrice(productObject.getPrice());
                existingProduct.setQuantity(productObject.getQuantity());
                existingProduct.setStoreSection(productObject.getStoreSection());
                productRepository.save(existingProduct);
                return existingProduct;
            } else throw new ProductNotFoundException("Product with id " + productId + " not found.");
        } else throw new NoAuthorizationException("You are not authorized to update this product.");
    }

    public String deleteProduct(Long productId) {
        Optional<User> user = Optional.ofNullable(UserService.getCurrentLoggedInUser());
        if (user.isPresent() && user.get().getRole().equals("Manager") || user.isPresent() && user.get().getRole().equals("Admin")) {
            Optional<Product> product = productRepository.findById(productId);
            if (product.isPresent()) {
                productRepository.deleteById(productId);
                return product.get() + " deleted successfully.";
            } else throw new ProductNotFoundException("Product with id " + productId + " not found.");
        } else throw new NoAuthorizationException("User not authorized to delete product.");
    }
}