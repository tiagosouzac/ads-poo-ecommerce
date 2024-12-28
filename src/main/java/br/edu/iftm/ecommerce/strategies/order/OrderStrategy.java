package br.edu.iftm.ecommerce.strategies.order;

import br.edu.iftm.ecommerce.models.Order;
import br.edu.iftm.ecommerce.repositories.OrderRepository;

public interface OrderStrategy {
    void execute(Order order, OrderRepository orderRepository);
}
