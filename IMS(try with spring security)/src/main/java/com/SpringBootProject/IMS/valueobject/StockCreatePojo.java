package com.SpringBootProject.IMS.valueobject;

import lombok.Getter;
import lombok.Setter;


//when we send request from postman to create a stock ill save it into this pojo , then it will pass to the
//then it'll be saved into the stock table
@Getter
@Setter
public class StockCreatePojo {
    String stockName;
    int stockQuantity;
    double pricePerUnit;
}
