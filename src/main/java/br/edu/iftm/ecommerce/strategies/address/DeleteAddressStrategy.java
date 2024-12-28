package br.edu.iftm.ecommerce.strategies.address;

import br.edu.iftm.ecommerce.models.Address;
import br.edu.iftm.ecommerce.repositories.AddressRepository;

public class DeleteAddressStrategy implements AddressStrategy {

    @Override
    public void execute(Address address, AddressRepository addressRepository) {
        System.out.println("Removendo endereço...");
        addressRepository.delete(address);
        System.out.println("Endereço removido com sucesso!");
    }
}
