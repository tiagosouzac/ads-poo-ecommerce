package br.edu.iftm.ecommerce.strategies.payment;

import br.edu.iftm.ecommerce.models.Payment;
import br.edu.iftm.ecommerce.repositories.PaymentRepository;

public class SavePaymentStrategy implements PaymentStrategy {

    @Override
    public void execute(Payment payment, PaymentRepository paymentRepository) {
        System.out.println("Salvando pagamento...");
        paymentRepository.save(payment);
        System.out.println("Pagamento salvo com sucesso!");
    }
}
