package com.PS29729.service;

import java.util.List;

import com.PS29729.entity.Authority;

public interface AuthorityService {
	public List<Authority> findAll() ;

	public Authority create(Authority auth) ;

	public void delete(Integer id) ;

	public List<Authority> findAuthoritiesOfAdministrators() ;
}
