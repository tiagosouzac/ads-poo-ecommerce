package br.edu.iftm.ecommerce.strategies.shipping;

import br.edu.iftm.ecommerce.models.Shipping;
import br.edu.iftm.ecommerce.repositories.ShippingRepository;

public interface ShippingStrategy {
    void execute(Shipping shipping, ShippingRepository shippingRepository);
}
