package com.spring.Dao;

import com.spring.model.Authorities;
import com.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthDao extends JpaRepository<Authorities,Long> {
}
