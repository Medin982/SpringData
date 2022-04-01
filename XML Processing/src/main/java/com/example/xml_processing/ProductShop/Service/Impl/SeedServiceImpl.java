package com.example.xml_processing.ProductShop.Service.Impl;

import com.example.xml_processing.ProductShop.Entites.Category;
import com.example.xml_processing.ProductShop.Entites.DTO.CategorySeedDTO;
import com.example.xml_processing.ProductShop.Entites.DTO.ProductsSeedDTO;
import com.example.xml_processing.ProductShop.Entites.DTO.UserSeedDTO;
import com.example.xml_processing.ProductShop.Entites.DTO.UsersSeedDTO;
import com.example.xml_processing.ProductShop.Entites.Product;
import com.example.xml_processing.ProductShop.Entites.User;
import com.example.xml_processing.ProductShop.Repository.CategoryRepository;
import com.example.xml_processing.ProductShop.Repository.ProductRepository;
import com.example.xml_processing.ProductShop.Repository.UserRepository;
import com.example.xml_processing.ProductShop.Service.SeedService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {

    private static final Path USER_PATH = Path.of("src", "main", "resources", "productShop", "users.xml");
    private static final Path PRODUCTS_PATH = Path.of("src", "main", "resources", "productShop", "products.xml");
    private static final Path CATEGORY_PATH = Path.of("src", "main", "resources", "productShop", "categories.xml");

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;

        this.modelMapper = new ModelMapper();

    }

    @Override
    public void seedUsers() throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(UserSeedDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        BufferedReader xmlReader = Files.newBufferedReader(USER_PATH);
        UsersSeedDTO userSeedDTOS = (UsersSeedDTO) unmarshaller.unmarshal(xmlReader);
        List<User> collect = userSeedDTOS.getUsers().stream()
                .map(u -> this.modelMapper.map(u, User.class))
                .collect(Collectors.toList());
        this.userRepository.saveAll(collect);
    }

    @Override
    public void seedProducts() throws FileNotFoundException {
//        FileReader fileReader = new FileReader(PRODUCTS_PATH.toAbsolutePath().toString());
//        JAXBContext context = JAXBContext.newInstance()
//        List<Product> productList = Arrays.stream(productsSeedDTOS)
//                .map(productDTO -> this.modelMapper.map(productDTO, Product.class))
//                .map(this::setRandomSeller)
//                .map(this::setRandomBuyer)
//                .map(this::setRandomCategory)
//                .collect(Collectors.toList());
//        this.productRepository.saveAll(productList);
    }

    private Product setRandomCategory(Product product) {
        long count = this.categoryRepository.count();
        int categoriesCount = new Random().nextInt((int) count);
        Set<Category> categories = new HashSet<>();
        for (int i = 0; i < count; i++) {
            int randomId = new Random().nextInt((int) count) + 1;
            categories.add(this.categoryRepository.findById(randomId).get());
        }
        product.setCategories(categories);
        return product;
    }

    private Product setRandomSeller(Product product) {
        User seller = getRandomUser();
        product.setSeller(seller);
        return product;
    }

    private User getRandomUser() {
        long count = this.userRepository.count();
        int randomId = new Random().nextInt((int) count) + 1;
        User user = this.userRepository.getById(randomId);
        return user;
    }

    private Product setRandomBuyer(Product product) {
        if (product.getPrice().compareTo(new BigDecimal(950)) > 0) {
            return product;
        }
        User buyer = getRandomUser();
        product.setBuyer(buyer);
        return product;
    }

    @Override
    public void seedCategories() throws FileNotFoundException {
//        FileReader fileReader = new FileReader(CATEGORY_PATH.toAbsolutePath().toString());
//        CategorySeedDTO[] categorySeedDTO = this.gson.fromJson(fileReader, CategorySeedDTO[].class);
//        List<Category> categories = Arrays.stream(categorySeedDTO)
//                .map(categoryDTO -> this.modelMapper.map(categoryDTO, Category.class))
//                .collect(Collectors.toList());
//        this.categoryRepository.saveAll(categories);
    }
}
