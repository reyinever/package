package com.tester.utils;

import com.tester.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFile {
    private static ResourceBundle resourceBundle=ResourceBundle.getBundle("application", Locale.CHINA);

    public static String getUrl(InterfaceName interfaceName){
        String baseUrl=resourceBundle.getString("test.url");
        String uri="";
        //最终的测试地址
        String testUrl;

        if(interfaceName==InterfaceName.ADDUSERINFO){
            uri=resourceBundle.getString("addUser.uri");
        }else if(interfaceName==InterfaceName.LOGIN){
            uri=resourceBundle.getString("login.uri");
        }else if(interfaceName==InterfaceName.GETUSERLIST){
            uri=resourceBundle.getString("getUserList.uri");
        }else if(interfaceName==InterfaceName.GETUSERINFO){
            uri=resourceBundle.getString("getUserInfo.uri");
        }else if(interfaceName==InterfaceName.UPDATEUSERINFO){
            uri=resourceBundle.getString("updateUserInfo.uri");
        }else{
            return "接口名称枚举类型不存在";
        }

        testUrl=baseUrl+uri;
        return testUrl;

    }
}
