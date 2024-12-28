package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.Payment;
import br.edu.iftm.ecommerce.repositories.PaymentRepository;
import br.edu.iftm.ecommerce.strategies.payment.DeletePaymentStrategy;
import br.edu.iftm.ecommerce.strategies.payment.SavePaymentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PaymentController {
    private final PaymentRepository paymentRepository;
    private final SavePaymentStrategy savePaymentStrategy;
    private final DeletePaymentStrategy deletePaymentStrategy;

    @Autowired
    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
        this.savePaymentStrategy = new SavePaymentStrategy();
        this.deletePaymentStrategy = new DeletePaymentStrategy();
    }

    public List<Payment> getPayments() {
        return this.paymentRepository.findAll();
    }

    public void savePayment(Payment payment) {
        this.savePaymentStrategy.execute(payment, this.paymentRepository);
    }

    public void deletePayment(Payment payment) {
        this.deletePaymentStrategy.execute(payment, this.paymentRepository);
    }
}
