package com.be.controller;

import com.be.DTO.UserMain.UserMainMapper;
import com.be.DTO.UserMain.UserWithPassword;
import com.be.entity.User;
import com.be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/email/{email}")
    com.be.DTO.UserMain.UserWithPassword findByEmail(@PathVariable String email){
        User user = userRepository.findUserByEmail(email);
        return UserMainMapper.INSTANCE.userToUserWithPasswordDTO(user);
    }
    @GetMapping("")
    List<com.be.DTO.UserMain.UserWithPassword> findAll(){
        List<User> users = userRepository.findAll();
        return UserMainMapper.INSTANCE.usersToUserWithPasswordDTOs(users);
    }
    @PostMapping("")
    com.be.DTO.UserMain.UserWithPassword createUser(@RequestBody User user){
        User userFound = userRepository.findUserByEmail(user.getEmail());
        if(userFound!=null) return null;
        return UserMainMapper.INSTANCE.userToUserWithPasswordDTO(userRepository.save(user));
    }
    @GetMapping("/user/{email}")
    com.be.DTO.UserMain.UserAuth getUserAuthByEmail(@PathVariable String email){
        User user = userRepository.findUserByEmail(email);
        return UserMainMapper.INSTANCE.userToUserAuthDTO(user);
    }

}
