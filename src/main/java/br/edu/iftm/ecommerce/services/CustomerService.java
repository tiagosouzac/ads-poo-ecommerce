package br.edu.iftm.ecommerce.services;

import br.edu.iftm.ecommerce.models.Customer;
import br.edu.iftm.ecommerce.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    public CustomerRepository customerRepository;

    public List<Customer> findAll() {
        System.out.println("Buscando todos os clientes...");
        List<Customer> customers = customerRepository.findAll();
        System.out.println("Clientes encontrados!");
        return customers;
    }

    public void save(Customer customer) {
        System.out.println("Salvando cliente...");
        customerRepository.save(customer);
        System.out.println("Cliente salvo com sucesso!");
    }

    public void delete(Customer customer) {
        System.out.println("Removendo cliente...");
        customerRepository.delete(customer);
        System.out.println("Cliente removido com sucesso!");
    }
}
