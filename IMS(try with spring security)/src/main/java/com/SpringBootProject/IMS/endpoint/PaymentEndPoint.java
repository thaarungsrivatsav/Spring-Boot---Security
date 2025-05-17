package com.SpringBootProject.IMS.endpoint;

import com.SpringBootProject.IMS.entity.PaymentTable;
import com.SpringBootProject.IMS.service.PaymentService;
import com.SpringBootProject.IMS.valueobject.PaymentPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class PaymentEndPoint {

    @Autowired
    PaymentService paymentService;

    @PostMapping(value = "/payment/")
    public PaymentTable makePayment(@RequestBody PaymentPojo paymentPojo)
    {
        return this.paymentService.completePayment(paymentPojo.getOrderId(), paymentPojo.getPaymentType());
    }
}
