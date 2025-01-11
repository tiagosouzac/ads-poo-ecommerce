package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.Payment;
import br.edu.iftm.ecommerce.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    public List<Payment> getPayments() {
        return this.paymentService.findAll();
    }

    public void savePayment(Payment payment) {
        this.paymentService.save(payment);
    }

    public void deletePayment(Payment payment) {
        this.paymentService.delete(payment);
    }
}
