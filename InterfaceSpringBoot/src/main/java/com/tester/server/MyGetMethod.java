package com.tester.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.omg.CORBA.Request;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@RestController
@Api(value="/",description = "这是全部的get方法")
public class MyGetMethod {

    /*
    *返回cookies信息的get接口
     **/
    @RequestMapping(value="/getcookies",method = RequestMethod.GET)
    @ApiOperation(value="获取到cookies值的方法",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        //HttpServletRequest 装请求信息的类
        //HttpServletResponse 装响应信息的类
        Cookie cookie=new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获得cookies信息成功";
    }

    /*
    * 要求客户端携带cookies访问
    * */
    @RequestMapping(value="/getwithcookies",method = RequestMethod.GET)
    @ApiOperation(value="要求客户端携带cookies访问",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();  //key,value组成的数组
        if (Objects.isNull(cookies)) {
            return "必须要携带cookies信息";
        }
        for (Cookie c : cookies) {
            System.out.println(c.getName());
            System.out.println(c.getValue());
            if (c.getName().equals("login") && c.getValue().equals("true")) {
                System.out.println("ok");
                return "访问成功";
            }
        }
        return "cookies信息不正确！";
    }

    /*
    * 开发一个需要携带参数才能访问的get请求
    * 第一种实现方式 url:key=value&&key=value
    * */
    @RequestMapping(value="/getwithparam",method=RequestMethod.GET)
    @ApiOperation(value="需要携带参数才能访问的get请求方法一",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,
                                       @RequestParam Integer end){
        Map<String,Integer> myList=new HashMap<>();
        myList.put("苹果",30);
        myList.put("西瓜",50);
        myList.put("香蕉",20);
        return myList;
    }

    /*
    * 携带参数的get请求
    * 第二种实现方式：url:port/getwithparam/10/20
    * */
    @RequestMapping(value="/getwithparam/{start}/{end}",method=RequestMethod.GET)
    @ApiOperation(value="需要携带参数才能访问的get请求方法二",httpMethod = "GET")
    public Map myGetList(@PathVariable Integer start,
                         @PathVariable Integer end){
        Map<String,Integer> myList=new HashMap<>();
        myList.put("苹果",50);
        myList.put("西瓜",30);
        myList.put("香蕉",20);
        return myList;
    }

}
