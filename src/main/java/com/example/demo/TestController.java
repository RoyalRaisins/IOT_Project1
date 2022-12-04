package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.json.JSONObject;
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/h")
    public String test(){
        System.out.println("test server requested");
        return "Test successful";
    }
    @PostMapping("/h")
    public String rec(@RequestBody String json){
        JSONObject msg = new JSONObject(json);
        JSONObject body = msg.getJSONObject("notify_data").getJSONObject("body");
        System.out.println("post request received, \nbody: \n" + json);
        System.out.println("Body: \n"+body);
        return "Post successful";
    }
}
