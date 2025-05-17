package com.SpringBootProject.IMS.valueobject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentPojo {
    private Long orderId;
    private String paymentType;
}
