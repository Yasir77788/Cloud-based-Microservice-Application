package com.fdmgroup.beach_project.department.service;

import com.fdmgroup.beach_project.department.entity.Department;
import com.fdmgroup.beach_project.department.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department dept) {
        log.info("Inside saveDepartment of DepartmentService");
        return departmentRepository.save(dept);
    }

    public Department findDepartmentById(Long departmentId) {
        log.info("Inside saveDepartment of DepartmentService");
        return departmentRepository.findByDepartmentId(departmentId);

    }

    public List<Department> getAllDepartments() {

        return departmentRepository.findAll();
    }

    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }

    public Optional<Department> findDepartmentByIdForUser(Long departmentId) {
        log.info("Inside findDeptById2 of DepartmentService");
        return Optional.ofNullable(departmentRepository.findByDepartmentId(departmentId));
    }
}
