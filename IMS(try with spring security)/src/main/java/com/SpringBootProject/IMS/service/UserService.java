package com.SpringBootProject.IMS.service;

import com.SpringBootProject.IMS.entity.RoleTable;
import com.SpringBootProject.IMS.entity.UserProfileTable;
import com.SpringBootProject.IMS.repository.UserRepository;
import com.SpringBootProject.IMS.valueobject.UserCreatePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String createUser(UserCreatePojo userCreatePojo)
    {
        UserProfileTable userProfileTable = userRepository.findByUserEmail(userCreatePojo.getEmail());
        if(userProfileTable != null)
        {
            return "User Already Exists";
        }

        RoleTable roleTable = new RoleTable();
        roleTable.setRoleId(userCreatePojo.getRoleId());
        String rolename = "";
        if(userCreatePojo.getRoleId()==1)
        {
            rolename="vendor";
        }
        else if(userCreatePojo.getRoleId()==2){
            rolename="admin";
        }
        roleTable.setRoleName(rolename);
        userProfileTable = new UserProfileTable();
        userProfileTable.setUserEmail(userCreatePojo.getEmail());
        userProfileTable.setUserName(userCreatePojo.getName());
        userProfileTable.setUserPassword(this.passwordEncoder.encode(userCreatePojo.getPassword()));
        userProfileTable.setRoleTable(roleTable);
        userProfileTable.setCreatedAt(LocalDateTime.now());
        userRepository.save(userProfileTable);
        return "User Created";
    }
}
