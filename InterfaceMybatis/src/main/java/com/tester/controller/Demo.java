package com.tester.controller;

import com.tester.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


//@Log4j
@RestController
@Api(value = "v2", description = "这是我的第二个版本demo")
@RequestMapping("v2")
public class Demo {
    //首先获取一个执行sql语句的对象
    @Autowired
    private SqlSessionTemplate template;

    //查询数据
    @RequestMapping(value = "/getusercount", method = RequestMethod.GET)
    @ApiOperation(value = "可以获取到用户数", httpMethod = "GET")
    public int getUserCount() {
        return template.selectOne("getUserCount");
    }

    //添加数据
    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public int addUser(@RequestBody User user) {
        int result = template.insert("addUser", user);
        return result;
    }

    //更新数据
    @RequestMapping(value="/updateuser",method = RequestMethod.POST)
    public int updateUser(@RequestBody User user){
        int result=template.update("updateUser",user) ;
        return result;
    }

    //删除数据
    @RequestMapping(value="/deluser",method = RequestMethod.DELETE)
    public int delUser(@RequestParam int id){
        int result=template.delete("delUser",id);
        return result;
    }

}
