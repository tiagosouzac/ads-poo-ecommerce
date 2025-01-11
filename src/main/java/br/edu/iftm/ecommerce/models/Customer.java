package br.edu.iftm.ecommerce.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "customers")
public class Customer extends Addressable {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @OneToMany(mappedBy = "addressable", cascade = CascadeType.ALL)
    private List<Address> addresses;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    @OneToMany(mappedBy = "customer")
    private List<Payment> payments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public static class CustomerBuilder {
        private String name;
        private String email;
        private String phone;
        private List<Address> addresses;
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

        public CustomerBuilder addresses(List<Address> addresses) {
            this.addresses = addresses;
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
            customer.setAddresses(this.addresses);
            customer.setOrders(this.orders);
            customer.setPayments(this.payments);
            return customer;
        }
    }
}
