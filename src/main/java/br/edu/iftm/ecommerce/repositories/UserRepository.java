package br.edu.iftm.ecommerce.repositories;

import br.edu.iftm.ecommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
