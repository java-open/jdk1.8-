package com.example.demo;

import com.example.demo.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * Optional类测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestOptional {
    /*
	 * Optional容器类的常用方法：
	 * 	of(T value)：创建一个Optional实例
	 */
    @Test
    public void test1() {
        Optional<Employee> op = Optional.of(new Employee());
        System.out.println(op.get());
    }

    /*
	 * 	empty()：创建一个空的Optional实例
	 */
    @Test
    public void test2() {
        Optional<Employee> op = Optional.empty();
        System.out.println(op.get());
    }

    /*
	 * 	ofNullable(T value)：若T不为null，创建Optional实例，否则创建空实例
	 * 	isPresent()：判断是否包含值
	 * 	orElse(T other)：如果调用对象包含值，返回该值，否则返回other
	 * 	orElseGet(T other)：如果调用对象包含值，返回该值，否则返回other获取的值
	 */
    @Test
    public void test3() {
        Optional<Employee> op = Optional.ofNullable(null);
//		System.out.println(op.get());

        System.out.println(op.isPresent());

        Employee emp = op.orElse(new Employee("张三","公关"));
        System.out.println(emp);
        Employee emp1 = op.orElseGet(() -> new Employee());

       System.out.println(emp1);
    }


    /*
 * 	map(Function mapper)：如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
 * 	flatMap(Function mapper)：与map类似，要求返回值必须是Optional
 */
    @Test
    public void test4() {
        Optional<Employee> op = Optional.ofNullable(new Employee("张三", "出台"));
        Optional<String> str = op.map((e) -> e.getName());
        System.out.println(str.get());

        Optional<String> str1 = op.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(str1.get());
    }

}










