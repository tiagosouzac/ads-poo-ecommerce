package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.Address;
import br.edu.iftm.ecommerce.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class AddressController {
    @Autowired
    private AddressService addressService;

    public List<Address> getAddresses() {
        return this.addressService.findAll();
    }

    public void saveAddress(Address address) {
        this.addressService.save(address);
    }

    public void deleteAddress(Address address) {
        this.addressService.delete(address);
    }

    public Address findAddressByAddressableId(UUID addressableId) {
        return this.addressService.findByAddressableId(addressableId);
    }
}
