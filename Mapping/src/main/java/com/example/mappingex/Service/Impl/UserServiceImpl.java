package com.example.mappingex.Service.Impl;

import com.example.mappingex.Exceptions.ExistingLogetUsetException;
import com.example.mappingex.Repository.UserRepository;
import com.example.mappingex.Service.UserService;
import com.example.mappingex.entities.DTO.LoginDTO;
import com.example.mappingex.entities.DTO.RegisterDTO;
import com.example.mappingex.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private User currentUser;

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(RegisterDTO userInformation) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userInformation, User.class);
        long count = this.userRepository.count();
        if (count == 0) {
            user.setAdmin(true);
        }
        return this.userRepository.save(user);
    }

    @Override
    public Optional<User> LoginUser(LoginDTO userInformation) {
        if (this.currentUser != null) {
            throw new ExistingLogetUsetException("Already have a login user");
        }
        Optional<User> user = this.userRepository.findByEmailAndPassword(
                userInformation.getEmail(), userInformation.getPassword());
        if (!user.isEmpty()) {
            this.currentUser = user.get();
        }
        return user;
    }

    @Override
    public void LogoutUser() {
        this.currentUser = null;
    }

    @Override
    public User gerLoggedUser() {
        return this.currentUser;
    }
}
