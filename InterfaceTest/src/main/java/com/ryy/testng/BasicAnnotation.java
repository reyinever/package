package com.ryy.testng;


import org.testng.annotations.*;

public class BasicAnnotation {
    @BeforeClass
    public void beforeClass(){
        System.out.println("BeforeClass 这是在类运行之前运行的方法");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("AfterClass 这是在类运行之后运行的方法");
    }

    // 最基本的注解，标识这是一个测试方法，
    // 用来把方法标记为测试的一部分
    @Test
    public void testCase1(){
        System.out.println("Test 这是测试用例1");
        System.out.println("Thread id: "+Thread.currentThread().getId());
    }

    @Test
    public void testCase2(){
        System.out.println("Test 这是测试用例2");
        System.out.println("Thread id: "+Thread.currentThread().getId());
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("BeforeMethod 这是在测试方法之前运行的");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("AfterMethod 这是在测试方法之后运行的");
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("BeforeSuite 这是在BeforeClass之前运行的方法");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("AfterSuite 这是在AfterClass之后运行的方法");
    }
}
