package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.Supplier;
import br.edu.iftm.ecommerce.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SupplierController {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getSuppliers() {
        return this.supplierRepository.findAll();
    }

    public void saveSupplier(Supplier supplier) {
        this.supplierRepository.save(supplier);
    }

    public void deleteSupplier(Supplier supplier) {
        this.supplierRepository.delete(supplier);
    }
}
