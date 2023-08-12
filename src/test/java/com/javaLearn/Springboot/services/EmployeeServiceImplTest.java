package com.javaLearn.Springboot.services;

import com.javaLearn.Springboot.domain.Employee;
import com.javaLearn.Springboot.repositiories.EmployeeRepository;
import com.javaLearn.Springboot.services.EmployeeService;
import com.javaLearn.Springboot.services.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveEmployee() {
        Employee emp = new Employee();
        emp.setName("John");
        emp.setAge(30);
        emp.setSalary(50000.0);

        when(employeeRepository.save(any(Employee.class))).thenReturn(emp);

        Employee savedEmp = employeeService.saveEmployee(emp);

        assertNotNull(savedEmp);
        assertEquals(emp.getName(), savedEmp.getName());

        verify(employeeRepository).save(emp);
    }

    @Test
    public void testDeleteEmployeeById() {
        Long empId = 1L;

        doNothing().when(employeeRepository).deleteById(empId);

        employeeService.deleteEmployeeById(empId);

        verify(employeeRepository).deleteById(empId);
    }

}

