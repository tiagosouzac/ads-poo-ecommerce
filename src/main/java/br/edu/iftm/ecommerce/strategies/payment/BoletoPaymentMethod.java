package br.edu.iftm.ecommerce.strategies.payment;

import br.edu.iftm.ecommerce.builders.PaymentBuilder;
import br.edu.iftm.ecommerce.enums.PaymentStatus;
import br.edu.iftm.ecommerce.enums.PaymentType;
import br.edu.iftm.ecommerce.models.Customer;
import br.edu.iftm.ecommerce.models.Order;
import br.edu.iftm.ecommerce.models.Payment;

public class BoletoPaymentMethod implements PaymentMethod {
    @Override
    public Payment createPayment(Order order, Customer customer) {
        return new PaymentBuilder()
                .type(PaymentType.BOLETO)
                .status(PaymentStatus.PENDING)
                .order(order)
                .customer(customer)
                .build();
    }
}
