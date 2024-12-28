package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.Brand;
import br.edu.iftm.ecommerce.repositories.BrandRepository;
import br.edu.iftm.ecommerce.strategies.brand.DeleteBrandStrategy;
import br.edu.iftm.ecommerce.strategies.brand.SaveBrandStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BrandController {
    private final BrandRepository brandRepository;
    private final SaveBrandStrategy saveBrandStrategy;
    private final DeleteBrandStrategy deleteBrandStrategy;

    @Autowired
    public BrandController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
        this.saveBrandStrategy = new SaveBrandStrategy();
        this.deleteBrandStrategy = new DeleteBrandStrategy();
    }

    public List<Brand> getBrands() {
        return this.brandRepository.findAll();
    }

    public void saveBrand(Brand brand) {
        this.saveBrandStrategy.execute(brand, brandRepository);
    }

    public void deleteBrand(Brand brand) {
        this.deleteBrandStrategy.execute(brand, brandRepository);
    }
}
