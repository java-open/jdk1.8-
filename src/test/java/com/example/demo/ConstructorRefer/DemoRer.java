package com.example.demo.ConstructorRefer;

import javax.print.attribute.standard.PrinterName;

/**
 * 类的构造器引用
 */
public class DemoRer {
    //定义一个方法,参数传递姓名和PesonBuilder接口 方法中通过姓名创建person对象
    public static void printName(String name, PersonBuilder p){
        Person person = p.builderPerson(name);
        System.out.println(person.getName());

    }

   public static void main(String[] args) {
          //调用printName方法,方法的参数是PersonBuilder接口是一个函数式接口,可以使用lambda接口
       printName("迪丽热巴",(String name)->{
           return new Person(name);
       });


       /**
        * 使用方法引用优化lambda表达式
        * 构造方法new Person(String name) 已经存在
        * 创建对象已知  new
        * 就可以使用Person引用new 创建对象
        */
       //同使用Person的带参构造函数,通过传递的姓名创建对象
       printName("唐嫣",Person::new);

   }


}
