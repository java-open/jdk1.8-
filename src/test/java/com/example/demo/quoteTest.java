package com.example.demo;

import com.example.demo.ArrayMethodRef.ArrayBuilder;
import com.example.demo.MethodRef.Calcable;
import com.example.demo.MethodRef.MehodRefObject;
import com.example.demo.MethodRef.Printable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.lang.model.element.VariableElement;
import java.util.Arrays;

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

    /**
     * 通过类名引用静态成员方法
     * 类已经存在,静态成员方法也已经存在
     * 就可以通过类名直接引用静态成员方法
     */

    //定义一个方法,方法的参数传递要计算绝对值的整数和函数式接口calcable
    public static int method(int number,Calcable c){
        return c.clasAbs(number);
    }
    @Test
    public  void  testClssReference(){
        int number =method(-10,(n)->{
            return Math.abs(n);
        });
        System.out.println(number);

        //使用方法引用优化lambda表达式
        //math类存在,abs计算绝对值的静态方法也是存在的  所以我们可以直接通过类名引用静态方法
        int number2 =method(-20,Math::abs);
        System.out.println(number2);

    }

    /**
     * 通过super引用父类方法
     * super是已经存在的
     * 父类的成员方法sayhello也是已经存在的
     * 所以我们可以直接引用super 引用父类的成员方法
     */


    /**
     * 通过this引用成员方法
     * this是已经存在的
     * 本类的成员方法buyHouse也是已经存在的
     * 所有我们可以直接使用this引用本类的成员方法
     */

    /**
     * 类的构造器引用
     * 使用方法引用优化lambda表达式
     * 构造方法new Person(String name) 已经存在
     * 创建对象已知  new
     * 就可以使用Person引用new 创建对象
     */

    /**
     * 数组的构造器引用
     */
    //定义一个方法,方法的参数传递创建数组长度和ArrayBuilder接口
    public  static  int[] creatArray(int length , ArrayBuilder  ab){
        return ab.builderArray(length);
    }

    @Test
    public  void  testArrayRef(){
        //调用CreateArray方法传递数组长度和lambda表达式
        int[] array = creatArray(5, (len) -> {
            //根据数组长度,创建数组长度并返回
            return new int[len];
        });
        System.out.println(array.length);


        /**
         * 使用方法引用优化lambda表达式
         * 已知创建就是int[]数组
         * 数组的长度也是已知de
         *就可以使用方法引用
         * int{}引用new 根据参数传递的长度来创建数组
         */
        int[] array2 = creatArray(10, int[]::new);
        System.out.println(Arrays.toString(array2));
        System.out.println(array2.length);




    }




























}