package com.example.mappingex.Repository;

import com.example.mappingex.entities.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Repository
public interface GamesRepository extends JpaRepository<Games, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Games g " +
            "SET g.price = :price, g.size = :size" +
            " WHERE g.id = :gameId")
    void updateByIdWithPriceAndSize(int gameId, BigDecimal price, float size);

    Games findByTitle(String title);
}
