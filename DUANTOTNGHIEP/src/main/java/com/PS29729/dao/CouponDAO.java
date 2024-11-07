package com.PS29729.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.PS29729.entity.Coupon;

public interface CouponDAO extends JpaRepository<Coupon, Long>{
	Coupon findByCode(String code);
}
