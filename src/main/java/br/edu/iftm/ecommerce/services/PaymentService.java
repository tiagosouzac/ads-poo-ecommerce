package br.edu.iftm.ecommerce.services;

import br.edu.iftm.ecommerce.models.Payment;
import br.edu.iftm.ecommerce.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    public PaymentRepository paymentRepository;

    public List<Payment> findAll() {
        System.out.println("Buscando todos os pagamentos...");
        List<Payment> payments = paymentRepository.findAll();
        System.out.println("Pagamentos encontrados!");
        return payments;
    }

    public void save(Payment payment) {
        System.out.println("Salvando pagamento...");
        paymentRepository.save(payment);
        System.out.println("Pagamento salvo com sucesso!");
    }

    public void delete(Payment payment) {
        System.out.println("Removendo pagamento...");
        paymentRepository.delete(payment);
        System.out.println("Pagamento removido com sucesso!");
    }
}
