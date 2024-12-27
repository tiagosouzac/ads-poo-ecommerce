package br.edu.iftm.ecommerce.repositories;

import br.edu.iftm.ecommerce.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
}
