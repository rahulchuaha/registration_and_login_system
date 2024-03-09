package com.spring.security.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.security.login.entity.Role;



public interface RoleRepository extends JpaRepository<Role, Long>{
    
    Role findByName(String name);
}
