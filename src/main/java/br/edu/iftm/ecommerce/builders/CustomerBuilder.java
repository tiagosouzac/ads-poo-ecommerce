package br.edu.iftm.ecommerce.builders;

import br.edu.iftm.ecommerce.models.Address;
import br.edu.iftm.ecommerce.models.Customer;
import br.edu.iftm.ecommerce.models.Order;
import br.edu.iftm.ecommerce.models.Payment;

import java.util.List;

public class CustomerBuilder {
    private String name;
    private String email;
    private String phone;
    private Address address;
    private List<Order> orders;
    private List<Payment> payments;

    public CustomerBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CustomerBuilder email(String email) {
        this.email = email;
        return this;
    }

    public CustomerBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public CustomerBuilder address(Address address) {
        this.address = address;
        return this;
    }

    public CustomerBuilder orders(List<Order> orders) {
        this.orders = orders;
        return this;
    }

    public CustomerBuilder payments(List<Payment> payments) {
        this.payments = payments;
        return this;
    }

    public Customer build() {
        Customer customer = new Customer();
        customer.setName(this.name);
        customer.setEmail(this.email);
        customer.setPhone(this.phone);
        customer.setAddress(this.address);
        customer.setOrders(this.orders);
        customer.setPayments(this.payments);
        return customer;
    }
}
