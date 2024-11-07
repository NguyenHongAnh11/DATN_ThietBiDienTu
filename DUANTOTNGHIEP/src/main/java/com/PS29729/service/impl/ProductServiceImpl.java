package com.PS29729.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PS29729.dao.ProductDAO;
import com.PS29729.entity.Product;
import com.PS29729.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDAO dao;

	public List<Product> findAll() {
		return dao.findAll();
	}

	public Product findById(Integer id) {
		return dao.findById(id).get();
	}

	public List<Product> findByCategoryId(String cid) {
		return dao.findByCategoryId(cid);
	}
	public List<Product> findByBrandsId(String cid) {
		return dao.findByBrandsId(cid);
	}

	public Product create(Product product) {
		return dao.save(product);
	}

	public Product update(Product product) {
		return dao.save(product);
	}

	public void delete(Integer id) {
		dao.deleteById(id);
	}

	public List<Product> findByCategoryIdAndPriceRange(String categoryId, double minPrice, double maxPrice) {
		return dao.findByCategoryIdAndPriceBetween(categoryId, minPrice, maxPrice);
	}

	public List<Product> findByPriceRange(double minPrice, double maxPrice) {
		return dao.findByPriceBetween(minPrice, maxPrice);
	}
	
}
