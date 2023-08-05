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

    private EmployeeService employeeService;

    @GetMapping("")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping("/crt")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee newEmployee) {
        return new ResponseEntity<Employee>(employeeService.saveEmployee(newEmployee), HttpStatus.CREATED);
    }
}



