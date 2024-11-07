package com.PS29729.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PS29729.entity.OrderStatusTypes;

public interface OrderStatusTypeDAO extends JpaRepository<OrderStatusTypes, Integer> {
}
