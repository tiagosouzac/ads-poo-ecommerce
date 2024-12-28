package br.edu.iftm.ecommerce.strategies.order;

import br.edu.iftm.ecommerce.models.Order;
import br.edu.iftm.ecommerce.repositories.OrderRepository;

public class SaveOrderStrategy implements OrderStrategy {

    @Override
    public void execute(Order order, OrderRepository orderRepository) {
        System.out.println("Salvando ordem...");
        orderRepository.save(order);
        System.out.println("Ordem salva com sucesso!");
    }
}
