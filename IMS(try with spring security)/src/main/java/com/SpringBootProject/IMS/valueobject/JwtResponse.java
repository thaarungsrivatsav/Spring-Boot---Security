package com.SpringBootProject.IMS.valueobject;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {

    private String token;

    private Long roleId;
    public JwtResponse(String jwttoken , Long roleId) {
        this.token = jwttoken;
        this.roleId = roleId;
    }
}