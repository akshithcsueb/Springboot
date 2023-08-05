package com.javaLearn.Springboot.repositiories;

import com.javaLearn.Springboot.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}

