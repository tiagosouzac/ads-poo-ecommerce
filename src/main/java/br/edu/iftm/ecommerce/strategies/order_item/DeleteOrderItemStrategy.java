package br.edu.iftm.ecommerce.strategies.order_item;

import br.edu.iftm.ecommerce.models.OrderItem;
import br.edu.iftm.ecommerce.repositories.OrderItemRepository;

public class DeleteOrderItemStrategy implements OrderItemStrategy {

    @Override
    public void execute(OrderItem orderItem, OrderItemRepository orderItemRepository) {
        System.out.println("Removendo item...");
        orderItemRepository.delete(orderItem);
        System.out.println("Item removido com sucesso!");
    }
}
