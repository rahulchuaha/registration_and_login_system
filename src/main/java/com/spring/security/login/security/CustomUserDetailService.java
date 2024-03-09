package com.spring.security.login.security;

import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.login.entity.User;
import com.spring.security.login.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

    private UserRepository userRepository;

    
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String findByUserNameOrEmail) throws UsernameNotFoundException {
        
        User user = userRepository.findByEmail(findByUserNameOrEmail);

        if(user != null){
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
               user.getPassword(),
               user.getRoles().stream()
               .map((role) -> new SimpleGrantedAuthority(role.getName()))
               .collect(Collectors.toList()));
        } else{
            throw new UsernameNotFoundException("invalid email or password");
        }

    }
    

    
} 

