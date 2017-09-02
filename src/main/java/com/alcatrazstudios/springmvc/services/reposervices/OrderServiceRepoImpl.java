package com.alcatrazstudios.springmvc.services.reposervices;

import com.alcatrazstudios.springmvc.domain.Order;
import com.alcatrazstudios.springmvc.repositories.OrderRepository;
import com.alcatrazstudios.springmvc.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Profile("springdatajpa")
public class OrderServiceRepoImpl implements OrderService{
    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> listAll() {
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;
    }

    @Override
    public Order saveOrUpdate(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getById(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.isPresent()?order.get():null;
    }

    @Override
    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }
}
