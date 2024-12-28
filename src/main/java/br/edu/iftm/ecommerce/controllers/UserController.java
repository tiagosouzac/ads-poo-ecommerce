package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.User;
import br.edu.iftm.ecommerce.repositories.UserRepository;
import br.edu.iftm.ecommerce.strategies.user.DeleteUserStrategy;
import br.edu.iftm.ecommerce.strategies.user.SaveUserStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {
    private final UserRepository userRepository;
    private final SaveUserStrategy saveUserStrategy;
    private final DeleteUserStrategy deleteUserStrategy;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.saveUserStrategy = new SaveUserStrategy();
        this.deleteUserStrategy = new DeleteUserStrategy();
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public void saveUser(User user) {
        this.saveUserStrategy.execute(user, this.userRepository);
    }

    public void deleteUser(User user) {
        this.deleteUserStrategy.execute(user, this.userRepository);
    }
}
