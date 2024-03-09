package com.spring.security.login.service;

import java.util.List;

import com.spring.security.login.dto.UserDto;
import com.spring.security.login.entity.User;

public interface UserService {
    
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
