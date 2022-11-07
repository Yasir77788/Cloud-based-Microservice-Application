package com.fdmgroup.beach_project.department.controller;

import com.fdmgroup.beach_project.department.entity.Department;
import com.fdmgroup.beach_project.department.service.DepartmentService;
import com.fdmgroup.beach_project.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String listDepartments(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "departments";
    }

    @GetMapping("/new")
    public String createDepartmentForm(Model model) {
        // create department object to hold departmentform data
        Department department = new Department();
        model.addAttribute("department", department);
        return "create_department";
    }

    @PostMapping
    public String saveDepartment(@ModelAttribute("department") Department department) {
        departmentService.saveDepartment(department);
        return "redirect:/departments";
    }

    @GetMapping("/edit/{id}")
    public String editDepartmentForm(@PathVariable Long id, Model model) {
        model.addAttribute("department", departmentService.findDepartmentById(id));
        return "edit_department";
    }

    @PostMapping("/{id}")
    public String updateDepartment(@PathVariable Long id,
                                   @ModelAttribute("department") Department department,
                                   Model model) {

        // get department from database by id
        Department existingDepartment = departmentService.findDepartmentById(id);
        // .orElseThrow(() ->new ResourceNotFoundException("No dept by id:" + id));
        existingDepartment.setDepartmentName(department.getDepartmentName());
        existingDepartment.setDepartmentAddress(department.getDepartmentAddress());
        existingDepartment.setDepartmentCode(department.getDepartmentCode());


        // save updated department object
        departmentService.saveDepartment(existingDepartment);
        return "redirect:/departments";
    }

    // handler method to handle delete department request
    @GetMapping("/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartmentById(id);
        return "redirect:/departments";
    }


    @GetMapping("/dept_to_user/{id}")
    public ResponseEntity<Department> findDepartmentById(@PathVariable("id") Long departmentId){
        log.info("Inside findDepartmentById method of DepartmentController");
        Department dept =  departmentService.findDepartmentByIdForUser(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("No dept" +departmentId));
        return ResponseEntity.ok(dept);

    }
}