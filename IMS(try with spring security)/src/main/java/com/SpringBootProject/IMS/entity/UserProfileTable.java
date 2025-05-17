package com.SpringBootProject.IMS.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "user_profile")
public class UserProfileTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id" )
    private Long userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "role_id" , referencedColumnName =  "role_id" , nullable = false)
    private RoleTable roleTable;





}
