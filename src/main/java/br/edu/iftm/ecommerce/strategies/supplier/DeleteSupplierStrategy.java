package br.edu.iftm.ecommerce.strategies.supplier;

import br.edu.iftm.ecommerce.models.Supplier;
import br.edu.iftm.ecommerce.repositories.SupplierRepository;

public class DeleteSupplierStrategy implements SupplierStrategy {

    @Override
    public void execute(Supplier supplier, SupplierRepository supplierRepository) {
        System.out.println("Removendo fornecedor...");
        supplierRepository.delete(supplier);
        System.out.println("Fornecedor removido com sucesso!");
    }
}
