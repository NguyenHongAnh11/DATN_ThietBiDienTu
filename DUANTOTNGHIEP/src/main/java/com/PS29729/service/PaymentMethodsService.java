package com.PS29729.service;

import java.util.List;

import com.PS29729.entity.PaymentMethod;

public interface PaymentMethodsService {
	public List<PaymentMethod> findAll() ;

	public PaymentMethod findById(String id) ;

	public PaymentMethod create(PaymentMethod paymentmethod) ;

	public PaymentMethod update(PaymentMethod paymentmethod) ;

	public void delete(String id) ;
}
