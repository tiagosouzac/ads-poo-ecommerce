package br.edu.iftm.ecommerce.factories;

import br.edu.iftm.ecommerce.enums.PaymentType;
import br.edu.iftm.ecommerce.strategies.payment.*;

public class PaymentMethodFactory {
    public PaymentMethod createPaymentMethod(PaymentType paymentMethod) {
        return switch (paymentMethod) {
            case DEBIT_CARD -> new DebitCardPaymentMethod();
            case CREDIT_CARD -> new CreditCardPaymentMethod();
            case BOLETO -> new BoletoPaymentMethod();
            case PIX -> new PixPaymentMethod();
            case null, default -> throw new IllegalArgumentException("Invalid payment method");
        };
    }
}
