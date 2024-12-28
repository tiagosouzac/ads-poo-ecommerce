package br.edu.iftm.ecommerce.strategies.brand;

import br.edu.iftm.ecommerce.models.Brand;
import br.edu.iftm.ecommerce.repositories.BrandRepository;

public interface BrandStrategy {
    void execute(Brand brand, BrandRepository brandRepository);
}
