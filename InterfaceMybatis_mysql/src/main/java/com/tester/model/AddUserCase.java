package com.tester.model;

import lombok.Data;

@Data
public class AddUserCase {
    private int id;
    private String username;
    private String password;
    private int age;
    private String sex;
    private String permission;
    private String isDelete;
    private String expected;

}
