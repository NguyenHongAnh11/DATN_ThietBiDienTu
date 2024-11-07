package com.PS29729.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.PS29729.dao.OrderDAO;
import com.PS29729.dao.OrderDetailDAO;
import com.PS29729.entity.Order;
import com.PS29729.entity.OrderDetail;
import com.PS29729.entity.Product;
import com.PS29729.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderDAO dao;
	
	@Autowired
	OrderDetailDAO ddao;
	public List<Order> findAll() {
		return dao.findAll();
	}
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		Order order = mapper.convertValue(orderData, Order.class);
		order.setPaymentStatusId(1);
		dao.save(order);
		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
				.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
		ddao.saveAll(details);
		return order;
	}
	
	public Order findById(Long id) {
		return dao.findById(id).get();
	}
	 public List<Object[]> getOrderWithDetails(Long orderId) {
	        return dao.findOrderWithDetails(orderId);
	 }
	 public Order findByIdWithDetails(Long orderId) {
	        Optional<Order> result = dao.findById(orderId);
	        return result.orElse(null);
	    }
	public List<Order> findByUsername(String username) {
		return dao.findByUsername(username);
	}
}
