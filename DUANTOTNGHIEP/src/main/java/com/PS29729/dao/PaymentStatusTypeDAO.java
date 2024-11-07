package com.PS29729.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PS29729.entity.PaymentStatusTypes;

public interface PaymentStatusTypeDAO extends JpaRepository<PaymentStatusTypes, Integer> {
}
