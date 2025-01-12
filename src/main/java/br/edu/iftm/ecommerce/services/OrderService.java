package br.edu.iftm.ecommerce.services;

import br.edu.iftm.ecommerce.models.Order;
import br.edu.iftm.ecommerce.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    public OrderRepository orderRepository;

    public List<Order> findAll() {
        System.out.println("Buscando todas as ordens...");
        List<Order> orders = orderRepository.findAll();
        System.out.println("Ordens encontradas com sucesso!");
        return orders;
    }

    public Order save(Order order) {
        System.out.println("Salvando ordem...");
        order = orderRepository.save(order);
        System.out.println("Ordem salva com sucesso!");
        return order;
    }

    public void delete(Order order) {
        System.out.println("Removendo ordem...");
        orderRepository.delete(order);
        System.out.println("Ordem removida com sucesso!");
    }
}
