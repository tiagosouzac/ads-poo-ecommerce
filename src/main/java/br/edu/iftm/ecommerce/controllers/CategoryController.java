package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.Category;
import br.edu.iftm.ecommerce.repositories.CategoryRepository;
import br.edu.iftm.ecommerce.strategies.category.DeleteCategoryStrategy;
import br.edu.iftm.ecommerce.strategies.category.SaveCategoryStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final SaveCategoryStrategy saveCategoryStrategy;
    private final DeleteCategoryStrategy deleteCategoryStrategy;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.saveCategoryStrategy = new SaveCategoryStrategy();
        this.deleteCategoryStrategy = new DeleteCategoryStrategy();
    }

    public List<Category> getCategories() {
        return this.categoryRepository.findAll();
    }

    public void saveCategory(Category category) {
        this.saveCategoryStrategy.execute(category, categoryRepository);
    }

    public void deleteCategory(Category category) {
        this.deleteCategoryStrategy.execute(category, categoryRepository);
    }
}
