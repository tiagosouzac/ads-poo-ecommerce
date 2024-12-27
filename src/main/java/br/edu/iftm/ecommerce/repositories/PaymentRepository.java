package br.edu.iftm.ecommerce.repositories;

import br.edu.iftm.ecommerce.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
