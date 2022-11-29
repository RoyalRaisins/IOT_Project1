package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/h")
    public String test(){
        System.out.println("test server requested");
        return "Test successful";
    }
}
