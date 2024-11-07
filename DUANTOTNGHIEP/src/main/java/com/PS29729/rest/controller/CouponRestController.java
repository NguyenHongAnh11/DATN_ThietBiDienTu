package com.PS29729.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PS29729.entity.Coupon;
import com.PS29729.service.CouponService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/coupon")
public class CouponRestController {
	@Autowired
	CouponService couponservice;

	@GetMapping
	public List<Coupon> findAll() {
		return couponservice.findAll();
	}

	@GetMapping("/check/{code}")
	public ResponseEntity<Map<String, Object>> checkIfCouponExists(@PathVariable String code) {
		Coupon coupon = couponservice.findBycode(code);
		Map<String, Object> response = new HashMap<>();

		if (coupon != null) { 
			double discount = coupon.getDiscount(); 
			response.put("valid", true);
			response.put("discount", discount);
		} else {
			response.put("valid", false);
			response.put("message", "Coupon code is invalid");
		}

		return ResponseEntity.ok(response);
	}

}
