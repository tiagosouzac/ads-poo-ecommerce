package br.edu.iftm.ecommerce.facade;

import br.edu.iftm.ecommerce.builders.OrderBuilder;
import br.edu.iftm.ecommerce.controllers.OrderController;
import br.edu.iftm.ecommerce.controllers.OrderItemController;
import br.edu.iftm.ecommerce.controllers.PaymentController;
import br.edu.iftm.ecommerce.controllers.ProductController;
import br.edu.iftm.ecommerce.enums.OrderStatus;
import br.edu.iftm.ecommerce.enums.PaymentType;
import br.edu.iftm.ecommerce.factories.PaymentMethodFactory;
import br.edu.iftm.ecommerce.models.Customer;
import br.edu.iftm.ecommerce.models.Order;
import br.edu.iftm.ecommerce.models.OrderItem;
import br.edu.iftm.ecommerce.models.Payment;
import br.edu.iftm.ecommerce.strategies.payment.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class OrderProcessor {
    private Customer customer;
    private List<OrderItem> orderItems;
    private PaymentType paymentType;

    @Autowired
    private OrderController orderController;

    @Autowired
    private OrderItemController orderItemController;

    @Autowired
    private PaymentController paymentController;

    @Autowired
    private ProductController productController;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public void processOrder() {
        Order order = saveOrder();
        saveOrderItems(order);
        savePayment(order);
        decreaseProductStock();
    }

    private Order saveOrder() {
        Order order = new OrderBuilder()
                .subtotal(getOrderSubTotal())
                .discount(getOrderDiscount())
                .total(getOrderTotal())
                .status(OrderStatus.PENDING)
                .customer(customer)
                .build();

        return orderController.saveOrder(order);
    }

    private BigDecimal getOrderSubTotal() {
        BigDecimal subTotal = BigDecimal.ZERO;

        for (OrderItem orderItem : orderItems) {
            BigDecimal itemSubtotal = orderItem.getSubtotal();
            subTotal = subTotal.add(itemSubtotal);
        }

        return subTotal;
    }

    private BigDecimal getOrderDiscount() {
        BigDecimal discount = BigDecimal.ZERO;

        for (OrderItem orderItem : orderItems) {
            BigDecimal itemDiscount = orderItem.getDiscount();
            discount = discount.add(itemDiscount);
        }

        return discount;
    }

    private BigDecimal getOrderTotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem orderItem : orderItems) {
            BigDecimal itemTotal = orderItem.getTotal();
            total = total.add(itemTotal);
        }

        return total;
    }

    private void saveOrderItems(Order order) {
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(order);
            orderItemController.saveOrderItem(orderItem);
        }
    }

    private void savePayment(Order order) {
        PaymentMethodFactory paymentMethodFactory = new PaymentMethodFactory();
        PaymentMethod paymentMethod = paymentMethodFactory.createPaymentMethod(paymentType);
        Payment payment = paymentMethod.createPayment(order, customer);

        paymentController.savePayment(payment);
    }

    private void decreaseProductStock() {
        for (OrderItem orderItem : orderItems) {
            productController.decreaseProductStock(orderItem.getProduct().getId(), orderItem.getQuantity());
        }
    }
}
