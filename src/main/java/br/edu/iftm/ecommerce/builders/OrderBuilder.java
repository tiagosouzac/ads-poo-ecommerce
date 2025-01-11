package br.edu.iftm.ecommerce.builders;

import br.edu.iftm.ecommerce.enums.OrderStatus;
import br.edu.iftm.ecommerce.models.Customer;
import br.edu.iftm.ecommerce.models.Order;
import br.edu.iftm.ecommerce.models.OrderItem;
import br.edu.iftm.ecommerce.models.Payment;

import java.math.BigDecimal;
import java.util.List;

public class OrderBuilder {
    private BigDecimal discount;
    private BigDecimal subtotal;
    private BigDecimal total;
    private OrderStatus status;
    private Customer customer;
    private List<OrderItem> items;
    private Payment payment;

    public OrderBuilder discount(BigDecimal discount) {
        this.discount = discount;
        return this;
    }

    public OrderBuilder subtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
        return this;
    }

    public OrderBuilder total(BigDecimal total) {
        this.total = total;
        return this;
    }

    public OrderBuilder status(OrderStatus status) {
        this.status = status;
        return this;
    }

    public OrderBuilder customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public OrderBuilder items(List<OrderItem> items) {
        this.items = items;
        return this;
    }

    public OrderBuilder payment(Payment payment) {
        this.payment = payment;
        return this;
    }

    public Order build() {
        Order order = new Order();
        order.setDiscount(this.discount);
        order.setSubtotal(this.subtotal);
        order.setTotal(this.total);
        order.setStatus(this.status);
        order.setCustomer(this.customer);
        order.setItems(this.items);
        order.setPayment(this.payment);
        return order;
    }
}
