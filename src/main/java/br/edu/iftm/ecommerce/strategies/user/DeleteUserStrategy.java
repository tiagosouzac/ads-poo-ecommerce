package br.edu.iftm.ecommerce.strategies.user;

import br.edu.iftm.ecommerce.models.User;
import br.edu.iftm.ecommerce.repositories.UserRepository;

public class DeleteUserStrategy implements UserStrategy {

    @Override
    public void execute(User user, UserRepository userRepository) {
        System.out.println("Removendo usuário...");
        userRepository.delete(user);
        System.out.println("Usuário removido com sucesso!");
    }
}
