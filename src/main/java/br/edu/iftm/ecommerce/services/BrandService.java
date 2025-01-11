package br.edu.iftm.ecommerce.services;

import br.edu.iftm.ecommerce.models.Brand;
import br.edu.iftm.ecommerce.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    public BrandRepository brandRepository;

    public List<Brand> findAll() {
        System.out.println("Buscando todas as marcas...");
        List<Brand> brands = brandRepository.findAll();
        System.out.println("Marcas encontradas!");
        return brands;
    }

    public void save(Brand brand) {
        System.out.println("Salvando marca...");
        brandRepository.save(brand);
        System.out.println("Marca salva com sucesso!");
    }

    public void delete(Brand brand) {
        System.out.println("Removendo marca...");
        brandRepository.delete(brand);
        System.out.println("Marca removida com sucesso!");
    }
}
