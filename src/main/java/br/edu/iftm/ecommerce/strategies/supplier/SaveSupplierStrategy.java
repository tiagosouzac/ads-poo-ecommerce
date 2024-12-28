package br.edu.iftm.ecommerce.strategies.supplier;

import br.edu.iftm.ecommerce.models.Supplier;
import br.edu.iftm.ecommerce.repositories.SupplierRepository;

public class SaveSupplierStrategy implements SupplierStrategy {

    @Override
    public void execute(Supplier supplier, SupplierRepository supplierRepository) {
        System.out.println("Salvando fornecedor...");
        supplierRepository.save(supplier);
        System.out.println("Fornecedor salvo com sucesso!");
    }
}
