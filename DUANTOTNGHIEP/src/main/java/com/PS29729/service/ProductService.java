package com.PS29729.service;

import java.util.List;

import com.PS29729.entity.Product;

public interface ProductService {
	public List<Product> findAll();

	public Product findById(Integer id);

	public List<Product> findByCategoryId(String cid);

	public Product create(Product product);

	public Product update(Product product);

	public void delete(Integer id);

	List<Product> findByCategoryIdAndPriceRange(String categoryId, double minPrice, double maxPrice);

	List<Product> findByPriceRange(double minPrice, double maxPrice);

	public List<Product> findByBrandsId(String id);

}
