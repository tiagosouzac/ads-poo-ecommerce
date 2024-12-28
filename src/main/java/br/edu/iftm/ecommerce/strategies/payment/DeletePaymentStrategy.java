package br.edu.iftm.ecommerce.strategies.payment;

import br.edu.iftm.ecommerce.models.Payment;
import br.edu.iftm.ecommerce.repositories.PaymentRepository;

public class DeletePaymentStrategy implements PaymentStrategy {

    @Override
    public void execute(Payment payment, PaymentRepository paymentRepository) {
        System.out.println("Removendo pagamento...");
        paymentRepository.delete(payment);
        System.out.println("Pagamento removido com sucesso!");
    }
}
