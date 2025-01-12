package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.OrderItem;
import br.edu.iftm.ecommerce.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    public List<OrderItem> getOrderItems() {
        return this.orderItemService.findAll();
    }

    public OrderItem getOrderItemById(UUID id) {
        return this.orderItemService.findById(id);
    }

    public void saveOrderItem(OrderItem orderItem) {
        this.orderItemService.save(orderItem);
    }

    public void deleteOrderItem(OrderItem orderItem) {
        this.orderItemService.delete(orderItem);
    }
}
