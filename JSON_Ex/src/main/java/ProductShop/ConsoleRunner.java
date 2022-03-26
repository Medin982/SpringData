package ProductShop;

import ProductShop.Entites.DTO.CategoriesByProductCountDTO;
import ProductShop.Entites.DTO.ProductInRangeDTO;
import ProductShop.Entites.DTO.UserWithSoldProductsDTO;
import ProductShop.Entites.DTO.UsersCountDTO;
import ProductShop.Entites.User;
import ProductShop.Service.CategoryService;
import ProductShop.Service.ProductService;
import ProductShop.Service.SeedService;
import ProductShop.Service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final ProductService productService;
    private final UserService userService;
    private final CategoryService categoryService;

    private Gson gson;
    @Autowired
    public ConsoleRunner(SeedService seedService, ProductService productService, UserService userService, CategoryService categoryService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;

        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedService.seedAll();
//        _1_ProductsInRange();
//        _2_SuccessfullySoldProducts();
//        _3_CategoriesByProductsCount();
        _4_UsersAndProducts();
    }

    private void _4_UsersAndProducts() {
        UsersCountDTO users = this.userService.getAllUsers();
        System.out.println(gson.toJson(users));
    }


    private void _3_CategoriesByProductsCount() {
        List<CategoriesByProductCountDTO> byProductCount = this.productService.findCategoriesStats();
        System.out.println(this.gson.toJson(byProductCount));
    }

    private void _2_SuccessfullySoldProducts() {
        List<UserWithSoldProductsDTO> userByProductsSell = this.userService.findByProductsSell();
        System.out.println(this.gson.toJson(userByProductsSell));
    }

    private void _1_ProductsInRange() {
        List<ProductInRangeDTO> productBetweenRange = this.productService.findProductBetweenRange(500, 1000);
        System.out.println(this.gson.toJson(productBetweenRange));
    }
}
