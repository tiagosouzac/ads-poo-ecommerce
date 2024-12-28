package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.Payment;
import br.edu.iftm.ecommerce.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PaymentController {
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getPayments() {
        return this.paymentRepository.findAll();
    }

    public void savePayment(Payment payment) {
        this.paymentRepository.save(payment);
    }

    public void deletePayment(Payment payment) {
        this.paymentRepository.delete(payment);
    }
}
