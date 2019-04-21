package com.fapi.service;

import com.fapi.DTO.UserMain.UserAuth;
import com.fapi.DTO.UserMain.UserWithPassword;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserServiceAuth {
    UserAuth getUserAuthByEmail(String email);
    UserWithPassword findByEmail(String email);
    List<UserWithPassword> findAll();
    UserWithPassword save(UserWithPassword user);
}
