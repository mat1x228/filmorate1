package main.interfaces;

import main.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);
    List<User> getUsers();
    User updateUser(User user);
}

