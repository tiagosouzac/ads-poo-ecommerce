package br.edu.iftm.ecommerce.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String website;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @OneToMany(mappedBy = "brand")
    private List<Product> products;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

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

}
