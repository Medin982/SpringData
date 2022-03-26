package ProductShop.Service;

import ProductShop.Entites.DTO.UsersCountDTO;
import ProductShop.Entites.User;
import ProductShop.Entites.DTO.UserWithSoldProductsDTO;

import java.util.List;

public interface UserService {

    List<UserWithSoldProductsDTO> findByProductsSell();

    UsersCountDTO getAllUsers();
}
