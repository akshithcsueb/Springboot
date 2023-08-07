package com.javaLearn.Springboot.controller;

import com.javaLearn.Springboot.domain.Employee;
import com.javaLearn.Springboot.repositiories.EmployeeRepository;
import com.javaLearn.Springboot.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping("/cre")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee newEmployee) {
        return new ResponseEntity<Employee>(employeeService.saveEmployee(newEmployee), HttpStatus.CREATED);
    }

    @PutMapping("/upd/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        // Check if the employee with the given id exists in the database
        if (!employeeRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Set the id of the updated employee object
        updatedEmployee.setId(id);

        // Save the updated employee
        Employee savedEmployee = employeeService.saveEmployee(updatedEmployee);

        return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        // Check if the employee with the given id exists in the database
        if (!employeeRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Delete the employee
        employeeService.deleteEmployeeById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}



