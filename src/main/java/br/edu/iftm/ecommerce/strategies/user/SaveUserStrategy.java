package br.edu.iftm.ecommerce.strategies.user;

import br.edu.iftm.ecommerce.models.User;
import br.edu.iftm.ecommerce.repositories.UserRepository;

public class SaveUserStrategy implements UserStrategy {

    @Override
    public void execute(User user, UserRepository userRepository) {
        System.out.println("Salvando usuário...");
        userRepository.save(user);
        System.out.println("Usuário salvo com sucesso!");
    }
}
