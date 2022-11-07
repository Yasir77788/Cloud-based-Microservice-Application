package com.fdmgroup.beach_project.user_service.service;

import com.fdmgroup.beach_project.user_service.entity.User;
import com.fdmgroup.beach_project.user_service.repository.UserRepository;
import com.fdmgroup.beach_project.user_service.valueObject.Department;
import com.fdmgroup.beach_project.user_service.valueObject.ResponseTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

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

    public Optional<ResponseTemplateVO> getUserWithDepartment(Long userId) {
        log.info("Inside saveUser of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);
        Department department =
                restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/dept_to_user/"
                                + user.getDepartmentId(), Department.class);
        vo.setUser(user);
        vo.setDepartment(department);
        return Optional.of(vo); // responseTemplateVO object
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findByUserId(id);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
