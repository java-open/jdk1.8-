package com.example.demo.ConstructorRefer;

/**
 * 测试类的构造器引用
 */
public class Person {
    private  String  name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
