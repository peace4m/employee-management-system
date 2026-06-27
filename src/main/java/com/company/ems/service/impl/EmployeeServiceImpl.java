package com.company.ems.service.impl;

import com.company.ems.dto.EmployeeDto;
import com.company.ems.entity.Department;
import com.company.ems.entity.Designation;
import com.company.ems.entity.Employee;
import com.company.ems.repository.DepartmentRepository;
import com.company.ems.repository.DesignationRepository;
import com.company.ems.repository.EmployeeRepository;
import com.company.ems.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final DesignationRepository designationRepository;

    // Constructor injection for all repositories
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               DepartmentRepository departmentRepository,
                               DesignationRepository designationRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.designationRepository = designationRepository;
    }

    @Override
    @Transactional
    public EmployeeDto createEmployee(EmployeeDto dto) {
        // 1. Fetch related entities
        Department dept = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + dto.getDepartmentId()));

        Designation desig = designationRepository.findById(dto.getDesignationId())
                .orElseThrow(() -> new RuntimeException("Designation not found with ID: " + dto.getDesignationId()));

        Employee manager = null;
        if (dto.getManagerId() != null) {
            manager = employeeRepository.findById(dto.getManagerId()).orElse(null);
        }

        // 2. Map DTO to Entity
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setRole(dto.getRole());
        employee.setDepartment(dept);
        employee.setDesignation(desig);
        employee.setManager(manager);

        // 3. Save and return mapped DTO
        return mapToDto(employeeRepository.save(employee));
    }

    private EmployeeDto mapToDto(Employee emp) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(emp.getId());
        dto.setName(emp.getName());
        dto.setEmail(emp.getEmail());
        dto.setRole(emp.getRole());
        dto.setDepartmentId(emp.getDepartment() != null ? emp.getDepartment().getId() : null);
        dto.setDesignationId(emp.getDesignation() != null ? emp.getDesignation().getId() : null);
        dto.setManagerId(emp.getManager() != null ? emp.getManager().getId() : null);
        return dto;
    }

    // Placeholder methods for other operations
    @Override public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) { return null; }
    @Override public void deleteEmployee(Long id) { }
    @Override public EmployeeDto getEmployeeById(Long id) { return null; }
    @Override public Page<EmployeeDto> findAllEmployees(String name, Pageable pageable) { return null; }
}