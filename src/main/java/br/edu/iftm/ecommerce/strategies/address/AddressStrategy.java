package br.edu.iftm.ecommerce.strategies.address;

import br.edu.iftm.ecommerce.models.Address;
import br.edu.iftm.ecommerce.repositories.AddressRepository;

public interface AddressStrategy {
    void execute(Address address, AddressRepository addressRepository);
}
