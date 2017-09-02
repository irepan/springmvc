package com.alcatrazstudios.springmvc.repositories;

import com.alcatrazstudios.springmvc.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer>{

}
