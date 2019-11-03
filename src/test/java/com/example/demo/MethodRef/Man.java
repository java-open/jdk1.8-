package com.example.demo.MethodRef;

/**
 * 子类 --测试super父类方法引用
 */
public class Man extends Human {
    //重写父类sayhello方法


    @Override
    public void sayHello() {
       System.out.println("我是子类!!!!!");
    }

    //定义一个方法参数传递Greetable接口
    public  void method(Greetable g){
        g.greet();
    }

    //逐步开始引入super引用父类方法
    public void show(){
        //调用method方法,方法的参数Greetable是一个函数式接口,所以可以传递lambda表达式
        method(()->{
            //创建父类Human对象
            Human h= new Human();
            //调用父类的sayhello方法
            h.sayHello();
        });

        //因为有子父类关系,所以存在的一个关键字super,代表父类,所以我们可以直接使用super调用父类的成员方法
        method(()->{
            super.sayHello();
        });

        /**
         * 使用super引用类的成员方法
         * super是已经存在的 父类成员方法sayHello也是已经存在的
         * 所以我们可以直接使用super引用父类的成员方法
         *
         * */
        method(super::sayHello);
    }

    public static void main(String[] args) {
        new Man().show();
    }
}

