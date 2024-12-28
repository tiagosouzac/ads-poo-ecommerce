package br.edu.iftm.ecommerce.strategies.category;

import br.edu.iftm.ecommerce.models.Category;
import br.edu.iftm.ecommerce.repositories.CategoryRepository;

public class SaveCategoryStrategy implements CategoryStrategy {

    @Override
    public void execute(Category category, CategoryRepository categoryRepository) {
        System.out.println("Salvando categoria...");
        categoryRepository.save(category);
        System.out.println("Categoria salva com sucesso!");
    }
}
