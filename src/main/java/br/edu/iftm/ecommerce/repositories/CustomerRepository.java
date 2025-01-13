package br.edu.iftm.ecommerce.repositories;

import br.edu.iftm.ecommerce.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    List<Customer> findAllByNameContainingIgnoreCase(String name);

    Customer findByNameContainingIgnoreCase(String name);

    boolean existsByEmail(String email);
}
