package br.edu.iftm.ecommerce.strategies.payment;

import br.edu.iftm.ecommerce.models.Customer;
import br.edu.iftm.ecommerce.models.Order;
import br.edu.iftm.ecommerce.models.Payment;

public interface PaymentMethod {
    Payment createPayment(Order order, Customer customer);
}
