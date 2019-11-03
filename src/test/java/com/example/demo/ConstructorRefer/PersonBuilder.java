package com.example.demo.ConstructorRefer;

import net.bytebuddy.asm.Advice;

/**
 * 定义一个创建person对象的函数式接口
 */
@FunctionalInterface
public interface PersonBuilder {
    //定义一个方法,根据传递的姓名创建person对象返回
    Person builderPerson(String name);
}
