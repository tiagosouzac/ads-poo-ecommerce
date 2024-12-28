package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.Address;
import br.edu.iftm.ecommerce.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AddressController {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAddresses() {
       return this.addressRepository.findAll();
    }

    public void saveAddress(Address address) {
        this.addressRepository.save(address);
    }

    public void deleteAddress(Address address) {
        this.addressRepository.delete(address);
    }
}
