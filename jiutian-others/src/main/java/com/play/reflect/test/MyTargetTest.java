package com.play.reflect.test;

/**
 * @Author : lihao
 * Created on : 2020-04-29
 * @Description : TODO描述类作用
 */
import java.lang.reflect.Method;


public class MyTargetTest
{
    @MyTarget
    public void doSomething()
    {
        System.out.println("hello world");
    }

    public static void main(String[] args) throws Exception
    {
        Method method = MyTargetTest.class.getMethod("doSomething",null);
        Class<?> aClass = Class.forName("com.play.reflect.test.MyTargetTest");
        Object o1 = aClass.newInstance();
        if(o1 instanceof MyTargetTest){
            System.out.println("bcd");
        }

        Class<MyTargetTest> class1 =MyTargetTest.class;
        Object o = class1.newInstance();
        if(o instanceof MyTargetTest){
            System.out.println("abc");
        }

        if(method.isAnnotationPresent(MyTarget.class))//如果doSomething方法上存在注解@MyTarget，则为true
        {
            System.out.println(method.getAnnotation(MyTarget.class));
        }
    }
}
