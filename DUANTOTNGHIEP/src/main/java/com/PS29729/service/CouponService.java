package com.PS29729.service;

import java.util.List;

import com.PS29729.entity.Coupon;

public interface CouponService {
	public List<Coupon> findAll() ;

	public Coupon findById(Long id) ;

	public Coupon create(Coupon coupon) ;

	public Coupon update(Coupon coupon) ;

	public void delete(Long id) ;

	Coupon findBycode(String code);

}
