package ProductShop.Repository;

import ProductShop.Entites.Product;
import ProductShop.Entites.DTO.CategoriesByProductCountDTO;
import ProductShop.Entites.DTO.ProductInRangeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT new ProductShop.Entites.DTO.ProductInRangeDTO    (" +
            " p.name, p.price," +
            " p.seller.firstName, p.seller.lastName)" +
            " FROM Product p" +
            " WHERE p.price BETWEEN :startPrice AND :endPrice" +
            " AND p.buyer IS NULL" +
            " ORDER BY p.price ASC")
    List<ProductInRangeDTO> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);

    @Query("SELECT new ProductShop.Entites.DTO.CategoriesByProductCountDTO("+
            "c.name, COUNT(p), AVG(p.price), SUM(p.price))" +
            " FROM Product p" +
            " JOIN p.categories c" +
            " GROUP BY c")
    List<CategoriesByProductCountDTO> findCategoriesStats();
}
