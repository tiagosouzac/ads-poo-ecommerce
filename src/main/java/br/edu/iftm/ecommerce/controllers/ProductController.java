package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.Product;
import br.edu.iftm.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    public List<Product> getProducts() {
        return this.productService.findAll();
    }

    public Product getProductById(UUID productId) {
        return this.productService.findById(productId);
    }

    public List<Product> getProductsByName(String productName) {
        return this.productService.findAllByProductName(productName);
    }

    public Product getProductByName(String productName) {
        return this.productService.findByProductName(productName);
    }

    public void saveProduct(Product product) {
        this.productService.save(product);
    }

    public void deleteProduct(Product product) {
        this.productService.delete(product);
    }

    public List<Product> searchProductsByName(String name) {
        return this.productService.findAllByProductName(name);
    }

    public List<Product> searchProductsByCategoryName(String categoryName) {
        return this.productService.findByCategoryName(categoryName);
    }

    public List<Product> searchProductsByBrandName(String brandName) {
        return this.productService.findByBrandName(brandName);
    }

    public List<Product> searchProductsBySupplierName(String supplierName) {
        return this.productService.findBySupplierName(supplierName);
    }

    public Product decreaseProductStock(UUID productId, int quantity) {
        return this.productService.decreaseStock(productId, quantity);
    }
}
