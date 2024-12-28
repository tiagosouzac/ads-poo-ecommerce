package br.edu.iftm.ecommerce.strategies.address;

import br.edu.iftm.ecommerce.models.Address;
import br.edu.iftm.ecommerce.repositories.AddressRepository;

public class SaveAddressStrategy implements AddressStrategy {

    @Override
    public void execute(Address adress, AddressRepository addressRepository) {
        System.out.println("Salvando endereço...");
        addressRepository.save(adress);
        System.out.println("Endereço salvo com sucesso!");
    }
}
