package com.ryy.testng.multiThread;

import org.testng.annotations.Test;

import static java.lang.Thread.*;

public class MultiThreadOnXml {

    @Test
    public void test1(){
        System.out.println("Thread id: "+Thread.currentThread().getId());
    }

    @Test
    public void test2(){
        System.out.println("Thread id: "+Thread.currentThread().getId());
    }

    @Test
    public void test3(){
        System.out.println("Thread id: "+Thread.currentThread().getId());
    }
}
