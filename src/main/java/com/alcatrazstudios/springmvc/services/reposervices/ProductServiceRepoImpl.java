package com.alcatrazstudios.springmvc.services.reposervices;

import com.alcatrazstudios.springmvc.domain.Product;
import com.alcatrazstudios.springmvc.repositories.ProductRepository;
import com.alcatrazstudios.springmvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Profile("springdatajpa")
public class ProductServiceRepoImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> listAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Integer id) {
        Optional<Product> opProduct=productRepository.findById(id);

        return opProduct.isPresent()?opProduct.get():null;
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

}
