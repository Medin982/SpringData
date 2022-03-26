package ProductShop.Service;

import ProductShop.Entites.DTO.CategoriesByProductCountDTO;
import ProductShop.Entites.DTO.ProductInRangeDTO;

import java.util.List;

public interface ProductService {
    List<ProductInRangeDTO> findProductBetweenRange(float start, float end);

    List<CategoriesByProductCountDTO> findCategoriesStats();
}
