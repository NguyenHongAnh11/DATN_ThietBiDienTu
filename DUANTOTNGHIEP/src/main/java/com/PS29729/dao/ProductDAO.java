package com.PS29729.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.PS29729.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {

	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	List<Product> findByCategoryId(String cid);

	List<Product> findByCategoryIdAndPriceBetween(String categoryId, double minPrice, double maxPrice);
	List<Product> findByPriceBetween(double minPrice, double maxPrice);
	@Query("SELECT p FROM Product p WHERE p.brands.id=?1")
	List<Product> findByBrandsId(String cid);
	
}
