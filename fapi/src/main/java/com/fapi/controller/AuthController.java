package com.fapi.controller;
import com.fapi.DTO.AuthToken;
import com.fapi.DTO.UserMain.UserAuth;
import com.fapi.DTO.UserMain.UserWithPassword;
import com.fapi.security.TokenProvider;
import com.fapi.service.UserServiceAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users/auth")
public class AuthController {

    @Autowired
    private UserServiceAuth userServiceAuth;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/token")
    ResponseEntity getToken(@RequestBody UserWithPassword userWithPassword){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userWithPassword.getEmail(), userWithPassword.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = tokenProvider.generateToken(authentication);
            return ResponseEntity.ok(new AuthToken(token));
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
    @PostMapping("/signup")
    ResponseEntity registerUser(@RequestBody UserWithPassword userWithPassword){
        String password = userWithPassword.getPassword();
        try {
            UserWithPassword userWithPasswordResult = userServiceAuth.save(userWithPassword);

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userWithPassword.getEmail(), password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = tokenProvider.generateToken(authentication);
            return  new ResponseEntity(new AuthToken(token),HttpStatus.OK);
        }
        catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @GetMapping("/user")
    ResponseEntity<UserAuth> getUserAuth(Principal user){
        try {
            UserAuth userAuth = userServiceAuth.getUserAuthByEmail(user.getName());
            return new ResponseEntity<>(userAuth,HttpStatus.OK);
        }
        catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

}
