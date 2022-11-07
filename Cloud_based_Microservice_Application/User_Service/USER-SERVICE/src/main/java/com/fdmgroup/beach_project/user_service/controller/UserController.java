package com.fdmgroup.beach_project.user_service.controller;

import com.fdmgroup.beach_project.user_service.entity.User;
import com.fdmgroup.beach_project.user_service.exception.ResourceNotFoundException;
import com.fdmgroup.beach_project.user_service.service.UserService;
import com.fdmgroup.beach_project.user_service.valueObject.ResponseTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/new")
    public String createUserForm(Model model) {
        // create User object to hold userform data
        User user = new User();
        model.addAttribute("user", user);
        return "create_user";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "edit_user";
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable Long id,
                                   @ModelAttribute("user") User user,
                                   Model model) {

        // get User from database by id
        User existingUser = userService.findUserById(id);
        // .orElseThrow(() ->new ResourceNotFoundException("No dept by id:" + id));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setDepartmentId(user.getDepartmentId());

        // save updated department object
        userService.saveUser(existingUser);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }

    //@RequestMapping(path = "/{id}", produces = "application/json; charset=UTF-8")
    @GetMapping("/user_with_dept/{id}")
    @ResponseBody
    public ResponseEntity<ResponseTemplateVO> getUserWithDepartment(@PathVariable("id") Long userId){

        log.info("Inside saveUser of UserController");

        ResponseTemplateVO user = userService.getUserWithDepartment(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return ResponseEntity.ok(user);
    }

}
