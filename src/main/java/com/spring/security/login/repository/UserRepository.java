package com.spring.security.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.security.login.entity.User;



public interface UserRepository extends JpaRepository<User, Long>{
    
    User findByEmail(String email);
}
