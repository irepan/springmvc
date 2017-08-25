package com.alcatrazstudios.springmvc.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
public class CartDetail extends AbstractDomainClass {
    @ManyToOne
    private Cart cart;

    private Integer quantity;

    @OneToOne
    private Product product;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
