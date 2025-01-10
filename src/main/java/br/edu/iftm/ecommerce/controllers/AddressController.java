package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.Address;
import br.edu.iftm.ecommerce.repositories.AddressRepository;
import br.edu.iftm.ecommerce.strategies.address.DeleteAddressStrategy;
import br.edu.iftm.ecommerce.strategies.address.SaveAddressStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class AddressController {
    private final AddressRepository addressRepository;
    private final SaveAddressStrategy saveAddressStrategy;
    private final DeleteAddressStrategy deleteAddressStrategy;

    @Autowired
    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
        this.saveAddressStrategy = new SaveAddressStrategy();
        this.deleteAddressStrategy = new DeleteAddressStrategy();
    }

    public List<Address> getAddresses() {
       return this.addressRepository.findAll();
    }

    public void saveAddress(Address address) {
        this.saveAddressStrategy.execute(address, addressRepository);
    }

    public void deleteAddress(Address address) {
        this.deleteAddressStrategy.execute(address, addressRepository);
    }
    
    public Address findAddressByAddressableId(UUID addressableId){
        return this.addressRepository.findByAddressableId(addressableId);
    }
}
