package com.PS29729.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PS29729.entity.Carts;

public interface CartDAO extends JpaRepository<Carts, Long> {
    List<Carts> findByAccountUsername(String username);
}
