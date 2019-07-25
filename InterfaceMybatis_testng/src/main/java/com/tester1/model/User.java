package com.tester1.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private int age;
    private String sex;
    private String permission;
    private String isDelete;
}
