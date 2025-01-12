package br.edu.iftm.ecommerce.services;

import br.edu.iftm.ecommerce.models.Supplier;
import br.edu.iftm.ecommerce.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SupplierService {
    @Autowired
    public SupplierRepository supplierRepository;

    public List<Supplier> findAll() {
        System.out.println("Buscando todos os fornecedores...");
        List<Supplier> suppliers = supplierRepository.findAll();
        System.out.println("Fornecedores encontrados!");
        return suppliers;
    }

    public Supplier findById(UUID supplierId) {
        System.out.println("Buscando fornecedor pelo id: " + supplierId);
        Supplier supplier = supplierRepository.findById(supplierId).orElse(null);
        System.out.println("Fornecedor encontrado!");
        return supplier;
    }

    public void save(Supplier supplier) {
        System.out.println("Salvando fornecedor...");
        supplierRepository.save(supplier);
        System.out.println("Fornecedor salvo com sucesso!");
    }

    public void delete(Supplier supplier) {
        System.out.println("Removendo fornecedor...");
        supplierRepository.delete(supplier);
        System.out.println("Fornecedor removido com sucesso!");
    }
}
