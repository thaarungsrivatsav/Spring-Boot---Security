package com.SpringBootProject.IMS.endpoint;


import com.SpringBootProject.IMS.entity.OrdersTable;
import com.SpringBootProject.IMS.service.UserService;
import com.SpringBootProject.IMS.service.UserService2;
import com.SpringBootProject.IMS.valueobject.UserCreatePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserEndPoint {

    @Autowired
    UserService userService;
    @Autowired
    UserService2 userService2;

    @PostMapping(value = "/create/user")
    public String createUser(@RequestBody UserCreatePojo userCreatePojo)
    {
        return this.userService.createUser(userCreatePojo);
    }




}
