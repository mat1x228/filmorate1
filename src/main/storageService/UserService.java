package ru.yandex.practicum.filmorate.src.main.storageService;

import ru.yandex.practicum.filmorate.src.main.interfaces.UserInterface;
import ru.yandex.practicum.filmorate.src.main.model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class UserService implements UserInterface {

    private Map<Integer, User> userStorage = new HashMap<Integer, User>();

    private static final AtomicInteger USER_ID_HOLDER = new AtomicInteger();

    @Override
    public Boolean createUser(User user) {
        final int userId = USER_ID_HOLDER.incrementAndGet();
        user.setId(userId);
        userStorage.put(userId, user);
        return true;
    }

    @Override
    public List<User> getUsers() {
        Collection<User> values = userStorage.values();
        return new ArrayList<>(values);
    }

    @Override
    public Boolean updateUser(User user) {
        if(userStorage.containsKey(user.getId())) {
            Integer userId = user.getId();
            userStorage.remove(user.getId());
            userStorage.put(userId, user);
            return true;
        }
        return false;
    }
}
