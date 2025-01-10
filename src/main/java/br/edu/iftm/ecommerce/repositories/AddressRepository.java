package br.edu.iftm.ecommerce.repositories;

import br.edu.iftm.ecommerce.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    Address findByAddressableId(UUID addressableId);
}
