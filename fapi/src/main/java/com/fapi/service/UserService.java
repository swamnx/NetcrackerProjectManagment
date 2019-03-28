package com.fapi.service;

import com.fapi.DTO.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service("UserService")
public class UserService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    public User findByEmail(String email) {
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(backendServerUrl + "/api/user/name/" + email, User.class);
        return user;
    }
    public List<User> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        User[] usersResponse = restTemplate.getForObject(backendServerUrl + "/api/users", User[].class);
        return usersResponse == null ? Collections.emptyList() : Arrays.asList(usersResponse);
    }
    public User save(User user) {
        user.setPassword(user.getPassword());
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/users", user, User.class).getBody();
    }
}
