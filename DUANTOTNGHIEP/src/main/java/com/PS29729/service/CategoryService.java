package com.PS29729.service;

import java.util.List;

import com.PS29729.entity.Category;

public interface CategoryService {
	public List<Category> findAll() ;

	public Category findById(String id) ;

	public Category create(Category category) ;

	public Category update(Category category) ;

	public void delete(String id) ;
}
