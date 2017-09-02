package com.alcatrazstudios.springmvc.repositories;

import com.alcatrazstudios.springmvc.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer>{
}
