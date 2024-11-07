package com.PS29729.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PS29729.dao.RoleDAO;
import com.PS29729.entity.Role;
import com.PS29729.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleDAO dao;

	public List<Role> findAll() {
		return dao.findAll();
	}
}
