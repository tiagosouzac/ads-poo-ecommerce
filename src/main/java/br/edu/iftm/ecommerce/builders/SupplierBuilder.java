package br.edu.iftm.ecommerce.builders;

import br.edu.iftm.ecommerce.models.Address;
import br.edu.iftm.ecommerce.models.Product;
import br.edu.iftm.ecommerce.models.Supplier;

import java.util.List;

public class SupplierBuilder {
    private String name;
    private String cnpj;
    private String phone;
    private String email;
    private Address address;
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

    public SupplierBuilder address(Address address) {
        this.address = address;
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
        supplier.setAddress(this.address);
        supplier.setProducts(this.products);
        return supplier;
    }
}
