package com.SpringBootProject.IMS.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "stock")
public class StockTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id", nullable = false)
    private Long stockId;

    @Column(name = "stock_name" , nullable = false)
    private String stockName;

    @Column(name = "stock_quantity" , nullable = false)
    private int stock_quantity;

    @Column(name = "stock_price_per_unit" , nullable = false)
    private double stock_price_per_unit;

    @Column(name="created_at")
    private String createdAt;

    @Column(name="modified_at" , nullable=false)
    private String modifiedAt;

}
