package com.fapi.controller;
import com.fapi.DTO.AuthToken;
import com.fapi.DTO.UserMain.UserAuth;
import com.fapi.DTO.UserMain.UserMainMapper;
import com.fapi.DTO.UserMain.UserWithPassword;
import com.fapi.security.TokenProvider;
import com.fapi.service.UserServiceAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

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
        Authentication authentication=null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userWithPassword.getEmail(),
                            userWithPassword.getPassword()
                    )
            );
        }
        catch (Exception e){
           return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }
    @PostMapping("/signup")
    ResponseEntity registerUser(@RequestBody UserWithPassword userWithPassword){
        String password = userWithPassword.getPassword();
        UserWithPassword userWithPasswordResult = userServiceAuth.save(userWithPassword);
        if(userWithPasswordResult==null) return new ResponseEntity<>(HttpStatus.CONFLICT);

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userWithPassword.getEmail(),
                        password
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @GetMapping("/user")
    ResponseEntity<UserAuth> getUserAuth(Principal user){
        UserAuth userAuth = userServiceAuth.getUserAuthByEmail(user.getName());
        if(userAuth==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userAuth,HttpStatus.OK);
    }

}
