package br.edu.iftm.ecommerce.strategies.user;

import br.edu.iftm.ecommerce.models.User;
import br.edu.iftm.ecommerce.repositories.UserRepository;

public interface UserStrategy {
    void execute(User user, UserRepository userRepository);
}
