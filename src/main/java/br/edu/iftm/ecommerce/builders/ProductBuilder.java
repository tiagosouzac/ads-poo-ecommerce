package br.edu.iftm.ecommerce.builders;

import br.edu.iftm.ecommerce.models.Brand;
import br.edu.iftm.ecommerce.models.Category;
import br.edu.iftm.ecommerce.models.Product;
import br.edu.iftm.ecommerce.models.Supplier;

import java.math.BigDecimal;

public class ProductBuilder {
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
    private Category category;
    private Brand brand;
    private Supplier supplier;

    public ProductBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ProductBuilder stock(int stock) {
        this.stock = stock;
        return this;
    }

    public ProductBuilder category(Category category) {
        this.category = category;
        return this;
    }

    public ProductBuilder brand(Brand brand) {
        this.brand = brand;
        return this;
    }

    public ProductBuilder supplier(Supplier supplier) {
        this.supplier = supplier;
        return this;
    }

    public Product build() {
        Product product = new Product();
        product.setName(this.name);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setStock(this.stock);
        product.setCategory(this.category);
        product.setBrand(this.brand);
        product.setSupplier(this.supplier);
        return product;
    }
}
