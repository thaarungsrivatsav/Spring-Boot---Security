package com.SpringBootProject.IMS.service;


import com.SpringBootProject.IMS.entity.OrdersTable;
import com.SpringBootProject.IMS.entity.PaymentTable;
import com.SpringBootProject.IMS.repository.OrderRepository;
import com.SpringBootProject.IMS.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PaymentRepository paymentRepository;
    public PaymentTable completePayment(Long orderId , String paymentType)
    {
        OrdersTable ordersTable = orderRepository.findByOrderId(orderId);
        PaymentTable paymentTable = new PaymentTable();
        if(ordersTable == null)
        {
            return paymentTable;
        }

        Random r = new Random();
        int paymentId = r.nextInt(1000);
        paymentTable.setPaymentId(Long.valueOf(paymentId));
        paymentTable.setPaymentStatus("Paid");
        paymentTable.setOrdersTable(ordersTable);
        paymentTable.setPaymentType(paymentType);
        paymentRepository.save(paymentTable);
        return paymentTable;
    }
}
