package com.SpringBootProject.IMS.repository;

import com.SpringBootProject.IMS.entity.OrdersTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrdersTable , Long> {

    public OrdersTable findByOrderId(Long orderId);

    Page<OrdersTable> findByUserProfileTableUserId(Long userId, PageRequest of);
}
