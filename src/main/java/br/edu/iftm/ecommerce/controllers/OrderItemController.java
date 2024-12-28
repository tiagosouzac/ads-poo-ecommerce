package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.OrderItem;
import br.edu.iftm.ecommerce.repositories.OrderItemRepository;
import br.edu.iftm.ecommerce.strategies.order_item.DeleteOrderItemStrategy;
import br.edu.iftm.ecommerce.strategies.order_item.SaveOrderItemStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderItemController {
    private final OrderItemRepository orderItemRepository;
    private final SaveOrderItemStrategy saveOrderItemStrategy;
    private final DeleteOrderItemStrategy deleteOrderItemStrategy;

    @Autowired
    public OrderItemController(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
        this.saveOrderItemStrategy = new SaveOrderItemStrategy();
        this.deleteOrderItemStrategy = new DeleteOrderItemStrategy();
    }

    public List<OrderItem> getOrderItems() {
        return this.orderItemRepository.findAll();
    }

    public void saveOrderItem(OrderItem orderItem) {
        this.saveOrderItemStrategy.execute(orderItem, this.orderItemRepository);
    }

    public void deleteOrderItem(OrderItem orderItem) {
        this.deleteOrderItemStrategy.execute(orderItem, this.orderItemRepository);
    }
}
