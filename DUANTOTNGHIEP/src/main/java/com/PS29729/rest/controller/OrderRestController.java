package com.PS29729.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;


import com.PS29729.entity.Order;
import com.PS29729.service.OrderService;

@CrossOrigin("*")
@RestController
public class OrderRestController {
	@Autowired
	OrderService orderService;
	

	@GetMapping("/rest/orders")
	public ResponseEntity<List<Order>> getAllOrders() {
		try {
			List<Order> orders = orderService.findAll();
			return ResponseEntity.ok(orders);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	


	@GetMapping("/rest/infororders/{orderId}")
	public ResponseEntity<Order> getOrderById(@PathVariable("orderId") Long orderId) {
		try {
			Order order = orderService.findByIdWithDetails(orderId);
			if (order != null) {
				return ResponseEntity.ok(order);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
