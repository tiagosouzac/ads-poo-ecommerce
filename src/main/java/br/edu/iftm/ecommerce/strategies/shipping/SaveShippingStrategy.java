package br.edu.iftm.ecommerce.strategies.shipping;

import br.edu.iftm.ecommerce.models.Shipping;
import br.edu.iftm.ecommerce.repositories.ShippingRepository;

public class SaveShippingStrategy implements ShippingStrategy {

    @Override
    public void execute(Shipping shipping, ShippingRepository shippingRepository) {
        System.out.println("Salvando remessa...");
        shippingRepository.save(shipping);
        System.out.println("Remessa salva com sucesso!");
    }
}
