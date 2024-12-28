package br.edu.iftm.ecommerce.strategies.category;

import br.edu.iftm.ecommerce.models.Category;
import br.edu.iftm.ecommerce.repositories.CategoryRepository;

public class DeleteCategoryStrategy implements CategoryStrategy {

    @Override
    public void execute(Category category, CategoryRepository categoryRepository) {
        System.out.println("Removendo categoria...");
        categoryRepository.delete(category);
        System.out.println("Categoria removida com sucesso!");
    }
}
