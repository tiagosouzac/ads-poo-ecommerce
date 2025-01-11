package br.edu.iftm.ecommerce.builders;

import br.edu.iftm.ecommerce.models.Category;
import br.edu.iftm.ecommerce.models.Product;

import java.util.List;

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
