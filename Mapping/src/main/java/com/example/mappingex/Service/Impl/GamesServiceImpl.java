package com.example.mappingex.Service.Impl;

import com.example.mappingex.Repository.GamesRepository;
import com.example.mappingex.Service.GamesService;
import com.example.mappingex.entities.DTO.ValidateGameDTO;
import com.example.mappingex.entities.Games;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class GamesServiceImpl implements GamesService {

    private GamesRepository gamesRepository;

    @Autowired
    public GamesServiceImpl(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    @Override
    public void adminAddGame(ValidateGameDTO game) {
        ModelMapper modelMapper = new ModelMapper();
        Games games = modelMapper.map(game, Games.class);
        this.gamesRepository.save(games);
    }

    @Override
    public Optional<Games> findById(int gameId) {
        return this.gamesRepository.findById(gameId);
    }

    @Override
    public void editGame(int gameId, BigDecimal price, float size) {
        this.gamesRepository.updateByIdWithPriceAndSize(gameId, price, size);
    }

    @Override
    public void deleteGame(int gameId) {
        this.gamesRepository.deleteById(gameId);
    }

    @Override
    public List<Games> allGames() {
        return this.gamesRepository.findAll();
    }

    @Override
    public Games findByTitle(String title) {
        return this.gamesRepository.findByTitle(title);
    }
}
