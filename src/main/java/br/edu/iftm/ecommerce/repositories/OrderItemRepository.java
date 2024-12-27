package br.edu.iftm.ecommerce.repositories;

import br.edu.iftm.ecommerce.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
}
