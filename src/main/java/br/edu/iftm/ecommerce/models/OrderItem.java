package br.edu.iftm.ecommerce.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, scale = 2)
    private BigDecimal discount;

    @Column(nullable = false, scale = 2)
    private BigDecimal subtotal;

    @Column(nullable = false, scale = 2)
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public class OrderItemBuilder {
        private Integer quantity;
        private BigDecimal discount;
        private BigDecimal subtotal;
        private BigDecimal total;
        private Product product;
        private Order order;

        public OrderItemBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderItemBuilder discount(BigDecimal discount) {
            this.discount = discount;
            return this;
        }

        public OrderItemBuilder subtotal(BigDecimal subtotal) {
            this.subtotal = subtotal;
            return this;
        }

        public OrderItemBuilder total(BigDecimal total) {
            this.total = total;
            return this;
        }

        public OrderItemBuilder product(Product product) {
            this.product = product;
            return this;
        }

        public OrderItemBuilder order(Order order) {
            this.order = order;
            return this;
        }

        public OrderItem build() {
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(this.quantity);
            orderItem.setDiscount(this.discount);
            orderItem.setSubtotal(this.subtotal);
            orderItem.setTotal(this.total);
            orderItem.setProduct(this.product);
            orderItem.setOrder(this.order);
            return orderItem;
        }
    }
}
