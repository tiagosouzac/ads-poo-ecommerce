package br.edu.iftm.ecommerce.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier extends Addressable {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cnpj;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "addressable", cascade = CascadeType.ALL)
    private List<Address> addresses;

    @OneToMany(mappedBy = "supplier")
    private List<Product> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public static class SupplierBuilder {
        private String name;
        private String cnpj;
        private String phone;
        private String email;
        private List<Address> addresses;
        private List<Product> products;

        public SupplierBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SupplierBuilder cnpj(String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        public SupplierBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public SupplierBuilder email(String email) {
            this.email = email;
            return this;
        }

        public SupplierBuilder addresses(List<Address> addresses) {
            this.addresses = addresses;
            return this;
        }

        public SupplierBuilder products(List<Product> products) {
            this.products = products;
            return this;
        }

        public Supplier build() {
            Supplier supplier = new Supplier();
            supplier.setName(this.name);
            supplier.setCnpj(this.cnpj);
            supplier.setPhone(this.phone);
            supplier.setEmail(this.email);
            supplier.setAddresses(this.addresses);
            supplier.setProducts(this.products);
            return supplier;
        }
    }
}
