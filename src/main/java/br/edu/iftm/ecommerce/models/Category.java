package br.edu.iftm.ecommerce.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "category")
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public class CategoryBuilder {
        private String name;
        private String description;
        private List<Product> products;

        public CategoryBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CategoryBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CategoryBuilder products(List<Product> products) {
            this.products = products;
            return this;
        }

        public Category build() {
            Category category = new Category();
            category.setName(this.name);
            category.setDescription(this.description);
            category.setProducts(this.products);
            return category;
        }
    }
}