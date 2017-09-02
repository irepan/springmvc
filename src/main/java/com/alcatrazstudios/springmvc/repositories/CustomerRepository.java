package com.alcatrazstudios.springmvc.repositories;

import com.alcatrazstudios.springmvc.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Integer> {
}
