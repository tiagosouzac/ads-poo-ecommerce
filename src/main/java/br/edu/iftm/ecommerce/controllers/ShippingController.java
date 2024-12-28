package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.Shipping;
import br.edu.iftm.ecommerce.repositories.ShippingRepository;
import br.edu.iftm.ecommerce.strategies.shipping.DeleteShippingStrategy;
import br.edu.iftm.ecommerce.strategies.shipping.SaveShippingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ShippingController {
    private final ShippingRepository shippingRepository;
    private final SaveShippingStrategy saveShippingStrategy;
    private final DeleteShippingStrategy deleteShippingStrategy;

    @Autowired
    public ShippingController(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
        this.saveShippingStrategy = new SaveShippingStrategy();
        this.deleteShippingStrategy = new DeleteShippingStrategy();
    }

    public List<Shipping> getShippings() {
        return this.shippingRepository.findAll();
    }

    public void saveShipping(Shipping shipping) {
        this.saveShippingStrategy.execute(shipping, this.shippingRepository);
    }

    public void deleteShipping(Shipping shipping) {
        this.deleteShippingStrategy.execute(shipping, this.shippingRepository);
    }
}
