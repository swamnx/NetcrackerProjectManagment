package com.fapi.controller;

import com.fapi.DTO.User;
import com.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserControlller {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/login/{email}")
    public User getUserByLogin(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST, produces = "application/json")
    public User saveUser(@RequestBody User user){
        return userService.save(user);
    }
}
