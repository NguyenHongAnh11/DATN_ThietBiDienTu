package com.PS29729.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PS29729.dao.PaymentMethodDAO;
import com.PS29729.entity.PaymentMethod;
import com.PS29729.service.PaymentMethodsService;

@Service

public class PaymentMethodsServicelmpl implements PaymentMethodsService {
	@Autowired
	PaymentMethodDAO dao;

	public List<PaymentMethod> findAll() {
		return dao.findAll();
	}

	public PaymentMethod findById(String id) {
		return dao.findById(id).get();
	}

	public PaymentMethod create(PaymentMethod category) {
		return dao.save(category);
	}

	public PaymentMethod update(PaymentMethod category) {
		return dao.save(category);
	}

	public void delete(String id) {
		dao.deleteById(id);

	}

}
