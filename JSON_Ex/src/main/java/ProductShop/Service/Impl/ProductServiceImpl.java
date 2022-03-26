package ProductShop.Service.Impl;

import ProductShop.Entites.DTO.CategoriesByProductCountDTO;
import ProductShop.Repository.ProductRepository;
import ProductShop.Repository.UserRepository;
import ProductShop.Service.ProductService;
import ProductShop.Entites.DTO.ProductInRangeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ProductInRangeDTO> findProductBetweenRange(float start, float end) {
        BigDecimal startPrice = BigDecimal.valueOf(start);
        BigDecimal endPrice = BigDecimal.valueOf(end);
        return this.productRepository.findByPriceBetween(startPrice, endPrice);
    }

    @Override
    public List<CategoriesByProductCountDTO> findCategoriesStats() {
        return this.productRepository.findCategoriesStats();
    }
}
