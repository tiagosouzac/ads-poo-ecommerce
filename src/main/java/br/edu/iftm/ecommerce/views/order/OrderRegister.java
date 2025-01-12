package br.edu.iftm.ecommerce.views.order;

import br.edu.iftm.ecommerce.EcommerceApplication;
import br.edu.iftm.ecommerce.builders.OrderBuilder;
import br.edu.iftm.ecommerce.builders.OrderItemBuilder;
import br.edu.iftm.ecommerce.controllers.*;
import br.edu.iftm.ecommerce.enums.OrderStatus;
import br.edu.iftm.ecommerce.enums.PaymentType;
import br.edu.iftm.ecommerce.factories.PaymentMethodFactory;
import br.edu.iftm.ecommerce.models.*;
import br.edu.iftm.ecommerce.strategies.payment.PaymentMethod;
import br.edu.iftm.ecommerce.views.menu.MenuView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderRegister extends javax.swing.JFrame {

    @Autowired
    private CustomerController customerController;

    @Autowired
    private ProductController productController;

    @Autowired
    private OrderController orderController;

    @Autowired
    private PaymentController paymentController;

    @Autowired
    private OrderItemController orderItemController;

    private List<OrderItem> selectedOrderItems = new ArrayList<>();

    private final ApplicationContext context;

    /**
     * Creates new form RegisterCategory
     */

    @Autowired
    public OrderRegister(ApplicationContext context) {
        initComponents();
        this.context = context;

        setupCustomerCmb();
        setupProductCmb();
        setupPaymentCmb();
    }

    private void setupCustomerCmb() {
        customerTxt.addCaretListener((evt) -> {
            customerCmb.removeAllItems();

            String customer = customerTxt.getText().trim();

            if (!customer.isEmpty()) {
                List<Customer> customers = customerController.getCustomersByName(customer);

                for (Customer c : customers) {
                    customerCmb.addItem(c.getName());
                }
            }
        });
    }

    private Customer getSelectedCustomer() {
        int selectedIndex = customerCmb.getSelectedIndex();

        if (selectedIndex >= 0) {
            return customerController.getCustomerByName(customerCmb.getItemAt(selectedIndex));
        }

        return null;
    }

    private void setupProductCmb() {
        productTxt.addCaretListener((evt) -> {
            productCmb.removeAllItems();

            String product = productTxt.getText().trim();

            if (!product.isEmpty()) {
                List<Product> products = productController.getProductsByName(product);

                for (Product p : products) {
                    if (p.getStock() > 0) {
                        productCmb.addItem(p.getName());
                    } else {
                        productCmb.addItem(p.getName() + " (Indisponível)");
                    }
                }
            }
        });

        productCmb.addActionListener(this::onProductSelect);

        quantityTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                onQuantitySelect();
            }
        });

        itemDiscountTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                onDiscountSelect();
            }
        });
    }

    private Product getSelectedProduct() {
        int selectedIndex = productCmb.getSelectedIndex();

        if (selectedIndex >= 0) {
            return productController.getProductByName(productCmb.getItemAt(selectedIndex));
        }

        return null;
    }

    private int getSelectedProductQuantity() {
        int quantity = 1;

        if (!quantityTxt.getText().trim().isEmpty()) {
            try {
                quantity = Integer.parseInt(quantityTxt.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantidade inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        return quantity;
    }

    private BigDecimal getSelectedProductDiscount() {
        BigDecimal discount = BigDecimal.ZERO;

        if (!itemDiscountTxt.getText().trim().isEmpty()) {
            try {
                discount = new BigDecimal(itemDiscountTxt.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Desconto inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        return discount;
    }

    private void onProductSelect(ActionEvent e) {
        Product product = getSelectedProduct();

        if (product != null && product.getStock() > 0) {
            quantityTxt.setEnabled(true);
            itemDiscountTxt.setEnabled(true);
            itemPriceTxt.setText(product.getPrice().toString());
            itemTotalTxt.setText(product.getPrice().toString());
        } else {
            quantityTxt.setEnabled(false);
            itemDiscountTxt.setEnabled(false);
            itemPriceTxt.setText("");
            itemTotalTxt.setText("");
        }
    }

    private void onQuantitySelect() {
        int quantity = getSelectedProductQuantity();

        if (quantity <= 0) {
            JOptionPane.showMessageDialog(this, "Quantidade deve ser maior que 0.", "Erro", JOptionPane.ERROR_MESSAGE);
            quantityTxt.setText("");
        }

        updateItemTotal();
    }

    private void onDiscountSelect() {
        BigDecimal discount = getSelectedProductDiscount();

        if (discount.compareTo(BigDecimal.ZERO) < 0) {
            JOptionPane.showMessageDialog(this, "Desconto deve ser maior ou igual a 0.", "Erro", JOptionPane.ERROR_MESSAGE);
            itemDiscountTxt.setText("");
        }

        updateItemTotal();
    }

    private void updateItemTotal() {
        Product product = getSelectedProduct();

        if (product != null) {
            BigDecimal price = product.getPrice();
            BigDecimal discount = getSelectedProductDiscount();
            int quantity = getSelectedProductQuantity();

            if (quantity > product.getStock()) {
                JOptionPane.showMessageDialog(this, "Quantidade de produto indisponível em estoque. Disponível: " + product.getStock(), "Erro", JOptionPane.ERROR_MESSAGE);
                quantityTxt.setText(String.valueOf(product.getStock()));
                quantity = product.getStock();
            }

            BigDecimal subtotal = price.multiply(new BigDecimal(quantity));

            if (discount.compareTo(subtotal) > 0) {
                JOptionPane.showMessageDialog(this, "Desconto não pode ser maior que o valor do produto.", "Erro", JOptionPane.ERROR_MESSAGE);
                itemDiscountTxt.setText("");
                discount = BigDecimal.ZERO;
            }

            BigDecimal total = subtotal.subtract(discount);

            itemPriceTxt.setText(subtotal.toString());
            itemTotalTxt.setText(total.toString());
        }
    }

    private void addOrderItem(ActionEvent e) {
        Product product = getSelectedProduct();
        int quantity = getSelectedProductQuantity();
        BigDecimal subtotal = new BigDecimal(itemPriceTxt.getText().trim());
        BigDecimal discount = getSelectedProductDiscount();
        BigDecimal total = new BigDecimal(itemTotalTxt.getText().trim());

        if (product != null) {
            selectedOrderItems.removeIf(orderItem -> orderItem.getProduct().getId().equals(product.getId()));

            OrderItem orderItem = new OrderItemBuilder()
                    .product(product)
                    .quantity(quantity)
                    .subtotal(subtotal)
                    .discount(discount)
                    .total(total)
                    .build();

            selectedOrderItems.add(orderItem);
            fillTable(selectedOrderItems);

            updateOrderTotal();

            clearProductSelectorFields();
        }
    }

    private void removeOrderItem(ActionEvent e) {
        int selectedRow = orderItemTable.getSelectedRow();

        if (selectedRow >= 0) {
            OrderItem orderItem = selectedOrderItems.get(selectedRow);

            selectedOrderItems.remove(orderItem);
            fillTable(selectedOrderItems);

            updateOrderTotal();
        }
    }

    private void clearProductSelectorFields() {
        productTxt.setText("");
        productCmb.removeAllItems();
        quantityTxt.setText("");
        itemDiscountTxt.setText("");
        itemPriceTxt.setText("");
        itemTotalTxt.setText("");
        fillTable(selectedOrderItems);
    }

    private void setupPaymentCmb() {
        paymentCmb.removeAllItems();

        for (PaymentType paymentType : PaymentType.values()) {
            paymentCmb.addItem(paymentType.toString());
        }
    }

    private PaymentType getSelectedPaymentType() {
        int selectedIndex = paymentCmb.getSelectedIndex();

        if (selectedIndex >= 0) {
            return PaymentType.values()[selectedIndex];
        }

        return null;
    }

    private void updateOrderTotal() {
        BigDecimal subtotal = getOrderSubTotal();
        orderSubtotalTxt.setText(subtotal.toString());

        BigDecimal discount = getOrderDiscount();
        orderDiscountTxt.setText(discount.toString());

        BigDecimal total = getOrderTotal();
        orderTotalTxt.setText(total.toString());
    }

    private BigDecimal getOrderDiscount() {
        BigDecimal discount = BigDecimal.ZERO;

        for (OrderItem orderItem : selectedOrderItems) {
            BigDecimal itemDiscount = orderItem.getDiscount();
            discount = discount.add(itemDiscount);
        }

        return discount;
    }

    private BigDecimal getOrderSubTotal() {
        BigDecimal subTotal = BigDecimal.ZERO;

        for (OrderItem orderItem : selectedOrderItems) {
            BigDecimal itemSubtotal = orderItem.getSubtotal();
            subTotal = subTotal.add(itemSubtotal);
        }

        return subTotal;
    }

    private BigDecimal getOrderTotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem orderItem : selectedOrderItems) {
            BigDecimal itemTotal = orderItem.getTotal();
            total = total.add(itemTotal);
        }

        return total;
    }

    private void saveOrder(ActionEvent e) {
        Customer customer = getSelectedCustomer();

        if (customer == null) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (selectedOrderItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Adicione pelo menos um item à venda.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        PaymentType paymentType = getSelectedPaymentType();

        if (paymentType == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma forma de pagamento.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Order order = new OrderBuilder()
                .subtotal(getOrderSubTotal())
                .discount(getOrderDiscount())
                .total(getOrderTotal())
                .status(OrderStatus.PENDING)
                .customer(customer)
                .build();

        order = orderController.saveOrder(order);

        for (OrderItem orderItem : selectedOrderItems) {
            orderItem.setOrder(order);
            orderItemController.saveOrderItem(orderItem);
        }

        PaymentMethodFactory paymentMethodFactory = new PaymentMethodFactory();
        PaymentMethod paymentMethod = paymentMethodFactory.createPaymentMethod(paymentType);
        Payment payment = paymentMethod.createPayment(order, customer);

        paymentController.savePayment(payment);

        selectedOrderItems.forEach(orderItem -> {
            productController.decreaseProductStock(orderItem.getProduct().getId(), orderItem.getQuantity());
        });

        JOptionPane.showMessageDialog(this, "Venda realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        reset();
    }

    private void reset() {
        customerTxt.setText("");
        customerCmb.removeAllItems();
        clearProductSelectorFields();
        selectedOrderItems.clear();
        fillTable(selectedOrderItems);
        updateOrderTotal();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        customerTxt = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        productTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        productCmb = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        customerCmb = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        saveButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        orderItemTable = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        quantityTxt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        itemDiscountTxt = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        itemPriceTxt = new javax.swing.JTextField();
        itemTotalTxt = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        orderDiscountTxt = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        orderSubtotalTxt = new javax.swing.JTextField();
        orderTotalTxt = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        paymentCmb = new javax.swing.JComboBox<>();
        deleteButton = new javax.swing.JButton();
        menuButton = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Realizar Venda");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nome do Cliente:");

        customerTxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Produtos");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
        );

        addButton.setBackground(new java.awt.Color(0, 51, 153));
        addButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("Adicionar");
        addButton.addActionListener(this::addOrderItem);

        productTxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nome do produto:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Produto:");

        jPanel8.setBackground(new java.awt.Color(102, 102, 102));
        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Cliente");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel12)
        );

        customerCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerCmbActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Cliente:");

        saveButton1.setBackground(new java.awt.Color(0, 102, 51));
        saveButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        saveButton1.setForeground(new java.awt.Color(255, 255, 255));
        saveButton1.setText("Salvar");
        saveButton1.addActionListener(this::saveOrder);

        orderItemTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null}
                },
                new String[]{
                        "Nome", "Quantidade", "Desconto", "Valor Item", "Total"
                }
        ));
        jScrollPane1.setViewportView(orderItemTable);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Quantidade:");

        quantityTxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        quantityTxt.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Desconto:");

        itemDiscountTxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        itemDiscountTxt.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Valor Item:");


        itemPriceTxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        itemPriceTxt.setEnabled(false);

        itemTotalTxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        itemTotalTxt.setEnabled(false);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Total Item:");

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Valores Venda");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
        );

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Desconto:");

        orderDiscountTxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        orderDiscountTxt.setEnabled(false);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Subtotal:");

        orderSubtotalTxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        orderSubtotalTxt.setEnabled(false);

        orderTotalTxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        orderTotalTxt.setEnabled(false);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Total:");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Forma de Pagamento:");

        deleteButton.setBackground(new java.awt.Color(255, 0, 51));
        deleteButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("Excluir");
        deleteButton.addActionListener(this::removeOrderItem);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 52, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel14))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(productTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(productCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(131, 131, 131)
                                                .addComponent(quantityTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel16)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(itemPriceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(59, 59, 59)
                                                .addComponent(jLabel15)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addComponent(itemDiscountTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabel17)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(itemTotalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(141, 141, 141))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addGap(62, 62, 62)
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                        .addComponent(jLabel13)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(customerCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                                        .addComponent(jLabel2)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(customerTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                        .addGap(47, 47, 47)
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                        .addComponent(jLabel25)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(paymentCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                        .addComponent(jLabel18)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(orderDiscountTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGap(27, 27, 27)
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                        .addComponent(jLabel21)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(orderTotalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                        .addComponent(jLabel19)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(orderSubtotalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(307, 307, 307)
                                                .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(customerTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(customerCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(productTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(productCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(quantityTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14)
                                        .addComponent(itemPriceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(itemDiscountTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(itemTotalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel15)
                                        .addComponent(jLabel17))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(addButton)
                                        .addComponent(deleteButton))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel19)
                                        .addComponent(orderSubtotalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel25)
                                        .addComponent(paymentCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(orderTotalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel21)
                                        .addComponent(orderDiscountTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel18))
                                .addGap(18, 18, 18)
                                .addComponent(saveButton1)
                                .addContainerGap(21, Short.MAX_VALUE))
        );

        menuButton.setBackground(new java.awt.Color(102, 0, 51));
        menuButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        menuButton.setForeground(new java.awt.Color(255, 255, 255));
        menuButton.setText("Voltar ao Menu");
        menuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(menuButton)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(menuButton)
                                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void fillTable(List<OrderItem> selectedOrderList) {
        DefaultTableModel table = (DefaultTableModel)
                orderItemTable.getModel();
        orderItemTable.getColumnModel().getColumn(0).setPreferredWidth(180);
        orderItemTable.getColumnModel().getColumn(1).setPreferredWidth(180);
        orderItemTable.getColumnModel().getColumn(2).setPreferredWidth(180);
        orderItemTable.getColumnModel().getColumn(3).setPreferredWidth(180);
        orderItemTable.getColumnModel().getColumn(3).setPreferredWidth(180);

        table.setNumRows(0);

        selectedOrderList.forEach(orderItem -> {
            table.addRow(new Object[]{orderItem.getProduct().getName(), orderItem.getQuantity(), orderItem.getDiscount(), orderItem.getSubtotal(), orderItem.getTotal()});
        });
    }


    private void populatePaymentComboBox() {
        paymentCmb.removeAllItems();
        for (PaymentType paymentType : PaymentType.values()) {
            paymentCmb.addItem(paymentType.toString());
        }
    }

    private void menuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButtonActionPerformed
        MenuView menuView = context.getBean(MenuView.class);
        menuView.setVisible(true);
        dispose();
    }//GEN-LAST:event_menuButtonActionPerformed

    private void customerCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerCmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customerCmbActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OrderRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ApplicationContext context = SpringApplication.run(EcommerceApplication.class, args);

                java.awt.EventQueue.invokeLater(() -> {
                    OrderRegister supplierRegister = context.getBean(OrderRegister.class);
                    supplierRegister.setVisible(true);
                });
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JComboBox<String> customerCmb;
    private javax.swing.JTextField customerTxt;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField itemDiscountTxt;
    private javax.swing.JTextField itemPriceTxt;
    private javax.swing.JTextField itemTotalTxt;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton menuButton;
    private javax.swing.JTextField orderDiscountTxt;
    private javax.swing.JTable orderItemTable;
    private javax.swing.JTextField orderSubtotalTxt;
    private javax.swing.JTextField orderTotalTxt;
    private javax.swing.JComboBox<String> paymentCmb;
    private javax.swing.JComboBox<String> productCmb;
    private javax.swing.JTextField productTxt;
    private javax.swing.JTextField quantityTxt;
    private javax.swing.JButton saveButton1;
    // End of variables declaration//GEN-END:variables
}
