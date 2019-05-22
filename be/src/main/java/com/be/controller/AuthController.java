package com.be.controller;

import com.be.DTO.UserMain.UserMainMapper;
import com.be.DTO.UserMain.UserWithPassword;
import com.be.entity.User;
import com.be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.executable.ValidateOnExecution;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/userWithPassword")
    ResponseEntity<com.be.DTO.UserMain.UserWithPassword> findByEmail(String email){
        User user = userRepository.findUserByEmail(email);
        if(user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return  new ResponseEntity<>(UserMainMapper.INSTANCE.userToUserWithPasswordDTO(user), HttpStatus.OK);
    }
    @GetMapping("")
    List<com.be.DTO.UserMain.UserWithPassword> findAll(){
        List<User> users = userRepository.findAll();
        return UserMainMapper.INSTANCE.usersToUserWithPasswordDTOs(users);
    }
    @PostMapping("")
    ResponseEntity<com.be.DTO.UserMain.UserWithPassword> createUser(@Valid @RequestBody User user){
        User userFound = userRepository.findUserByEmail(user.getEmail());
        if(userFound!=null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(UserMainMapper.INSTANCE.userToUserWithPasswordDTO(userRepository.save(user)),HttpStatus.OK);
    }
    @GetMapping("/userAuth")
    ResponseEntity<com.be.DTO.UserMain.UserAuth> getUserAuthByEmail(@RequestParam String email){
        User userFound = userRepository.findUserByEmail(email);
        if(userFound==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(UserMainMapper.INSTANCE.userToUserAuthDTO(userFound),HttpStatus.OK);
    }

}
