package com.PS29729.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PS29729.dao.AccountDAO;
import com.PS29729.entity.Account;
import com.PS29729.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountDAO dao;

	public List<Account> findAll() {
		return dao.findAll();
	}

	public Account findById(String username) {
		return dao.findById(username).get();
	}
	public boolean existsByEmail(String email) {
        return dao.existsByEmail(email);
    }

	public List<Account> getAdministrators() {
		return dao.getAdministrators();
	}

	public void save(Account newAccount) {
		dao.save(newAccount);
	}

	public boolean existsById(String username) {
		 return dao.existsById(username);
	}
}
