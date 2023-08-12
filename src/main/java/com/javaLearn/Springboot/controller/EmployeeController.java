package com.javaLearn.Springboot.controller;

import com.javaLearn.Springboot.domain.Employee;
import com.javaLearn.Springboot.repositiories.EmployeeRepository;
import com.javaLearn.Springboot.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/show")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping("/cre")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee newEmployee) {
        return new ResponseEntity<Employee>(employeeService.saveEmployee(newEmployee), HttpStatus.CREATED);
    }

    @PutMapping("/upd/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        if (!employeeRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updatedEmployee.setId(id);

        Employee savedEmployee = employeeService.saveEmployee(updatedEmployee);

        return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (!employeeRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        employeeService.deleteEmployeeById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/upd/few/{id}")
    public ResponseEntity<Employee> patchEmployee(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Employee existingEmployee = employeeRepository.findById(id).orElse(null);

        if (existingEmployee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        existingEmployee.applyPatch(updates);

        Employee updatedEmployee = employeeService.saveEmployee(existingEmployee);

        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }
}



