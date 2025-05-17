package com.SpringBootProject.IMS.valueobject;


import lombok.Getter;
import lombok.Setter;

//this is the pojo class for the displaying list of orders where i set in this class and put this objects
// into the list of objects present in the DisplayOrdersWithListPaginationConcept
@Getter
@Setter
public class OrdersResponseToStoreOrders {

    private double totalPrice;
    private Long orderId;
    private Long stockId;
    private Long userId;
    private int quantity;
    private String status;

}
