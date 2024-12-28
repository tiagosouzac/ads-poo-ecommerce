package br.edu.iftm.ecommerce.strategies.supplier;

import br.edu.iftm.ecommerce.models.Supplier;
import br.edu.iftm.ecommerce.repositories.SupplierRepository;

public interface SupplierStrategy {
    void execute(Supplier supplier, SupplierRepository supplierRepository);
}
