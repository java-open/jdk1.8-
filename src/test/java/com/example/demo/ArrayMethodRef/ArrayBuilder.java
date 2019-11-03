package com.example.demo.ArrayMethodRef;

/**
 * 创建一个数组的函数式接口
 */
@FunctionalInterface
public interface ArrayBuilder {
    //定义一个创建int类型数组的方法,参数传递数字的长度 ,返回创建好的int类型数组
    int[] builderArray(int length);
}
