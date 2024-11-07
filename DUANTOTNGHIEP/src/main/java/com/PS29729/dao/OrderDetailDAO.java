package com.PS29729.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PS29729.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{

	List<OrderDetail> findByOrderId(Long orderId);
	
}