package com.PS29729.service;

import com.PS29729.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> findAll();
    OrderDetail findById(Long id);
    OrderDetail create(OrderDetail orderDetail);
    OrderDetail update(Long id, OrderDetail orderDetail);
    boolean delete(Long id);
	List<OrderDetail> findByOrderId(Long orderId);
}
