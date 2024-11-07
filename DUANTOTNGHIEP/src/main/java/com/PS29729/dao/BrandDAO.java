package com.PS29729.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PS29729.entity.Brands;

public interface BrandDAO extends JpaRepository<Brands, String> {
}
