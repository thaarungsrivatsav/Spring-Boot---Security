package com.SpringBootProject.IMS.valueobject;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreatePojo {
    private String name;
    private String email;
    private String password;
    private Long roleId;
}
