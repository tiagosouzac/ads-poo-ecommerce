package br.edu.iftm.ecommerce.services;

import br.edu.iftm.ecommerce.models.Category;
import br.edu.iftm.ecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    public CategoryRepository categoryRepository;

    public List<Category> findAll() {
        System.out.println("Buscando todas as categorias...");
        List<Category> categories = categoryRepository.findAll();
        System.out.println("Categorias encontradas!");
        return categories;
    }

    public void save(Category category) {
        System.out.println("Salvando categoria...");
        categoryRepository.save(category);
        System.out.println("Categoria salva com sucesso!");
    }

    public void update(Category category) {
        System.out.println("Atualizando categoria...");
        categoryRepository.save(category);
        System.out.println("Categoria atualizada com sucesso!");
    }
    
    public void delete(Category category) {
        System.out.println("Removendo categoria...");
        categoryRepository.delete(category);
        System.out.println("Categoria removida com sucesso!");
    }
}
