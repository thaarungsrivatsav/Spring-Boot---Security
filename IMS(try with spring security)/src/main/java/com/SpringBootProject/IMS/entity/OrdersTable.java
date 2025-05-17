package com.SpringBootProject.IMS.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrdersTable {
    @Id
    @Column(name = "order_id",nullable = false)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name="stock_id", referencedColumnName = "stock_id" , nullable = false)
    private StockTable stockTable;

    @Column(name = "order_quantity",nullable = false)
    private int stockQuantity;

    @Column(name = "total_price",nullable = false)
    private double totalPrice;

    @OneToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "user_id" , nullable = false)
    private UserProfileTable userProfileTable;

    @Column(name = "order_status",nullable = false)
    private String orderStatus;

    @Column(name = "order_at")
    private LocalDateTime orderedAt;

}

