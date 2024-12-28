package br.edu.iftm.ecommerce.strategies.brand;

import br.edu.iftm.ecommerce.models.Brand;
import br.edu.iftm.ecommerce.repositories.BrandRepository;

public class DeleteBrandStrategy implements BrandStrategy {

    @Override
    public void execute(Brand brand, BrandRepository brandRepository) {
        System.out.println("Removendo marca...");
        brandRepository.delete(brand);
        System.out.println("Marca removida com sucesso!");
    }
}
