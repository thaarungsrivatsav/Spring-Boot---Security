package com.SpringBootProject.IMS.repository;

import com.SpringBootProject.IMS.entity.PaymentTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentTable , Long> {

}
