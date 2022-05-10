package com.kanvas.user.controller;

import com.kanvas.user.entity.User;
import com.kanvas.user.service.UserService;
import com.kanvas.user.valueObjects.ResponseTemplateVO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private static final String SERVICE_A = "serviceA";

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user){
        log.info("Inside saveUser of UserController");
        return userService.saveUser(user);
    }

    int count = 1;
    @GetMapping("/{id}")
    // @CircuitBreaker(name = SERVICE_A, fallbackMethod = "serviceFallback")
    // @Retry(name = SERVICE_A)
    @RateLimiter(name = SERVICE_A)
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId){
        log.info("Inside getUserWithDepartment of UserController");
        log.info("Number of retries " + count++ + new Date());
        return userService.getUserWithDepartment(userId);
    }

    public ResponseTemplateVO serviceFallback(Exception e){
        return new ResponseTemplateVO();
    }

}
