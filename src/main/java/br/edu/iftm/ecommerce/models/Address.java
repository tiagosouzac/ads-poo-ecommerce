package br.edu.iftm.ecommerce.models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private String complement;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "addressable_id", nullable = false)
    private Addressable addressable;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Addressable getAddressable() {
        return addressable;
    }

    public void setAddressable(Addressable addressable) {
        this.addressable = addressable;
    }

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
}
