package com.tester.cases;

import com.tester.config.TestConfig;
import com.tester.model.InterfaceName;
import com.tester.model.LoginCase;
import com.tester.utils.ConfigFile;
import com.tester.utils.DatabaseUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class LoginTest {

    @BeforeTest(groups="loginTrue",description = "测试准备工作，获取HttpClient对象")
    public void beforeTest(){
        //获取测试的url
        TestConfig.getUserInfoUrl= ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl=ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.addUserUrl=ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
        TestConfig.updateUserInfoUrl=ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        TestConfig.loginURL=ConfigFile.getUrl(InterfaceName.LOGIN);
        //创建请求对象
        TestConfig.defaultHttpClient=new DefaultHttpClient();
    }

    @Test(groups="loginTrue",description="用户登录成功")
    public void loginTrue() throws IOException {
        SqlSession sqlSession= DatabaseUtils.getSqlSession();
        LoginCase loginCase=sqlSession.selectOne("loginCase",11);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginURL);

        //接口测试代码
        String result=getResult(loginCase);
        //验证结果
        Assert.assertEquals(loginCase.getExpected(),result);


    }

    @Test(description = "用户登录失败")
    public void loginFalse() throws IOException {
        SqlSession sqlSession=DatabaseUtils.getSqlSession();
        LoginCase loginCase=sqlSession.selectOne("loginCase",13);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginURL);
        //发送请求，获取结果
        String result=getResult(loginCase);
        //验证结果
        Assert.assertEquals(loginCase.getExpected(),result);
    }

    private String getResult(LoginCase loginCase) throws IOException {
        //接口测试代码
        HttpPost httpPost=new HttpPost(TestConfig.loginURL);
        JSONObject param=new JSONObject();
        param.put("username",loginCase.getUsername());
        param.put("password",loginCase.getPassword());
        //设置请求头信息 设置header
        httpPost.setHeader("content-type","application/json");
        //把参数添加到请求方法中
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(entity);
        //保存返回结果
        String result;
        //执行post请求
        HttpResponse response=TestConfig.defaultHttpClient.execute(httpPost);
        //处理响应结果
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        TestConfig.cookieStore=TestConfig.defaultHttpClient.getCookieStore();
        return result;
    }

}
