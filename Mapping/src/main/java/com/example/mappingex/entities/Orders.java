package com.example.mappingex.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    private User buyer;
    @ManyToMany
    private Set<Games> products;

    public Orders() {
    }

    public Orders(User buyer) {
        this.buyer = buyer;
        this.products = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Set<Games> getProducts() {
        return products;
    }

    public void setProducts(Set<Games> products) {
        this.products = products;
    }
}
