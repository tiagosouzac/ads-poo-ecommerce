package br.edu.iftm.ecommerce.services;

import br.edu.iftm.ecommerce.models.Product;
import br.edu.iftm.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    public ProductRepository productRepository;

    public List<Product> findAll() {
        System.out.println("Buscando todos os produtos...");
        List<Product> products = productRepository.findAll();
        System.out.println("Produtos encontrados!");
        return products;
    }

    public List<Product> findByProductName(String productName) {
        System.out.println("Buscando produto pelo nome...");
        List<Product> product = productRepository.findByNameContainingIgnoreCase(productName);
        System.out.println("Produto encontrado!");
        return product;
    }

    public List<Product> findByCategoryName(String categoryName) {
        System.out.println("Buscando produto pelo nome da categoria...");
        List<Product> product = productRepository.findByCategoryNameContainingIgnoreCase(categoryName);
        System.out.println("Produto encontrado!");
        return product;
    }

    public List<Product> findByBrandName(String brandName) {
        System.out.println("Buscando produto pelo nome da marca...");
        List<Product> product = productRepository.findByBrandNameContainingIgnoreCase(brandName);
        System.out.println("Produto encontrado!");
        return product;
    }

    public List<Product> findBySupplierName(String supplierName) {
        System.out.println("Buscando produto pelo nome do fornecedor...");
        List<Product> product = productRepository.findBySupplierNameContainingIgnoreCase(supplierName);
        System.out.println("Produto encontrado!");
        return product;
    }

    public void save(Product product) {
        System.out.println("Salvando produto...");
        productRepository.save(product);
        System.out.println("Produto salvo com sucesso!");
    }

    public void delete(Product product) {
        System.out.println("Removendo produto...");
        productRepository.delete(product);
        System.out.println("Produto removido com sucesso!");
    }
}
