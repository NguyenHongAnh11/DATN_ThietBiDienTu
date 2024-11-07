package com.PS29729.service;

import java.util.List;

import com.PS29729.entity.Brands;

public interface BrandsService {
	public List<Brands> findAll();
	public Brands findById(String id) ;

	public Brands create(Brands brands) ;

	public Brands update(Brands brands) ;

	public void delete(String id);

}
