package com.fapi.service.impl;

import com.fapi.DTO.UserMain.UserAuth;
import com.fapi.DTO.UserMain.UserWithPassword;
import com.fapi.service.UserServiceAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service("customUserDetailsService")
public class UserServiceAuthImpl implements UserDetailsService, UserServiceAuth {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserWithPassword findByEmail(String email) {
        RestTemplate restTemplate = new RestTemplate();
        UserWithPassword user = restTemplate.getForObject(backendServerUrl + "/api/users/auth/email/" + email, UserWithPassword.class);
        return user;
    }
    @Override
    public UserAuth getUserAuthByEmail(String email) {
        RestTemplate restTemplate = new RestTemplate();
        UserAuth user = restTemplate.getForObject(backendServerUrl + "/api/users/auth/user/" + email, UserAuth.class);
        return user;
    }

    @Override
    public List<UserWithPassword> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        UserWithPassword[] usersResponse = restTemplate.getForObject(backendServerUrl + "/api/users/auth", UserWithPassword[].class);
        return usersResponse == null ? Collections.emptyList() : Arrays.asList(usersResponse);
    }

    @Override
    public UserWithPassword save(UserWithPassword user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/users/auth", user, UserWithPassword.class).getBody();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserWithPassword user = findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid email or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserWithPassword user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return authorities;
    }

}
