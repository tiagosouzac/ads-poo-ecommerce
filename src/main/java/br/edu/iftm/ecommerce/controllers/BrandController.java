package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.Brand;
import br.edu.iftm.ecommerce.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class BrandController {
    @Autowired
    private BrandService brandService;

    public List<Brand> getBrands() {
        return this.brandService.findAll();
    }
    
     public Brand getBrandById(UUID brandId) {
        return this.brandService.findById(brandId);
    }

    public void saveBrand(Brand brand) {
        this.brandService.save(brand);
    }

    public void deleteBrand(Brand brand) {
        this.brandService.delete(brand);
    }
}
