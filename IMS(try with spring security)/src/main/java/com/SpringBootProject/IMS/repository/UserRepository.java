package com.SpringBootProject.IMS.repository;

import com.SpringBootProject.IMS.entity.UserProfileTable;
import org.springframework.data.jpa.repository.JpaRepository;


//this repo handles with all the user_profile table related query methods.
public interface UserRepository extends JpaRepository<UserProfileTable , Long> {

 public UserProfileTable findByUserName(String user);
 public UserProfileTable findByUserEmail(String email);
 public UserProfileTable findByUserId(Long id);
}
