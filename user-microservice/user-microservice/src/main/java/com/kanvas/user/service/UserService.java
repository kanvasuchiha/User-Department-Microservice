package com.kanvas.user.service;

import com.kanvas.user.entity.User;
import com.kanvas.user.repository.UserRepository;
import com.kanvas.user.valueObjects.Department;
import com.kanvas.user.valueObjects.ResponseTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {

        log.info("Inside getUserWithDepartment of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

        //If you don't add http here, it throws error
        // Department department = restTemplate.getForObject("http://localhost:9001/departments/" + user.getDepartmentId(), Department.class);

        //Since this url can change, but altogether we know that we will always connect with the DEPARTMENT-SERVICE,
        //we can give the application name instead, as both of these are connected to service registry
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(), Department.class);

        vo.setUser(user);
        vo.setDepartment(department);
        return vo;

    }
}
