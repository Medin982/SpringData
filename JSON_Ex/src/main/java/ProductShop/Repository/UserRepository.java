package ProductShop.Repository;

import ProductShop.Entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u" +
            " JOIN u.productsSell p" +
            " WHERE p.buyer IS NOT NULL")
    List<User> findAllByBuyer();

    @Query("SELECT u, " +
            "   (SELECT COUNT(p) " +
            "   FROM Product p " +
            "   WHERE p.seller = u AND p.buyer IS NOT NULL) AS soldCount " +
            " FROM User u" +
            " WHERE soldCount > 0 ")
    List<User> findAllByProductSellIsNotNull();
}
