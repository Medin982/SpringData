package com.example.xml_processing.ProductShop.Service.Impl;

import com.example.xml_processing.ProductShop.Repository.ProductRepository;
import com.example.xml_processing.ProductShop.Repository.UserRepository;
import com.example.xml_processing.ProductShop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }
}
