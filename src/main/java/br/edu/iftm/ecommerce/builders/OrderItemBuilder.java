package br.edu.iftm.ecommerce.builders;

import br.edu.iftm.ecommerce.models.Order;
import br.edu.iftm.ecommerce.models.OrderItem;
import br.edu.iftm.ecommerce.models.Product;

import java.math.BigDecimal;

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