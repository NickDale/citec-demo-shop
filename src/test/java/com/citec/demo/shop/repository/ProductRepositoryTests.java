package com.citec.demo.shop.repository;

import com.citec.demo.shop.creator.ProductCreator;
import com.citec.demo.shop.model.entity.Product;
import com.citec.demo.shop.model.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void checkIfProductRepositoryIsNotNull(){
        assertThat(productRepository).isNotNull();
    }

    @Test
    public void checkIfParamsAreTheSame(){
        Product testObject = ProductCreator.createTestProduct();
        entityManager.persistAndFlush(testObject);

        Product found = productRepository.findByName(testObject.getName());

        assertThat(found.getId()).isEqualTo(testObject.getId());
        assertThat(found.getName()).isEqualTo(testObject.getName());
        assertThat(found.getDescription()).isEqualTo(testObject.getDescription());
        assertThat(found.getPrice()).isEqualTo(testObject.getPrice());
        assertThat(found.getImageUrl()).isEqualTo(testObject.getImageUrl());
    }

    @Test
    public void whenFindByNameThenReturnProduct() {
        Product testObject = ProductCreator.createTestProduct();
        entityManager.persistAndFlush(testObject);

        Product found = productRepository.findByName(testObject.getName());
        assertThat(found.getName()).isEqualTo(testObject.getName());
    }

    @Test
    public void testFindByProductId() {
        Product testObject = ProductCreator.createTestProduct();
        entityManager.persistAndFlush(testObject);

        Optional<Product> product = productRepository.findById(testObject.getId());
        assertThat(product).isNotEmpty();
    }
    @Test
    public void whenFindByIdThenReturnProduct(){
        Product testObject = ProductCreator.createTestProduct();
        entityManager.persistAndFlush(testObject);

        Optional<Product> product = productRepository.findById(testObject.getId());
        assertTrue(product.isPresent() && product.get().getId().equals(testObject.getId()));
    }


    @Test
    public void whenFindAllByOrderByIdAscThenReturnAllProducts(){
        Product testObject1 = ProductCreator.createTestProduct();
        Product testObject2 = ProductCreator.createTestProduct();
        Product testObject3 = ProductCreator.createTestProduct();
        entityManager.persistAndFlush(testObject1);
        entityManager.persistAndFlush(testObject2);
        entityManager.persistAndFlush(testObject3);

        List<Product> found = productRepository.findAllByOrderByIdAsc();
        assertThat(found.size()).isEqualTo(3);
        assertThat(found.get(0).getId()).isEqualTo(testObject1.getId());
        assertThat(found.get(1).getId()).isEqualTo(testObject2.getId());
        assertThat(found.get(2).getId()).isEqualTo(testObject3.getId());
    }

    @Test
    public void whenFindByIdAndNoProductReturnNull(){
        assertThat(productRepository.findById(new Random().nextLong())).isNull();
    }

    @Test
    public void whenFindByNameAndNoProductReturnNull(){
        assertThat(productRepository.findByName("random string")).isNull();
    }

    @Test
    public void whenFindAllByOrderByIdAscAndNoProductsReturnNull(){
        assertThat(productRepository.findAllByOrderByIdAsc()).isNullOrEmpty();
    }
}
