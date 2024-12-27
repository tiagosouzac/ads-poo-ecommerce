package br.edu.iftm.ecommerce.repositories;

import br.edu.iftm.ecommerce.models.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShippingRepository extends JpaRepository<Shipping, UUID> {
}
