package com.be.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:8083")
@RestController
public class SampleController {
    @GetMapping("/test1")
    public String func(){
        return "nothing";
    }
}
