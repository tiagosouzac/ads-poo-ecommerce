package br.edu.iftm.ecommerce.strategies.payment;

import br.edu.iftm.ecommerce.models.Customer;
import br.edu.iftm.ecommerce.models.Order;

public class PaymentProcessor {
    private PaymentMethod paymentMethod;

    public PaymentProcessor(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void processPayment(Order order, Customer customer) {
        paymentMethod.createPayment(order, customer);
    }
}
