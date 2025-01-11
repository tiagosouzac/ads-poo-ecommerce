package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.Product;
import br.edu.iftm.ecommerce.repositories.ProductRepository;
import br.edu.iftm.ecommerce.strategies.product.DeleteProductStrategy;
import br.edu.iftm.ecommerce.strategies.product.SaveProductStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductController {
    private final ProductRepository productRepository;
    private final SaveProductStrategy saveProductStrategy;
    private final DeleteProductStrategy deleteProductStrategy;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.saveProductStrategy = new SaveProductStrategy();
        this.deleteProductStrategy = new DeleteProductStrategy();
    }

    public List<Product> getProducts() {
        return this.productRepository.findAll();
    }

    public void saveProduct(Product product) {
        this.saveProductStrategy.execute(product, this.productRepository);
    }

    public void deleteProduct(Product product) {
        this.deleteProductStrategy.execute(product, this.productRepository);
    }
    
    public List<Product> searchProductsByCategoryName(String categoryName) {
        return this.productRepository.findByCategoryNameContainingIgnoreCase(categoryName);
    }

    public List<Product> searchProductsByBrandName(String brandName) {
        return this.productRepository.findByBrandNameContainingIgnoreCase(brandName);
    }

    public List<Product> searchProductsBySupplierName(String supplierName) {
        return this.productRepository.findBySupplierNameContainingIgnoreCase(supplierName);
    }
}
