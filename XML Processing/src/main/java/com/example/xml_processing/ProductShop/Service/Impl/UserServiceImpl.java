package com.example.xml_processing.ProductShop.Service.Impl;
import com.example.xml_processing.ProductShop.Repository.UserRepository;
import com.example.xml_processing.ProductShop.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

        this.modelMapper = new ModelMapper();
    }


}
