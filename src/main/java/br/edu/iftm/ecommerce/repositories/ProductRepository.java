package br.edu.iftm.ecommerce.repositories;

import br.edu.iftm.ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByCategoryNameContainingIgnoreCase(String name);

    List<Product> findByBrandNameContainingIgnoreCase(String name);

    List<Product> findBySupplierNameContainingIgnoreCase(String name);

    List<Product> findAllByNameContainingIgnoreCase(String name);

    Product findByNameContainingIgnoreCase(String name);
}
