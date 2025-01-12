package br.edu.iftm.ecommerce.services;

import br.edu.iftm.ecommerce.models.OrderItem;
import br.edu.iftm.ecommerce.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderItemService {
    @Autowired
    public OrderItemRepository orderItemRepository;

    public List<OrderItem> findAll() {
        System.out.println("Buscando todos os itens...");
        List<OrderItem> orderItems = orderItemRepository.findAll();
        System.out.println("Itens encontrados!");
        return orderItems;
    }

    public OrderItem findById(UUID id) {
        System.out.println("Buscando item por id...");
        OrderItem orderItem = orderItemRepository.findById(id).orElse(null);
        System.out.println("Item encontrado!");
        return orderItem;
    }

    public void save(OrderItem orderItem) {
        System.out.println("Salvando item...");
        orderItemRepository.save(orderItem);
        System.out.println("Item salvo com sucesso!");
    }

    public void delete(OrderItem orderItem) {
        System.out.println("Removendo item...");
        orderItemRepository.delete(orderItem);
        System.out.println("Item removido com sucesso!");
    }
}
