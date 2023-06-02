package com.sei.findgo.seed;

import com.sei.findgo.models.Product;
import com.sei.findgo.models.Store;
import com.sei.findgo.models.StoreSection;
import com.sei.findgo.models.User;
import com.sei.findgo.repository.ProductRepository;
import com.sei.findgo.repository.StoreRepository;
import com.sei.findgo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private StoreRepository storeRepository;

    @Autowired
    public void setStoreRepository(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadSeedData();
    }

    private void loadSeedData() {

        System.out.println("Calling Seed Data");

        User user1 = new User("John", "john@example.com", "password1", "User");
        User user2 = new User("Emma", "emma@example.com", "password2" , "User");
        User user3 = new User("Michael", "michael@example.com", "password3" , "User");
        User user4 = new User("Sophia", "sophia@example.com", "password4" , "User");
        User user5 = new User("William", "william@example.com", "password5", "User");

        user5.setRole("Admin");

        Store Target = new Store();
        Target.setStoreName("Target");
        Store BestBuy = new Store();

        // Creating Store Sections
        StoreSection electronicsSection = new StoreSection();
        electronicsSection.setSectionName("Electronics Section");
        electronicsSection.setStore(Target);

        StoreSection clothingSection = new StoreSection();
        clothingSection.setSectionName("Clothing Section");
        electronicsSection.setStore(Target);

        // Creating products
        Product product1 = new Product();
        product1.setProductName("Laptop");
        product1.setDescription("High-performance laptop with Intel Core i7 processor.");
        product1.setCategory("Electronics");
        product1.setPrice(1200);
        product1.setQuantity(10);
        product1.setStoreSection(electronicsSection);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setProductName("T-Shirt");
        product2.setDescription("Cotton t-shirt with a printed logo.");
        product2.setCategory("Clothing");
        product2.setPrice(20);
        product2.setQuantity(50);
        product2.setStoreSection(clothingSection);

        Product product3 = new Product();
        product3.setId(3L);
        product3.setProductName("Smartphone");
        product3.setDescription("Latest smartphone with a high-resolution display.");
        product3.setCategory("Electronics");
        product3.setPrice(800);
        product3.setQuantity(20);
        product3.setStoreSection(electronicsSection);

        Product product4 = new Product();
        product4.setId(4L);
        product4.setProductName("Jeans");
        product4.setDescription("Men's blue jeans.");
        product4.setCategory("Clothing");
        product4.setPrice(50);
        product4.setQuantity(30);
        product4.setStoreSection(clothingSection);

        //Adding a list of products to the store isles
        List<Product> isle4 = List.of(product1, product3);
        List<Product> isle17 = List.of(product2, product4);

        //Adding isles of products to store sections
        electronicsSection.setProductList(isle4);
        clothingSection.setProductList(isle17);

        //Adding a list of store sections to the store
        Target.setStoreSectionsList(List.of(electronicsSection, clothingSection));

        //Adding stores to the database
        storeRepository.save(Target);
        storeRepository.save(BestBuy);

        //Adding users to the database
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);

        //Adding products to the database
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);

        System.out.println("Seed Data Loaded");
    }
}
