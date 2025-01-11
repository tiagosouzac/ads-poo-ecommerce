package br.edu.iftm.ecommerce.builders;

import br.edu.iftm.ecommerce.enums.PaymentStatus;
import br.edu.iftm.ecommerce.enums.PaymentType;
import br.edu.iftm.ecommerce.models.Customer;
import br.edu.iftm.ecommerce.models.Order;
import br.edu.iftm.ecommerce.models.Payment;

public class PaymentBuilder {
    private PaymentType type;
    private PaymentStatus status;
    private String transactionCode;
    private Order order;
    private Customer customer;

    public PaymentBuilder type(PaymentType type) {
        this.type = type;
        return this;
    }

    public PaymentBuilder status(PaymentStatus status) {
        this.status = status;
        return this;
    }

    public PaymentBuilder transactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
        return this;
    }

    public PaymentBuilder order(Order order) {
        this.order = order;
        return this;
    }

    public PaymentBuilder customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Payment build() {
        Payment payment = new Payment();
        payment.setType(this.type);
        payment.setStatus(this.status);
        payment.setTransactionCode(this.transactionCode);
        payment.setOrder(this.order);
        payment.setCustomer(this.customer);
        return payment;
    }
}