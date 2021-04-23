package com.citec.demo.shop;

import com.citec.demo.shop.model.entity.Product;
import com.citec.demo.shop.service.ProductService;
import com.citec.demo.shop.model.entity.User;
import com.citec.demo.shop.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class StartupData implements CommandLineRunner {

    private final UserService userService;
    private final ProductService productService;

    public StartupData(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) {
        adminAccount();
        userAccount();
        exampleProducts();
    }

    private void userAccount(){
        User user = new User();

        user.setUsername("user");
        user.setPassword("user");
        user.setPasswordConfirm("user");
        user.setGender("Female");
        user.setEmail("user@example.com");

        userService.save(user);
    }

    private void adminAccount(){
        User admin = new User();

        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setPasswordConfirm("admin");
        admin.setGender("Male");
        admin.setEmail("admin@example.com");

        userService.save(admin);
    }

    private void exampleProducts(){
        final String NAME = "Example Name";
        final String IMAGE_URL = "https://d2gg9evh47fn9z.cloudfront.net/800px_COLOURBOX7389458.jpg";
        final String DESCRIPTION = "Example Description";


        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();

        product1.setName(NAME);
        product1.setImageUrl(IMAGE_URL);
        product1.setDescription(DESCRIPTION);
        product1.setPrice(BigDecimal.valueOf(22));

        product2.setName(NAME);
        product2.setImageUrl("https://waggybox.hu/wp-content/uploads/2018/09/chihuahua-dog-puppy-cute-39317-1024x683.jpeg");
        product2.setDescription(DESCRIPTION);
        product2.setPrice(BigDecimal.valueOf(145));

        product3.setName(NAME);
        product3.setImageUrl(IMAGE_URL);
        product3.setDescription(DESCRIPTION);
        product3.setPrice(BigDecimal.valueOf(15.9));

        product4.setName(NAME);
        product4.setImageUrl("https://www.cervezainternacional.net/images/productos/kwak-estuche-madera-4x33cl--vaso-duo-33cl-estuche-madera_1043_4_1.jpg");
        product4.setDescription(DESCRIPTION);
        product4.setPrice(BigDecimal.valueOf(40.2));

        productService.save(product1);
        productService.save(product2);
        productService.save(product3);
        productService.save(product4);
    }
}
