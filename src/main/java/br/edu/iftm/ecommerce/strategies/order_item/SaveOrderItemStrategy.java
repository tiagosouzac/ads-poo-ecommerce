package br.edu.iftm.ecommerce.strategies.order_item;

import br.edu.iftm.ecommerce.models.OrderItem;
import br.edu.iftm.ecommerce.repositories.OrderItemRepository;

public class SaveOrderItemStrategy implements OrderItemStrategy {

    @Override
    public void execute(OrderItem orderItem, OrderItemRepository orderItemRepository) {
        System.out.println("Salvando item...");
        orderItemRepository.save(orderItem);
        System.out.println("Item salvo com sucesso!");
    }
}
