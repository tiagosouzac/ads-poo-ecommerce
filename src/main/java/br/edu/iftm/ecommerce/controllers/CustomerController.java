package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.Customer;
import br.edu.iftm.ecommerce.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    public List<Customer> getCustomers() {
        return this.customerService.findAll();
    }

    public Customer getCustomerById(UUID customerId) {
        return this.customerService.findById(customerId);
    }

    public List<Customer> getCustomersByName(String name) {
        return this.customerService.findAllByName(name);
    }

    public Customer getCustomerByName(String name) {
        return this.customerService.findByName(name);
    }

    public void saveCustomer(Customer customer) {
        this.customerService.save(customer);
    }

    public void deleteCustomer(Customer customer) {
        this.customerService.delete(customer);
    }
}
