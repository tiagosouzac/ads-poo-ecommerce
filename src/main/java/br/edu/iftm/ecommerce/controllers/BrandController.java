package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.Brand;
import br.edu.iftm.ecommerce.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BrandController {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> getBrands() {
        return this.brandRepository.findAll();
    }

    public void saveBrand(Brand brand) {
        this.brandRepository.save(brand);
    }

    public void deleteBrand(Brand brand) {
        this.brandRepository.delete(brand);
    }
}
