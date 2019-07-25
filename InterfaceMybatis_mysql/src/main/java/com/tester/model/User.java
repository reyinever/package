package com.tester.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private int age;
    private String password;
    private String sex;
    private String permission;
    private String isDelete;

    @Override
    public String toString(){
        return ("{id:"+id+","+
                "username:"+username+","+
                "password:"+password+","+
                "age:"+age+","+
                "sex:"+sex+","+
                "permission:"+permission+","+
                "isDelete:"+isDelete+"}");

    }

}
