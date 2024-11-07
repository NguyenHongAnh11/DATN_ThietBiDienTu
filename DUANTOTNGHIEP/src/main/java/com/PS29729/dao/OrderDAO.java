package com.PS29729.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PS29729.entity.Order;

public interface OrderDAO extends JpaRepository<Order, Long>{
	@Query("SELECT o FROM Order o WHERE o.account.username=?1")
	List<Order> findByUsername(String username);

	@Query("SELECT o, od FROM Order o JOIN o.orderDetails od WHERE o.id = :orderId")
    List<Object[]> findOrderWithDetails(@Param("orderId") Long orderId);
    
}
