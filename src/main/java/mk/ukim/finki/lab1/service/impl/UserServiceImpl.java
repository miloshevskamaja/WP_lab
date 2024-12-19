package mk.ukim.finki.lab1.service.impl;

import mk.ukim.finki.lab1.model.User;
import mk.ukim.finki.lab1.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.lab1.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.lab1.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.lab1.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.lab1.repository.jpa.UserRepository;
import mk.ukim.finki.lab1.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        // Check if the username and password are not null or empty
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {

        if (
                username == null ||
                        username.isEmpty() ||
                        password == null ||
                        password.isEmpty() ||
                        repeatPassword == null ||
                        repeatPassword.isEmpty() ||
                        name == null ||
                        name.isEmpty() ||
                        surname == null ||
                        surname.isEmpty()
        ) {
            throw new InvalidArgumentsException();
        }


        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        if (userRepository.findByUsername(username)!=null) {
            throw new UsernameAlreadyExistsException(username);
        }

        return userRepository.save(new User(username, password, name, surname));
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }


}
