package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.Customer;
import br.edu.iftm.ecommerce.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CustomerController {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return this.customerRepository.findAll();
    }

    public void saveCustomer(Customer customer) {
        this.customerRepository.save(customer);
    }

    public void deleteCustomer(Customer customer) {
        this.customerRepository.delete(customer);
    }
}
