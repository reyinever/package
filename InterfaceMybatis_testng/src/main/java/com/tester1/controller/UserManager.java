package com.tester1.controller;

import com.tester1.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Log4j
@RestController
@Api(value = "v1", description = "用户管理系统")
@RequestMapping("v1")
public class UserManager {
    //获取一个执行sql语句对象
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @ApiOperation(value = "登录接口", httpMethod = "POST")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Boolean login(HttpServletResponse response,
                         @RequestBody User user) {
        int i = sqlSessionTemplate.selectOne("login", user);
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        log.info("查询到的结果是：" + i);
        if (i == 1) {
            log.info("登录的用户是：" + user.getUsername());
            return true;
        }
        return false;
    }


    @ApiOperation(value = "添加用户接口", httpMethod = "POST")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public Boolean addUser(HttpServletRequest request,
                           @RequestBody User user) {
        //验证cookie
        Boolean ck = verifyCookies(request);
        int result = 0;
        if (ck != null) {  //有cookies值
            result = sqlSessionTemplate.insert("addUser", user);
        }
        if (result > 0) {
            log.info("添加用户的数量是：" + request);
            return true;
        }
        return false;

    }

    private Boolean verifyCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            log.info("cookies为空");
            return false;
        }
        for (Cookie c : cookies) {
            if (c.getName().equals("login") && c.getValue().equals("true")) {
                log.info("cookies验证通过");
                return true;
            }
        }
        return false;
    }

    //获取用户列表/信息
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表（信息）", httpMethod = "POST")
    public List<User> getUserInfo(HttpServletRequest request,
                                  @RequestBody User user) {
        Boolean ck = verifyCookies(request);
        if (ck == true) {
            List<User> users = sqlSessionTemplate.selectList("getUserInfo", user);
            log.info("getUserInfo获取到的用户数量是：" + users.size());
            return users;
        } else {
            return null;
        }

    }

    @ApiOperation(value = "更新/删除用户接口", httpMethod = "POST")
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public int updateUser(HttpServletRequest request,
                          @RequestBody User user) {
        Boolean ck = verifyCookies(request);
        int i = 0;
        if (ck == true) {
            i = sqlSessionTemplate.update("updateUserInfo", user);
        }
        log.info("更新数据的条目是：" + i);
        return i;
    }

}
