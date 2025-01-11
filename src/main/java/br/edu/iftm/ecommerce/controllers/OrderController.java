package br.edu.iftm.ecommerce.controllers;

import br.edu.iftm.ecommerce.models.Order;
import br.edu.iftm.ecommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    public List<Order> getOrders() {
        return this.orderService.findAll();
    }

    public void saveOrder(Order order) {
        this.orderService.save(order);
    }

    public void deleteOrder(Order order) {
        this.orderService.delete(order);
    }
}
