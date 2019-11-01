package com.example.demo;

import com.example.demo.MethodRef.MehodRefObject;
import com.example.demo.MethodRef.Printable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
    方法引用
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class quoteTest {
    /**
     * 通过对象名引用成员方法
     * 1.通过对象名引用成员方法
     * 使用前提是对象名已经存在,成员方法也是已经存在
     * 就可以使用对象名来引用成员方法
     */

    //定义一个方法,方法的参数传递Printable接口
    public static void printString(Printable p) {
        p.print("hello!");
    }

    //调用printString方法,方法的参数是Printalbe 是一个函数式接口,可以传递lambda表达式
    @Test
    public void testref() {
        printString((s) -> {
            //创建一个MethodObject对象
            MehodRefObject obj = new MehodRefObject();
            //调用MethodRefObject对象中的成员方法printUpperCase
            obj.printUpperCaseString(s);

        });


    //使用方法引用来优化lambda 对象是已经存在的MethodRerObject成员方法也是已经存在的printUpperCase
    //所以使用对象名引用成员方法
        MehodRefObject obj = new MehodRefObject();
        printString(obj::printUpperCaseString);

    }



























}