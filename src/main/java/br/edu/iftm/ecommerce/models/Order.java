package br.edu.iftm.ecommerce.models;

import br.edu.iftm.ecommerce.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, scale = 2)
    private BigDecimal discount;

    @Column(nullable = false, scale = 2)
    private BigDecimal subtotal;

    @Column(nullable = false, scale = 2)
    private BigDecimal total;

    @Column(nullable = false)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;

    @OneToOne(mappedBy = "order")
    private Shipping shipping;

    @OneToOne(mappedBy = "order")
    private Payment payment;
}
