package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.Shipping;
import br.edu.iftm.ecommerce.repositories.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ShippingController {
    private final ShippingRepository shippingRepository;

    @Autowired
    public ShippingController(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    public List<Shipping> getShippings() {
        return this.shippingRepository.findAll();
    }

    public void saveShipping(Shipping shipping) {
        this.shippingRepository.save(shipping);
    }

    public void deleteShipping(Shipping shipping) {
        this.shippingRepository.delete(shipping);
    }
}
