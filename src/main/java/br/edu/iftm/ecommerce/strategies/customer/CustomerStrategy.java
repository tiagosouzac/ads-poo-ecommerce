package br.edu.iftm.ecommerce.strategies.customer;

import br.edu.iftm.ecommerce.models.Customer;
import br.edu.iftm.ecommerce.repositories.CustomerRepository;

public interface CustomerStrategy {
    void execute(Customer customer, CustomerRepository customerRepository);
}
