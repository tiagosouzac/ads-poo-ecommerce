package br.edu.iftm.ecommerce.strategies.product;

import br.edu.iftm.ecommerce.models.Product;
import br.edu.iftm.ecommerce.repositories.ProductRepository;

public interface ProductStrategy {
    void execute(Product product, ProductRepository productRepository);
}
