package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.Order;
import br.edu.iftm.ecommerce.repositories.OrderRepository;
import br.edu.iftm.ecommerce.strategies.order.DeleteOrderStrategy;
import br.edu.iftm.ecommerce.strategies.order.SaveOrderStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderController {
    private final OrderRepository orderRepository;
    private final SaveOrderStrategy saveOrderStrategy;
    private final DeleteOrderStrategy deleteOrderStrategy;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.saveOrderStrategy = new SaveOrderStrategy();
        this.deleteOrderStrategy = new DeleteOrderStrategy();
    }

    public List<Order> getOrders() {
        return this.orderRepository.findAll();
    }

    public void saveOrder(Order order) {
        this.saveOrderStrategy.execute(order, orderRepository);
    }

    public void deleteOrder(Order order) {
        this.deleteOrderStrategy.execute(order, orderRepository);
    }
}
