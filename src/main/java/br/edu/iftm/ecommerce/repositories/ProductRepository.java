package br.edu.iftm.ecommerce.repositories;

import br.edu.iftm.ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
