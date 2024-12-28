package br.edu.iftm.ecommerce.strategies.product;

import br.edu.iftm.ecommerce.models.Product;
import br.edu.iftm.ecommerce.repositories.ProductRepository;

public class SaveProductStrategy implements ProductStrategy {

    @Override
    public void execute(Product product, ProductRepository productRepository) {
        System.out.println("Salvando produto...");
        productRepository.save(product);
        System.out.println("Produto salvo com sucesso!");
    }
}
