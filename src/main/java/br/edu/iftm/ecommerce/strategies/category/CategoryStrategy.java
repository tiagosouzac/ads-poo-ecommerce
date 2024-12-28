package br.edu.iftm.ecommerce.strategies.category;

import br.edu.iftm.ecommerce.models.Category;
import br.edu.iftm.ecommerce.repositories.CategoryRepository;

public interface CategoryStrategy {
    void execute(Category category, CategoryRepository categoryRepository);
}
