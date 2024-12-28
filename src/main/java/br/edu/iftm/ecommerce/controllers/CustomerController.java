package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.Customer;
import br.edu.iftm.ecommerce.repositories.CustomerRepository;
import br.edu.iftm.ecommerce.strategies.customer.DeleteCustomerStrategy;
import br.edu.iftm.ecommerce.strategies.customer.SaveCustomerStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final SaveCustomerStrategy saveCustomerStrategy;
    private final DeleteCustomerStrategy deleteCustomerStrategy;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.saveCustomerStrategy = new SaveCustomerStrategy();
        this.deleteCustomerStrategy = new DeleteCustomerStrategy();
    }

    public List<Customer> getCustomers() {
        return this.customerRepository.findAll();
    }

    public void saveCustomer(Customer customer) {
        this.saveCustomerStrategy.execute(customer, customerRepository);
    }

    public void deleteCustomer(Customer customer) {
        this.deleteCustomerStrategy.execute(customer, customerRepository);
    }
}
