package com.PS29729.service;

import java.util.List;


import com.fasterxml.jackson.databind.JsonNode;

import com.PS29729.entity.Order;

public interface OrderService {
	public List<Order> findAll();

	public Order create(JsonNode orderData) ;
	
	public Order findById(Long id) ;
	
	public List<Order> findByUsername(String username) ;

	public Order findByIdWithDetails(Long orderId);

}
