package com.ryy.testng.groups;

import org.testng.annotations.Test;

@Test(groups="stua")
public class GroupsOnClass1 {

    public void stu1(){
        System.out.println("GroupsOnClass1中的stu1运行啦");
    }

    public void stu2(){
        System.out.println("GroupsOnClass1中的stu2方法运行啦");
    }

}
