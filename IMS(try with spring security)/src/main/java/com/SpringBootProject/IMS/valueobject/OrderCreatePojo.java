package com.SpringBootProject.IMS.valueobject;

import com.SpringBootProject.IMS.entity.StockTable;
import com.SpringBootProject.IMS.entity.UserProfileTable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreatePojo {

    private Long stockId;

    private int stockQuantity;

//    private Long userId; this concept i achieved by taking the username when user enters username in postman basic auth



}
