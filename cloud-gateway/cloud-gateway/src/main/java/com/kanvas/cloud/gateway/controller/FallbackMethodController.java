package com.kanvas.cloud.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackMethodController {

    @GetMapping("/userServiceFallBack")
    public String userServiceFallbackMethod(){
        return "User service is taking longer than expected." +
                " Please Try again later";
    }

    @GetMapping("/departmentServiceFallBack")
    public String departmentServiceFallbackMethod(){
        return "Departments service is taking longer than expected." +
                " Please Try again later";
    }

}
