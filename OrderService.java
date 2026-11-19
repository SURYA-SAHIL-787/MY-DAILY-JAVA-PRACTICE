package com.enterprise.app.service;

import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class OrderService {

    private final PaymentGateway paymentGateway;

    // Constructor-based Dependency Injection (Enterprise Best Practice)
    public OrderService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String processOrder(double amount) {
        String transactionId = UUID.randomUUID().toString();
        boolean isPaid = paymentGateway.charge(transactionId, amount);
        
        if (!isPaid) {
            throw new IllegalStateException("Payment failed for transaction: " + transactionId);
        }
        return "Order confirmed. Transaction ID: " + transactionId;
    }
}

interface PaymentGateway {
    boolean charge(String txId, double amount);
}
