package com.ryy.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//方法分组测试

public class GroupsOnMethod {

    @Test(groups="server")
    public void test1(){
        System.out.println("server组的测试方法111");
    }

    @Test(groups="server")
    public void test2(){
        System.out.println("server组的测试方法222");
    }

    @Test(groups="client")
    public void test3(){
        System.out.println("client组的测试方法333");
    }

    @Test(groups="client")
    public void test4(){
        System.out.println("client组的测试方法444");
    }

    @BeforeGroups("server")
    public void beforeGroupsServer(){
        System.out.println("BeforeGroups server");
    }

    @AfterGroups("server")
    public void afterGroupServer(){
        System.out.println("AfterGroups server");
    }

    @BeforeGroups("client")
    public void beforeGroupsClient(){
        System.out.println("BeforeGroups client");
    }

    @AfterGroups("client")
    public void afterGroupsClient(){
        System.out.println("AfterGroups client");
    }

}
