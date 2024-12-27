package br.edu.iftm.ecommerce.repositories;

import br.edu.iftm.ecommerce.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
