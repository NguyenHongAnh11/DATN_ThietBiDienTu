package com.PS29729.service;

import java.util.List;

import com.PS29729.entity.Account;

public interface AccountService {
	public List<Account> findAll() ;
	public Account findById(String username) ;
	public List<Account> getAdministrators() ;
    boolean existsById(String username);
    boolean existsByEmail(String email);
	public void save(Account newAccount);

}
