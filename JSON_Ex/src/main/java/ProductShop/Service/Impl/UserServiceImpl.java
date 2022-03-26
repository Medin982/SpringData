package ProductShop.Service.Impl;

import ProductShop.Entites.DTO.UsersCountDTO;
import ProductShop.Entites.DTO.UsersSoldProductDTO;
import ProductShop.Entites.User;
import ProductShop.Repository.UserRepository;
import ProductShop.Service.UserService;
import ProductShop.Entites.DTO.UserWithSoldProductsDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

        this.modelMapper = new ModelMapper();
    }

    @Override
    @Transactional
    public List<UserWithSoldProductsDTO> findByProductsSell() {
        List<User> userList = this.userRepository.findAllByBuyer();

        return userList.stream()
                .map(dto -> this.modelMapper.map(dto, UserWithSoldProductsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UsersCountDTO getAllUsers() {
        List<User> users = this.userRepository.findAllByProductSellIsNotNull();
        List<UsersSoldProductDTO> collect = users.stream()
                .map(user -> this.modelMapper.map(user, UsersSoldProductDTO.class))
                .collect(Collectors.toList());
        return new UsersCountDTO(collect);
    }
}
