package com.sei.findgo.seed;

import com.sei.findgo.models.Product;
import com.sei.findgo.models.Store;
import com.sei.findgo.models.StoreSection;
import com.sei.findgo.models.User;
import com.sei.findgo.repository.ProductRepository;
import com.sei.findgo.repository.StoreRepository;
import com.sei.findgo.repository.StoreSectionRepository;
import com.sei.findgo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
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

    private StoreSectionRepository storeSectionRepository;

    @Autowired
    public void setStoreSectionRepository(StoreSectionRepository storeSectionRepository) {
        this.storeSectionRepository = storeSectionRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        loadSeedData();
    }
    private void loadSeedData() {

        System.out.println("Calling Seed Data");

        // Creating Users
        User user1 = new User("Jeff", "john@example.com", "password1", "User");
        User user2 = new User("Kevin", "emma@example.com", "password2" , "User");
        User user3 = new User("Obinna", "obinna@example.com", "password3" , "User");
        User user4 = new User("Marie", "marie@example.com", "password4" , "Manager");
        User user5 = new User("Jay", "jay@example.com", "password5", "Admin");

        //Creating Stores
        Store Target = new Store("Target", "4734 E Ray Rd, Phoenix, AZ 85044", "Retail Store" ,  "floorPlan", "/assets/target.jpg");
        Store BestBuy= new Store("Best Buy", "2100 N Elston Ave, Chicago, IL 60614,", "Retail Store", "floorPlan", "/assets/bestBuy.jpg");
        Store CVS = new Store("CVS", "6045 N Scottsdale Rd, Scottsdale, AZ 85250", "Retail / Health", "floorPlan", "/assets/CVS.png");
        Store HomeDepot = new Store("Home Depot", "110 E Sepulveda Blvd, Carson, CA 90745", "Home Improvement", "floorPlan", "/assets/homeDepot.jpg");
        Store Walmart = new Store("Walmart", "1301 N Victory Pl, Burbank, CA 91502", "Retail Store", " floorPlan", "/assets/walmart.png");

        // Saving created stores to the database
        storeRepository.save(Target);
        storeRepository.save(BestBuy);
        storeRepository.save(CVS);
        storeRepository.save(HomeDepot);
        storeRepository.save(Walmart);

        // Creating Store Sections

        StoreSection B16 = new StoreSection("B16 Electronics", Target);
        storeSectionRepository.save(B16);

        StoreSection B17 = new StoreSection("B17 Electronics", Target);
        storeSectionRepository.save(B17);

        StoreSection A19 = new StoreSection("A19 Clothing", Target);
        storeSectionRepository.save(A19);

        StoreSection C12 = new StoreSection("C12 Appliances", Target);
        storeSectionRepository.save(C12);

        StoreSection D10 = new StoreSection("D10 Home Decor", Target);
        storeSectionRepository.save(D10);

        StoreSection E08 = new StoreSection("E08 Toys", Target);
        storeSectionRepository.save(E08);

        StoreSection F06 = new StoreSection("F06 Beauty", Target);
        storeSectionRepository.save(F06);


        // Creating products
        Product laptop = new Product("Laptop", "High-performance laptop with Intel Core i7 processor.", "Electronics", 1299.89, "img", 10);
        Product tShirt = new Product("T-Shirt", "Cotton t-shirt with a printed logo.", "Clothing", 21.22, "img", 50);
        Product smartPhone = new Product("Smartphone", "Latest smartphone with a high-resolution display.", "Electronics", 867.47, "img",  20);
        Product jeans = new Product("Jeans", "Men's blue jeans.", "Clothing", 49.99, "img",  30);
        Product smartTV = new Product("Smart TV", "Ultra HD smart TV with built-in streaming apps.", "Electronics", 997.97, "img",  5);
        Product wirelessHeadphones = new Product("Wireless Headphones", "Bluetooth wireless headphones with noise cancellation.", "Electronics", 149.67, "img",  15);
        Product digitalCamera = new Product("Digital Camera", "High-resolution digital camera with various shooting modes.", "Electronics", 432.23, "img",  8);
        Product bluetoothSpeaker = new Product("Bluetooth Speaker", "Portable Bluetooth speaker with excellent sound quality.", "Electronics", 85.49, "img",  20);
        Product kitchenBlender = new Product("Kitchen Blender", "Powerful blender for making smoothies and soups.", "Appliances", 56.00, "img",  10);
        Product coffeeMaker = new Product("Coffee Maker", "Programmable coffee maker with built-in grinder.", "Appliances", 70.32, "img",  12);
        Product tableLamp = new Product("Table Lamp", "Modern table lamp with adjustable brightness.", "Home Decor", 50.00, "img",  15);
        Product throwPillow = new Product("Throw Pillow", "Soft and cozy throw pillow for your living room.", "Home Decor", 25.22, "img",  20);



        // Grouping products to a list and adding it to Sections
        List<Product> electronicsSectionB16 = List.of(wirelessHeadphones, digitalCamera, bluetoothSpeaker);
        List<Product> electronicsSectionB17 = List.of(laptop, smartPhone, smartTV);
        List<Product> clothingSectionA19 = List.of(tShirt, jeans);
        List<Product> applianceSectionC12 = List.of(kitchenBlender, coffeeMaker);
        List<Product> homeDecorSectionD10 = List.of(tableLamp, throwPillow);

        //Adding grouped products lists to store sections
        B16.setProductList(electronicsSectionB16);
        B17.setProductList(electronicsSectionB17);
        A19.setProductList(clothingSectionA19);
        C12.setProductList(applianceSectionC12);
        D10.setProductList(homeDecorSectionD10);

        //Assigning store sections filled with products to a specific store
        Target.setStoreSectionsList(List.of(B16, B17, A19, C12, D10));


        //adding products to the store-section
        laptop.setStoreSection(B17);
        tShirt.setStoreSection(A19);
        smartPhone.setStoreSection(B17);
        jeans.setStoreSection(A19);
        smartTV.setStoreSection(B17);
        wirelessHeadphones.setStoreSection(B16);
        digitalCamera.setStoreSection(B16);
        bluetoothSpeaker.setStoreSection(B16);
        kitchenBlender.setStoreSection(C12);
        coffeeMaker.setStoreSection(C12);
        tableLamp.setStoreSection(D10);
        throwPillow.setStoreSection(D10);


      //Adding users to the database
      userService.registerUser(user1);
      userService.registerUser(user2);
      userService.registerUser(user3);
      userService.registerUser(user4);
      userService.registerUser(user5);


      //Adding all products to a list
        List<Product> targetProducts = Arrays.asList(
                laptop,
                tShirt,
                smartPhone,
                jeans,
                smartTV,
                wirelessHeadphones,
                digitalCamera,
                bluetoothSpeaker,
                kitchenBlender,
                coffeeMaker,
                tableLamp,
                throwPillow
        );
        productRepository.saveAll(targetProducts);
        System.out.println("Seed Data Loaded");

    }
}
