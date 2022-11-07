package com.fdmgroup.beach_project.department.repository;

import com.fdmgroup.beach_project.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository <Department, Long> {
    Department findByDepartmentId(Long departmentId);
}
