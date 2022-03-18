package com.example.mappingex.Service;

import com.example.mappingex.entities.DTO.LoginDTO;
import com.example.mappingex.entities.DTO.RegisterDTO;
import com.example.mappingex.entities.User;

import java.util.Optional;

public interface UserService {
    User registerUser(RegisterDTO userInformation);

    Optional<User> LoginUser(LoginDTO userInformation);

    void LogoutUser();

    User gerLoggedUser();
}
