package com.ryy.testng.groups;

import org.testng.annotations.Test;

@Test(groups="teacher")
public class GroupsOnClass3 {

    public void t1(){
        System.out.println("GroupsOnClass3中的t1运行啦");
    }

    public void t2(){
        System.out.println("GroupsOnClass3中的t2运行啦");
    }
}
