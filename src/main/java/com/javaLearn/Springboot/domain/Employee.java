package com.javaLearn.Springboot.domain;

import jakarta.persistence.*;

import java.util.Map;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private double salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void applyPatch(Map<String, Object> updates) {
        if (updates.containsKey("name")) {
            this.name = (String) updates.get("name");
        }
        if (updates.containsKey("age")) {
            this.age = (int) updates.get("age");
        }
        if (updates.containsKey("salary")) {
            this.salary = (double) updates.get("salary");
        }
    }
}
