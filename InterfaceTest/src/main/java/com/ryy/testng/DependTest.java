package com.ryy.testng;

import org.testng.annotations.Test;

public class DependTest {

    @Test
    public void test1(){
        System.out.println("test1执行啦");
        throw new RuntimeException();
    }

    @Test(dependsOnMethods={"test1"})
    public void test2(){
        System.out.println("test2执行啦");
    }
}
