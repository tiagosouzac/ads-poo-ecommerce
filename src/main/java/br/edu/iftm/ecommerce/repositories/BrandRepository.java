package br.edu.iftm.ecommerce.repositories;

import br.edu.iftm.ecommerce.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BrandRepository extends JpaRepository<Brand, UUID> {
}
