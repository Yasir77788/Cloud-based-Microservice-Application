package com.fdmgroup.beach_project.cloudgateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @GetMapping("/userServiceFallBack")
    public String userServiceFallBackMethod(){
        return "User Services is taking longer than expected." +
                " Please, try again later.";
    }


    @GetMapping("/departmentServiceFallBack")
    public String departmentServiceFallBackMethod(){
        return "Department Services is taking longer than expected." +
                " Please, try again later.";
    }
}
