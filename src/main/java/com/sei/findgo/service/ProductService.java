package com.sei.findgo.service;

import com.sei.findgo.exceptions.*;
import com.sei.findgo.models.Product;
import com.sei.findgo.models.User;
import com.sei.findgo.repository.ProductRepository;
import com.sei.findgo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing products.
 *
 * This class provides methods to perform CRUD (Create, Read, Update, Delete) operations on products.
 * It interacts with the {@link ProductRepository} and {@link UserRepository} to access and manipulate product data.
 * The service methods implement various business logic and authorization checks.
 */
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

    /**
     * Adds a new product.
     *
     * @param productObject The product object to be added.
     * @return The added product.
     * @throws NoAuthorizationException If the user is not authorized to add the product.
     */
    public Product addProduct(Product productObject) {
        if (UserService.getCurrentLoggedInUser().getRole().equals("Manager") || UserService.getCurrentLoggedInUser().getRole().equals("Admin")) {
           if (productObject.getProductName() == null || productObject.getProductName().equals("")) {
               throw new MissingFieldException("Product name cannot be empty.");
           }else {
               Optional<Product> product = productRepository.findByProductNameIgnoreCase(productObject.getProductName());
               if (product.isPresent()) {
                   throw new InformationExistException("Product with name " + productObject.getProductName() + " already exists.");
               }else return productRepository.save(productObject);
           }
        } else throw new NoAuthorizationException("User not authorized to add product.");
    }

    /**
     * Retrieves all products.
     *
     * @return A list of all products.
     * @throws InformationNotFoundException If no products are found.
     */
    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        if (productList.isEmpty()) {
            throw new InformationNotFoundException("No products found.");
        } else {
            return productList;
        }
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param productId The ID of the product to retrieve.
     * @return The product with the specified ID.
     * @throws ProductNotFoundException  If the product with the specified ID is not found.
     * @throws NoAuthorizationException If the user is not authorized to view the product.
     */
    public Product getProductById(Long productId) {
        if (UserService.getCurrentLoggedInUser().getRole().equals("Manager") || UserService.getCurrentLoggedInUser().getRole().equals("Admin")) {
            Optional<Product> product = productRepository.findById(productId);
            if (product.isPresent()) {
                return product.get();
            } else {
                throw new ProductNotFoundException("Product with id " + productId + " not found.");
            }
        } else {
            throw new NoAuthorizationException("User not authorized to view product.");
        }
    }

    /**
     * Retrieves a product by its name.
     *
     * @param productName The name of the product to retrieve.
     * @return The product with the specified name.
     * @throws ProductNotFoundException If the product with the specified name is not found.
     */
    public Product getProductByName(String productName) {
        Optional<Product> product = productRepository.findByProductNameIgnoreCase(productName);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new ProductNotFoundException("Product with name " + productName + " not found.");
        }
    }

    /**
     * Retrieves products by category.
     *
     * @param category The category of the products to retrieve.
     * @return A list of products in the specified category.
     * @throws InformationNotFoundException If no products are found in the specified category.
     */
    public List<Product> getProductsByCategory(String category) {
        List<Product> productList = productRepository.findProductsByCategoryIgnoreCase(category);
        if (productList.isEmpty()) {
            throw new InformationNotFoundException("No products found.");
        } else {
            return productList;
        }
    }

    /**
     * Updates a product.
     *
     * @param productObject The updated product object.
     * @param productId     The ID of the product to update.
     * @return The updated product.
     * @throws ProductNotFoundException  If the product with the specified ID is not found.
     * @throws NoAuthorizationException If the user is not authorized to update the product.
     */
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
            } else {
                throw new ProductNotFoundException("Product with id " + productId + " not found.");
            }
        } else {
            throw new NoAuthorizationException("You are not authorized to update this product.");
        }
    }

    /**
     * Deletes a product.
     *
     * @param productId The ID of the product to delete.
     * @return The deleted product.
     * @throws ProductNotFoundException  If the product with the specified ID is not found.
     * @throws NoAuthorizationException If the user is not authorized to delete the product.
     */
    public Product deleteProduct(Long productId) {
        if (UserService.getCurrentLoggedInUser().getRole().equals("Manager") || UserService.getCurrentLoggedInUser().getRole().equals("Admin")) {
            Optional<Product> product = productRepository.findById(productId);
            if (product.isPresent()) {
                productRepository.deleteById(productId);
                return product.get();
            } else {
                throw new ProductNotFoundException("Product with id " + productId + " not found.");
            }
        } else {
            throw new NoAuthorizationException("User not authorized to delete product.");
        }
    }
}
