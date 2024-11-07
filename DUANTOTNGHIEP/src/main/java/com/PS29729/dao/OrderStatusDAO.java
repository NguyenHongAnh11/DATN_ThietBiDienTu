package com.PS29729.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PS29729.entity.OrderStatus;

public interface OrderStatusDAO extends JpaRepository<OrderStatus, Long> {
    List<OrderStatus> findByOrderId(long orderId);
}
