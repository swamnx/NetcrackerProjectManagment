package com.fapi.controller;

import com.fapi.DTO.CommentMain.Comment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @PostMapping()
    public ResponseEntity<com.fapi.DTO.TaskMain.Comment> createComment(@RequestBody Comment commentDTO){
        RestTemplate restTemplate = new RestTemplate();
        try {
           return restTemplate.postForEntity(backendServerUrl + "/api/comments",commentDTO,com.fapi.DTO.TaskMain.Comment.class);
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
}
