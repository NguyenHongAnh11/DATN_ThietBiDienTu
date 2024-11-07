package com.PS29729.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PS29729.entity.Category;
import com.PS29729.entity.PaymentMethod;
import com.PS29729.service.CategoryService;
import com.PS29729.service.PaymentMethodsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/PaymentMethods")
public class PaymentMethodsRestController {
	@Autowired
	PaymentMethodsService PaymentMethodService;
	
	@GetMapping
	public List<PaymentMethod> findAll() {
		return PaymentMethodService.findAll();
	}
}
