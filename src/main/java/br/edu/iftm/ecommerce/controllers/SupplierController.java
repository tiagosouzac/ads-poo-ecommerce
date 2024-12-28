package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.Supplier;
import br.edu.iftm.ecommerce.repositories.SupplierRepository;
import br.edu.iftm.ecommerce.strategies.supplier.DeleteSupplierStrategy;
import br.edu.iftm.ecommerce.strategies.supplier.SaveSupplierStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SupplierController {
    private final SupplierRepository supplierRepository;
    private final SaveSupplierStrategy saveSuppilerStrategy;
    private final DeleteSupplierStrategy deleteSuppilerStrategy;

    @Autowired
    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
        this.saveSuppilerStrategy = new SaveSupplierStrategy();
        this.deleteSuppilerStrategy = new DeleteSupplierStrategy();
    }

    public List<Supplier> getSuppliers() {
        return this.supplierRepository.findAll();
    }

    public void saveSupplier(Supplier supplier) {
        this.saveSuppilerStrategy.execute(supplier, this.supplierRepository);
    }

    public void deleteSupplier(Supplier supplier) {
        this.deleteSuppilerStrategy.execute(supplier, this.supplierRepository);
    }
}
