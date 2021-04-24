package com.citec.demo.shop.model;

import com.citec.demo.shop.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findById(long id); //SELECT * FROM product WHERE id = ?

    Product findByName(String name);

    List<Product> findAllByOrderByIdAsc();
}
