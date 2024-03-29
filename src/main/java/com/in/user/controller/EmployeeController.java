package com.in.user.controller;

import com.in.user.domain.Employee;
import com.in.user.exception.ResourceNotFoundException;
import com.in.user.repository.EmployeeRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/api/v1")
    @Api(value = "Employee Management System", description = "Operations pertaining to employee in Employee Management System")
    public class EmployeeController {
        @Autowired
        private EmployeeRepository employeeRepository;

        @GetMapping("/employees")
        @ApiOperation(value = "Get all employee details")
        public List<Employee> getAllEmployees() {
            return employeeRepository.findAll();
        }

        @GetMapping("/employees/{id}")
        public ResponseEntity<Employee> getEmployeeById(@ApiParam(value = "Employee Id from which employee details you want", required = true) @PathVariable(value = "id") Long employeeId)
                throws ResourceNotFoundException {
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
            return ResponseEntity.ok().body(employee);
        }

        @PostMapping("/employees")
        public Employee createEmployee(@Valid @RequestBody Employee employee) {
            return employeeRepository.save(employee);
        }

        @PutMapping("/employees/{id}")
        public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                       @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
            employee.setEmailId(employeeDetails.getEmailId());
            employee.setLastName(employeeDetails.getLastName());
            employee.setFirstName(employeeDetails.getFirstName());
            final Employee updatedEmployee = employeeRepository.save(employee);
            return ResponseEntity.ok(updatedEmployee);
        }

        @DeleteMapping("/employees/{id}")
        public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
                throws ResourceNotFoundException {
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
            employeeRepository.delete(employee);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return response;
        }

}
