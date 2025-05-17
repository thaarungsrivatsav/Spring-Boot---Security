package com.SpringBootProject.IMS.endpoint;


import com.SpringBootProject.IMS.entity.OrdersTable;
import com.SpringBootProject.IMS.repository.OrderRepository;
import com.SpringBootProject.IMS.service.UserService2;
import com.SpringBootProject.IMS.valueobject.DisplayOrdersWithListPaginationConcept;
import com.SpringBootProject.IMS.valueobject.OrderCreatePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class OrdersEndPoint {

    @Autowired
    UserService2 userService2;

    @PostMapping(value = "create/order")
    public String createOrder(@RequestBody OrderCreatePojo orderCreatePojo)
    {
        return this.userService2.createOrder(orderCreatePojo);
    }

    @PostMapping(value = "display/orders")
    public List<OrdersTable> displayOrder()
    {
        return this.userService2.displayOrders();
    }

//    @PostMapping(value = "display/orders/{userId}")
//    public List<OrdersTable> displayOrdersWithUserId(@PathVariable  Long userId)
//    {
//        return this.userService2.displayOrdersWithUserId(userId);
//    }

    @GetMapping(value = "display/orders/{userId}/{offset}/{pageSize}")
    public DisplayOrdersWithListPaginationConcept displayOrdersWithUserId(@PathVariable  Long userId , @PathVariable int offset , @PathVariable int pageSize)
    {
        return this.userService2.displayOrderOfUserId(userId , offset , pageSize);
    }
}
