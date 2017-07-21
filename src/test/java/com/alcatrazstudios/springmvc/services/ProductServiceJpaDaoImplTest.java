package com.alcatrazstudios.springmvc.services;

import com.alcatrazstudios.springmvc.config.JpaIntegrationConfig;
import com.alcatrazstudios.springmvc.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by irepan on 13/07/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Import(JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class ProductServiceJpaDaoImplTest {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Test
    public void testListMethod() throws Exception {

        List<Product> products = (List<Product>) productService.listAll();

        assert products.size() == 5;

    }

    @Test
    public void testNewProduct(){
        Product product = new Product();
        product.setDescription("El Nuevo");
        product.setPrice(new BigDecimal("2.35"));
        product.setImageUrl("http://example/product");

        Product newProduct = productService.saveOrUpdate(product);

        assert newProduct.getDescription().equals("El Nuevo");
        assert newProduct.getPrice().equals(new BigDecimal("2.35"));
        assert newProduct.getImageUrl().equals("http://example/product");
        assert newProduct.getId() > 0;

    }

    @Test
    public void testUpdateProduct(){
        Product product = productService.getById(1);
        product.setDescription("El Nuevo");
        product.setPrice(new BigDecimal("2.35"));
        product.setImageUrl("http://example/product");

        Product newProduct = productService.saveOrUpdate(product);

        assert newProduct.getDescription().equals("El Nuevo");
        assert newProduct.getPrice().equals(new BigDecimal("2.35"));
        assert newProduct.getImageUrl().equals("http://example/product");
        assert newProduct.getId() == 1;

    }

    @Test
    public void testGetByIdMethod(){
        Product product = productService.getById(1);

        assert product != null;
        assert product.getId() == 1;

    }
}