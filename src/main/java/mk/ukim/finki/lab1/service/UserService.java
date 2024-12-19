package mk.ukim.finki.lab1.service;

import mk.ukim.finki.lab1.model.User;

public interface UserService {
    User login(String username, String password);

    User register(String username, String password, String repeatPassword, String name, String surname);
    User findByUsername(String username);

}
