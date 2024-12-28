package br.edu.iftm.ecommerce.strategies.customer;

import br.edu.iftm.ecommerce.models.Customer;
import br.edu.iftm.ecommerce.repositories.CustomerRepository;

public class DeleteCustomerStrategy implements CustomerStrategy {

    @Override
    public void execute(Customer customer, CustomerRepository customerRepository) {
        System.out.println("Removendo cliente...");
        customerRepository.delete(customer);
        System.out.println("Cliente removido com sucesso!");
    }
}
