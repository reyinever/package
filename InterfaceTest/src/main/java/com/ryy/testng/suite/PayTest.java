package com.ryy.testng.suite;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PayTest {

    @Test
    public void paySuccess(){
        System.out.print("Test 支付成功");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.print("BeforTest ...");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("AfterTest ...");
    }
}
