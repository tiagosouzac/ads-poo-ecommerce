package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.OrderItem;
import br.edu.iftm.ecommerce.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderItemController {
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemController(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> getOrderItems() {
        return this.orderItemRepository.findAll();
    }

    public void saveOrderItem(OrderItem orderItem) {
        this.orderItemRepository.save(orderItem);
    }

    public void deleteOrderItem(OrderItem orderItem) {
        this.orderItemRepository.delete(orderItem);
    }
}
