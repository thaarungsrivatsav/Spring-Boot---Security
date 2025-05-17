package com.SpringBootProject.IMS.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class PaymentTable {

    @Id
    @Column(name = "payment_id" , nullable = false)
    private Long paymentId;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "payment_type")
    private String paymentType;

    @OneToOne
    @JoinColumn(name = "order_id" , referencedColumnName = "order_id" , nullable = false)
    private OrdersTable ordersTable;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
