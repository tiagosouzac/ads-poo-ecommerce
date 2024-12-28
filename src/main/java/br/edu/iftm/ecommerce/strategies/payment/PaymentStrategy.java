package br.edu.iftm.ecommerce.strategies.payment;

import br.edu.iftm.ecommerce.models.Payment;
import br.edu.iftm.ecommerce.repositories.PaymentRepository;

public interface PaymentStrategy {
    void execute(Payment payment, PaymentRepository paymentRepository);
}
