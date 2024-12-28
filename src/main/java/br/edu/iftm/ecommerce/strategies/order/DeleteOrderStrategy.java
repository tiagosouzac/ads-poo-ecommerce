package br.edu.iftm.ecommerce.strategies.order;

import br.edu.iftm.ecommerce.models.Order;
import br.edu.iftm.ecommerce.repositories.OrderRepository;

public class DeleteOrderStrategy implements OrderStrategy {

    @Override
    public void execute(Order order, OrderRepository orderRepository) {
        System.out.println("Removendo ordem...");
        orderRepository.delete(order);
        System.out.println("Ordem removida com sucesso!");
    }
}
