package com.ryy.testng.suite;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {

    @Test
    public void loginSuccess(){
        System.out.println("Test 登录成功");
    }

    @BeforeTest
    public void beforTest(){
        System.out.print("BeforeTest ...");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("AfterTest ...");
    }

}
