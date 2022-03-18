package com.example.mappingex.Service;

import com.example.mappingex.entities.DTO.ValidateGameDTO;
import com.example.mappingex.entities.Games;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface GamesService {
    void adminAddGame(ValidateGameDTO game);

    Optional<Games> findById(int gameId);

    void editGame(int gameId, BigDecimal price, float size);

    void deleteGame(int gameId);

    List<Games> allGames();

    Games findByTitle(String title);
}
