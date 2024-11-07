package com.PS29729.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PS29729.dao.CategoryDAO;
import com.PS29729.dao.CouponDAO;
import com.PS29729.entity.Category;
import com.PS29729.entity.Coupon;
import com.PS29729.service.CouponService;

@Service
public class CouponServicelmpl implements CouponService{
	@Autowired
	CouponDAO dao;

	public List<Coupon> findAll() {
		return dao.findAll();
	}

	public Coupon findById(Long id) {
		return dao.findById(id).get();
	}

	public Coupon create(Coupon category) {
		return dao.save(category);
	}

	public Coupon update(Coupon category) {
		return dao.save(category);
	}

	public void delete(Long id) {
		dao.deleteById(id);
	}
	@Override
    public Coupon findBycode(String code) {
        return dao.findByCode(code); 
    }

}
