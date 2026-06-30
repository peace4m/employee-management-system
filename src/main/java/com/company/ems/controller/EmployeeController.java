package com.company.ems.controller;

import com.company.ems.dto.ApiResponse;
import com.company.ems.dto.EmployeeDto;
import com.company.ems.service.EmployeeService; // Make sure this exists
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse> createEmployee(@RequestBody EmployeeDto dto) {
        employeeService.createEmployee(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Employee created successfully"));
    }

    // READ
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    // UPDATE
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto dto) {
        employeeService.updateEmployee(id, dto);
        return ResponseEntity.ok(new ApiResponse(true, "Employee updated successfully"));
    }

    // DELETE
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok(new ApiResponse(true, "Employee deleted successfully"));
    }
}