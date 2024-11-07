package com.PS29729.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PS29729.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, String>{
}
