package com.spring.Dao;

import com.spring.model.Role;
import com.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Long> {
}
