package com.SpringBootProject.IMS.valueobject;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
//while updating we will send request from json , so that will need to stored in one pojo and
// this is that pojo... so this will update into the stockTable entity ...
public class TemporaryStock {
    Long stockId;
    String stockName;
    int stockQuantity;
    double pricePerUnit;
}
