package br.edu.iftm.ecommerce.strategies.order_item;

import br.edu.iftm.ecommerce.models.OrderItem;
import br.edu.iftm.ecommerce.repositories.OrderItemRepository;

public interface OrderItemStrategy {
    void execute(OrderItem orderItem, OrderItemRepository orderItemRepository);
}
