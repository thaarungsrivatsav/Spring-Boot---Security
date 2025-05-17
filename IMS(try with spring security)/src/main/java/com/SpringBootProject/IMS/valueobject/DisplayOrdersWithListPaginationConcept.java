package com.SpringBootProject.IMS.valueobject;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DisplayOrdersWithListPaginationConcept {

    private Integer pageSize;
    private Integer offset;
    private Long totalElements;
    private Integer totalPages;
    private List<OrdersResponseToStoreOrders> orderResponseList;

}
