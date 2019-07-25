package com.tester.model;

import lombok.Data;

@Data
public class GetUserListCase {
    private String username;
    private int age;
    private String sex;
    private String expected;

}
