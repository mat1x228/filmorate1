package ru.yandex.practicum.filmorate.src.main.controller;

import ch.qos.logback.classic.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.src.main.model.User;
import ru.yandex.practicum.filmorate.src.main.storageService.UserService;
import ru.yandex.practicum.filmorate.src.main.validator.Validator;


import java.util.ArrayList;
import java.util.List;

import static ru.yandex.practicum.filmorate.src.main.enums.StatusEnums.*;


@RestController
@RequestMapping("/api/filmorate")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(Validator.class);

    static {
        ((ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME))
                .setLevel(Level.INFO);
    }
    UserService userService = new UserService();
    Validator validator = new Validator();

    @PostMapping("/user")
    public String addUser(@RequestBody User user) {
        log.info("Создание нового юзера");
        if (validator.isValid(user)) {
            boolean userCreated = userService.createUser(user);
            log.debug("userCreated: " + String.valueOf(userCreated));
            if (userCreated) {
                log.trace("Имя пользователя: {}, Емайл пользователя: {}, Логин пользователя: {}, Дата рождения: {}",
                        user.getName(), user.getEmail(), user.getLogin(), user.getBirthday());
                return CREATE_STATUS.getDescription() + user.getName();
            }
        }
        return NOT_FOUND.getDescription();
    }

    @PutMapping("/user/{userId}")
    public String updateUser(@RequestBody User user) {
        log.info("Обновление юзера");
        boolean userUpdated = userService.updateUser(user);
        if (userUpdated) {
            log.trace("Имя пользователя: {}, Емайл пользователя: {}, Логин пользователя: {}, Дата рождения: {}",
                    user.getName(), user.getEmail(), user.getLogin(), user.getBirthday());
            return UPDATE_STATUS.getDescription();
        } else {
            return NOT_FOUND.getDescription();
        }
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        log.info("Получение всех юзеров");
        List<User> users = new ArrayList<>(userService.getUsers());
        log.debug("Количество пользователей: " + userService.getUsers().size());
        return users;
    }

}
