package com.PS29729.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PS29729.entity.PaymentMethod;

public interface PaymentMethodDAO  extends JpaRepository<PaymentMethod, String>{

}
