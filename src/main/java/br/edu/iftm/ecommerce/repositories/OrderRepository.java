package br.edu.iftm.ecommerce.repositories;

import br.edu.iftm.ecommerce.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
