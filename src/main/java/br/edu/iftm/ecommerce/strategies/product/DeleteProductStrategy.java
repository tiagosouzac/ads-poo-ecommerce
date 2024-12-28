package br.edu.iftm.ecommerce.strategies.product;

import br.edu.iftm.ecommerce.models.Product;
import br.edu.iftm.ecommerce.repositories.ProductRepository;

public class DeleteProductStrategy implements ProductStrategy {

    @Override
    public void execute(Product product, ProductRepository productRepository) {
        System.out.println("Removendo produto...");
        productRepository.delete(product);
        System.out.println("Produto removido com sucesso!");
    }
}
