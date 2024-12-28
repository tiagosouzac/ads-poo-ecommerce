package br.edu.iftm.ecommerce.strategies.customer;

import br.edu.iftm.ecommerce.models.Customer;
import br.edu.iftm.ecommerce.repositories.CustomerRepository;

public class SaveCustomerStrategy implements CustomerStrategy {

    @Override
    public void execute(Customer customer, CustomerRepository customerRepository) {
        System.out.println("Salvando cliente...");
        customerRepository.save(customer);
        System.out.println("Cliente salvo com sucesso!");
    }
}
