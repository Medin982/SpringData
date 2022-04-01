package com.example.xml_processing.ProductShop.Repository;

import com.example.xml_processing.ProductShop.Entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
