package br.edu.iftm.ecommerce.builders;

import br.edu.iftm.ecommerce.models.Brand;
import br.edu.iftm.ecommerce.models.Product;

import java.util.List;

public class BrandBuilder {
    private String name;
    private String website;
    private String email;
    private String phone;
    private List<Product> products;

    public BrandBuilder name(String name) {
        this.name = name;
        return this;
    }

    public BrandBuilder website(String website) {
        this.website = website;
        return this;
    }

    public BrandBuilder email(String email) {
        this.email = email;
        return this;
    }

    public BrandBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public BrandBuilder products(List<Product> products) {
        this.products = products;
        return this;
    }

    public Brand build() {
        Brand brand = new Brand();
        brand.setName(this.name);
        brand.setWebsite(this.website);
        brand.setEmail(this.email);
        brand.setPhone(this.phone);
        brand.setProducts(this.products);
        return brand;
    }
}
