package com.example.xml_processing.ProductShop.Repository;

import com.example.xml_processing.ProductShop.Entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
