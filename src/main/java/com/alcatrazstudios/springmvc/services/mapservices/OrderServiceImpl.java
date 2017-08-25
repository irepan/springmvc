package com.alcatrazstudios.springmvc.services.mapservices;

import com.alcatrazstudios.springmvc.domain.Order;
import com.alcatrazstudios.springmvc.services.OrderService;

import java.util.HashMap;
import java.util.List;

public class OrderServiceImpl extends AbstractMapService<Order> implements OrderService {
    @Override
    protected void loadDomainObjects() {
        domainMap = new HashMap<>();
    }

    @Override
    public List<Order> listAll() {
        return super.listAll();
    }

    @Override
    public Order saveOrUpdate(Order domainObject) {
        return (Order) super.saveOrUpdate(domainObject);
    }

}
