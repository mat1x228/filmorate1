package ru.yandex.practicum.filmorate.src.main.interfaces;




import ru.yandex.practicum.filmorate.src.main.model.User;

import java.util.List;

public interface UserInterface {

    Boolean createUser(User user);
     List<User> getUsers();
    Boolean updateUser(User user);
}

