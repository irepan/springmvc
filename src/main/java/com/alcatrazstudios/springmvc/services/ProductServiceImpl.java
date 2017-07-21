package com.alcatrazstudios.springmvc.services;

import com.alcatrazstudios.springmvc.domain.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by irepan on 10/07/17.
 */
@Service
@Profile("map")
public class ProductServiceImpl extends AbstractMapService<Product> implements ProductService {

    @Override
    public List<Product> listAll() {
        return super.listAll();
    }

    @Override
    protected void loadDomainObjects(){

        Product product1 = new Product();
        product1.setId(1);
        product1.setDescription("Product 1");
        product1.setPrice(new BigDecimal("15.02"));
        product1.setImageUrl("http://www.example.com/product1.jpg");
        domainMap.put(1,product1);

        product1 = new Product();
        product1.setId(2);
        product1.setDescription("Product 2");
        product1.setPrice(new BigDecimal("5.28"));
        product1.setImageUrl("http://www.example.com/product2.jpg");
        domainMap.put(2,product1);

        product1 = new Product();
        product1.setId(3);
        product1.setDescription("Product 3");
        product1.setPrice(new BigDecimal("329.50"));
        product1.setImageUrl("http://www.example.com/product3.jpg");
        domainMap.put(3,product1);

        product1 = new Product();
        product1.setId(4);
        product1.setDescription("Product 4");
        product1.setPrice(new BigDecimal("78.99"));
        product1.setImageUrl("http://www.example.com/product4.jpg");
        domainMap.put(4,product1);

        product1 = new Product();
        product1.setId(5);
        product1.setDescription("Product 5");
        product1.setPrice(new BigDecimal("65.45"));
        product1.setImageUrl("http://www.example.com/product5.jpg");
        domainMap.put(5,product1);

    }
}
