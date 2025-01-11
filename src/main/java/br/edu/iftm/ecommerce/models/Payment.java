package br.edu.iftm.ecommerce.models;

import br.edu.iftm.ecommerce.enums.PaymentStatus;
import br.edu.iftm.ecommerce.enums.PaymentType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private PaymentType type;

    @Column(nullable = false)
    private PaymentStatus status;

    @Column(nullable = false)
    private String transactionCode;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

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
}
