package com.company.ems.repository;

import com.company.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findByNameContaining(String name, Pageable pageable);
    Optional<Employee> findByEmail(String email);
}