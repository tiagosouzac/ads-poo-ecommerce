package br.edu.iftm.ecommerce.strategies.brand;

import br.edu.iftm.ecommerce.models.Brand;
import br.edu.iftm.ecommerce.repositories.BrandRepository;

public class SaveBrandStrategy implements BrandStrategy {

    @Override
    public void execute(Brand brand, BrandRepository brandRepository) {
        System.out.println("Salvando marca...");
        brandRepository.save(brand);
        System.out.println("Marca salva com sucesso!");
    }
}
