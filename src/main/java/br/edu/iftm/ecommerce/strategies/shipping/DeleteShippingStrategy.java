package br.edu.iftm.ecommerce.strategies.shipping;

import br.edu.iftm.ecommerce.models.Shipping;
import br.edu.iftm.ecommerce.repositories.ShippingRepository;

public class DeleteShippingStrategy implements ShippingStrategy {

    @Override
    public void execute(Shipping shipping, ShippingRepository shippingRepository) {
        System.out.println("Removendo remessa...");
        shippingRepository.delete(shipping);
        System.out.println("Remessa removida com sucesso!");
    }
}
