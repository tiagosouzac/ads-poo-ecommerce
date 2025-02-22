package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.Category;
import br.edu.iftm.ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    public List<Category> getCategories() {
        return this.categoryService.findAll();
    }

    public Category getCategoryById(UUID categoryId) {
        return this.categoryService.findById(categoryId);
    }

    public void saveCategory(Category category) {
        this.categoryService.save(category);
    }

    public void deleteCategory(Category category) {
        this.categoryService.delete(category);
    }
}
