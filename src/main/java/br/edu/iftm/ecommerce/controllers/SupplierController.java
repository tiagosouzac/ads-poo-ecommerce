package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.Supplier;
import br.edu.iftm.ecommerce.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    public List<Supplier> getSuppliers() {
        return this.supplierService.findAll();
    }

    public Supplier getSupplierById(UUID supplierId) {
        return this.supplierService.findById(supplierId);
    }

    public void saveSupplier(Supplier supplier) {
        this.supplierService.save(supplier);
    }

    public void deleteSupplier(Supplier supplier) {
        this.supplierService.delete(supplier);
    }
}
