package ProductShop.Service.Impl;

import ProductShop.Repository.UserRepository;
import ProductShop.Entites.Category;
import ProductShop.Entites.DTO.CategorySeedDTO;
import ProductShop.Entites.DTO.ProductsSeedDTO;
import ProductShop.Entites.DTO.UserSeedDTO;
import ProductShop.Entites.Product;
import ProductShop.Entites.User;
import ProductShop.Repository.CategoryRepository;
import ProductShop.Repository.ProductRepository;
import ProductShop.Service.SeedService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {

    private static final Path USER_PATH = Path.of("src", "main", "resources", "users.json");
    private static final Path PRODUCTS_PATH = Path.of("src", "main", "resources", "products.json");
    private static final Path CATEGORY_PATH = Path.of("src", "main", "resources", "categories.json");

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final Gson gson;
    private final ModelMapper modelMapper;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;

        this.modelMapper = new ModelMapper();
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void seedUsers() throws FileNotFoundException {
        FileReader fileReader = new FileReader(USER_PATH.toAbsolutePath().toString());
        UserSeedDTO[] userSeedDTOS = this.gson.fromJson(fileReader, UserSeedDTO[].class);

        List<User> userList = Arrays.stream(userSeedDTOS)
                .map(dto -> this.modelMapper.map(dto, User.class))
                .collect(Collectors.toList());
        this.userRepository.saveAll(userList);
    }

    @Override
    public void seedProducts() throws FileNotFoundException {
        FileReader fileReader = new FileReader(PRODUCTS_PATH.toAbsolutePath().toString());
        ProductsSeedDTO[] productsSeedDTOS = this.gson.fromJson(fileReader, ProductsSeedDTO[].class);

        List<Product> productList = Arrays.stream(productsSeedDTOS)
                .map(productDTO -> this.modelMapper.map(productDTO, Product.class))
                .map(this::setRandomSeller)
                .map(this::setRandomBuyer)
                .map(this::setRandomCategory)
                .collect(Collectors.toList());
        this.productRepository.saveAll(productList);
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
        FileReader fileReader = new FileReader(CATEGORY_PATH.toAbsolutePath().toString());
        CategorySeedDTO[] categorySeedDTO = this.gson.fromJson(fileReader, CategorySeedDTO[].class);
        List<Category> categories = Arrays.stream(categorySeedDTO)
                .map(categoryDTO -> this.modelMapper.map(categoryDTO, Category.class))
                .collect(Collectors.toList());
        this.categoryRepository.saveAll(categories);
    }
}
