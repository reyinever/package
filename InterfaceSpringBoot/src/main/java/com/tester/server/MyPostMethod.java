package com.tester.server;

import com.tester.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@Api(value = "/", description = "全部的post请求")
@RequestMapping(value = "/v1")
public class MyPostMethod {

    //保存cookie信息
    private static Cookie cookie;
    //登录成功取到cookie，然后再访问其他的接口

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登录接口，登录成功后获取cookies信息", httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName", required = true) String username,
                        @RequestParam(value = "password", required = true) String password) {
        if (username.equals("zs") && password.equals("11")) {
            Cookie cookies = new Cookie("login", "true");
            response.addCookie(cookies);
            return "恭喜你登录成功！";
        }
        return "用户名或密码错误";
    }

    @RequestMapping(value = "/getuser", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表", httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                            @RequestBody User u) {
        User user;
        //获取cookies信息
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("login")
                    && c.getValue().equals("true")
                    && u.getUserName().equals("zs")
                    && u.getPassword().equals("11")) {
                //验证通过
                user=new User();
                user.setName("zs");
                user.setAge("25");
                user.setSex("f");
                return user.toString();
            }
        }
        return "参数不合法";
    }

}
