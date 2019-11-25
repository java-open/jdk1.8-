package com.example.demo.entity;

import lombok.Data;

/**
 * 员工实体类
 */
@Data
public class Employee {
    private  String name;
    private  String depart;

    public Employee() {
    }

    public Employee(String name, String depart) {
        this.name = name;
        this.depart = depart;
    }
}
