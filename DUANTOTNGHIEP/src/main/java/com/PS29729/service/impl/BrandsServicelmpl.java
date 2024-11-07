package com.PS29729.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PS29729.dao.BrandDAO;
import com.PS29729.entity.Brands;
import com.PS29729.entity.Category;
import com.PS29729.service.BrandsService;

@Service
public class BrandsServicelmpl implements BrandsService {
	@Autowired
	BrandDAO dao;

	public List<Brands> findAll() {
		return dao.findAll();
	}
	public Brands findById(String id) {
		return dao.findById(id).get();
	}

	public Brands create(Brands brands) {
		return dao.save(brands);
	}

	public Brands update(Brands brands) {
		return dao.save(brands);
	}

	public void delete(String id) {
		dao.deleteById(id);
	}

}
