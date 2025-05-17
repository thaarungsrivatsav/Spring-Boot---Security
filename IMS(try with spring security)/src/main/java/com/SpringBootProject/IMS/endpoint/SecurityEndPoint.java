package com.SpringBootProject.IMS.endpoint;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityEndPoint {

    @GetMapping("/secured/ping/vendor")
    public String securedPingVendor() {
        return "Ping success vendor";
    }

    @GetMapping("/unsecured/ping")
    public String unsecuredPing() {
        return "Ping success";
    }

    @GetMapping("/secured/ping/admin")
    public String securedPingAdmin() {
        return "Ping success admin";
    }

    @GetMapping("/secured/ping/both")
    public String securedPingBoth() {
        return "Ping success both";
    }
}
