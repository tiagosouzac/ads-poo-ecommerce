package br.edu.iftm.ecommerce.services;

import br.edu.iftm.ecommerce.models.Address;
import br.edu.iftm.ecommerce.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AddressService {
    @Autowired
    public AddressRepository addressRepository;

    public List<Address> findAll() {
        System.out.println("Buscando todos os endereços...");
        List<Address> addresses = addressRepository.findAll();
        System.out.println("Endereços encontrados!");
        return addresses;
    }

    public Address findByAddressableId(UUID addressableId) {
        System.out.println("Buscando endereço pelo ID do endereçável...");
        Address address = addressRepository.findByAddressableId(addressableId);
        System.out.println("Endereço encontrado!");
        return address;
    }

    public void save(Address address) {
        System.out.println("Salvando endereço...");
        addressRepository.save(address);
        System.out.println("Endereço salvo com sucesso!");
    }

    public void delete(Address address) {
        System.out.println("Removendo endereço...");
        addressRepository.delete(address);
        System.out.println("Endereço removido com sucesso!");
    }
}
