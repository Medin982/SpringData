package com.example.mappingex.Service.Impl;

import com.example.mappingex.Service.ExecutorService;
import com.example.mappingex.Service.GamesService;
import com.example.mappingex.Service.UserService;
import com.example.mappingex.entities.DTO.LoginDTO;
import com.example.mappingex.entities.DTO.RegisterDTO;
import com.example.mappingex.entities.DTO.ValidateGameDTO;
import com.example.mappingex.entities.Games;
import com.example.mappingex.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@Service
public class ExecutorServiceImpl implements ExecutorService {

    private final UserService userService;
    private final GamesService gamesService;

    @Autowired
    public ExecutorServiceImpl(UserService userService, GamesService gamesService) {
        this.userService = userService;
        this.gamesService = gamesService;
    }

    @Override
    public String execute(String command, String data) {
        return switch (command) {
            case REGISTER_USER_COMMAND -> registerUser(data);
            case LOGIN_USER_COMMAND -> loginUser(data);
            case LOGOUT_USER_COMMAND -> logoutUser();
            case ADD_GAME_COMMAND -> addGame(data);
            case EDIT_GAME_COMMAND -> editGame(data);
            case DELETE_GAME_COMMAND -> deleteGame(data);
            case All_GAME_DETAil_COMMAND -> allGameDetails();
            case DETAil_GAME_COMMAND -> detailGame(data);
            default -> "Unknown command";
        };

    }

    private String detailGame(String data) {
        String title = data.split("\\|")[1];
        Games game = this.gamesService.findByTitle(title);
        return game.toString();

    }

    private String allGameDetails() {
        return this.gamesService.allGames()
                .stream()
                .map(Games::TitleAndPriceInformation)
                .findFirst()
                .get();
    }

    private String deleteGame(String data) {
        User user = this.userService.gerLoggedUser();
        if (!user.isAdmin()) {
            throw new UnsupportedOperationException("You must be admin to change games");
        }
        int gameId = Integer.parseInt(data.split("\\|")[1]);
        Optional<Games> currentGame = this.gamesService.findById(gameId);
        if (currentGame.isEmpty()) {
            throw new IllegalArgumentException("No such game with given id");
        }
        this.gamesService.deleteGame(gameId);
        return String.format("Deleted %s", currentGame.get().getTitle());
    }

    private String editGame(String data) {
        String[] dateArr = data.split("\\|");
        int gameId = Integer.parseInt(dateArr[1]);
        Optional<Games> currentGame = this.gamesService.findById(gameId);
        if (currentGame.isEmpty()) {
            throw new IllegalArgumentException("No such game with given id");
        }
        this.gamesService.editGame(gameId, currentGame.get().getPrice(), currentGame.get().getSize());
        return String.format("Edited %s", currentGame.get().getTitle());
    }

    private String addGame(String data) {
        User user = this.userService.gerLoggedUser();
        if (!user.isAdmin()) {
            throw new UnsupportedOperationException("You must be admin to change games");
        }

        String[] dateArr = data.split("\\|");
        ValidateGameDTO game = new ValidateGameDTO(dateArr[1], new BigDecimal(dateArr[2]),
                Float.parseFloat(dateArr[3]), dateArr[4], dateArr[5]
                , dateArr[6], LocalDate.parse(dateArr[7], DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        this.gamesService.adminAddGame(game);
        return String.format("Added %s", game.getTitle());
    }

    private String logoutUser() {
        User user = this.userService.gerLoggedUser();
        String fullName = user.getFullName();
        this.userService.LogoutUser();
        return String.format("User %s successfully logged out", fullName);
    }

    private String loginUser(String data) {
        String[] dataArr = data.split("\\|");
        LoginDTO loginDTO = new LoginDTO(dataArr[1], dataArr[2]);
        Optional<User> user = this.userService.LoginUser(loginDTO);
        if (user.isPresent()) {
            return String.format("Successfully logged in %s", user.get().getFullName());
        }
        return "Wrong credentials";
    }

    private String registerUser(String data) {
        String[] dataArr = data.split("\\|");
        RegisterDTO registerDTO = new RegisterDTO(dataArr[1], dataArr[2], dataArr[3], dataArr[4]);
        User user = userService.registerUser(registerDTO);
        return String.format("%s was registered", user.getFullName());
    }
}
