package br.edu.iftm.ecommerce.builders;

import br.edu.iftm.ecommerce.models.Address;
import br.edu.iftm.ecommerce.models.Addressable;

public class AddressBuilder {
    private String street;
    private String number;
    private String neighborhood;
    private String complement;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private Addressable addressable;

    public AddressBuilder street(String street) {
        this.street = street;
        return this;
    }

    public AddressBuilder number(String number) {
        this.number = number;
        return this;
    }

    public AddressBuilder neighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
        return this;
    }

    public AddressBuilder complement(String complement) {
        this.complement = complement;
        return this;
    }

    public AddressBuilder city(String city) {
        this.city = city;
        return this;
    }

    public AddressBuilder state(String state) {
        this.state = state;
        return this;
    }

    public AddressBuilder country(String country) {
        this.country = country;
        return this;
    }

    public AddressBuilder zipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public AddressBuilder addressable(Addressable addressable) {
        this.addressable = addressable;
        return this;
    }

    public Address build() {
        Address address = new Address();
        address.setStreet(this.street);
        address.setNumber(this.number);
        address.setNeighborhood(this.neighborhood);
        address.setComplement(this.complement);
        address.setCity(this.city);
        address.setState(this.state);
        address.setCountry(this.country);
        address.setZipCode(this.zipCode);
        address.setAddressable(this.addressable);
        return address;
    }
}
