package com.tester.cases;

import com.tester.config.TestConfig;
import com.tester.model.AddUserCase;
import com.tester.model.User;
import com.tester.utils.DatabaseUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AddUserTest {

    @Test(dependsOnGroups = "loginTrue",description = "添加用户接口测试")
    public void addUser() throws IOException, InterruptedException {
        SqlSession sqlSession= DatabaseUtils.getSqlSession();
        AddUserCase addUserCase=sqlSession.selectOne("addUserCase",21);
        System.out.println(addUserCase);
        System.out.println(TestConfig.addUserUrl);

        //发送请求，获取结果
        String result=getResult(addUserCase);

        /*
        *查询是否添加成功
         */
        Thread.sleep(6000);
        User user=sqlSession.selectOne("addUser",addUserCase);
        System.out.println(user.toString());
        //预期和实际进行比较
        Assert.assertEquals(addUserCase.getExpected(),result);


    }

    public String getResult(AddUserCase addUserCase) throws IOException {
        HttpPost httpPost=new HttpPost(TestConfig.addUserUrl);
        JSONObject param=new JSONObject();
        param.put("username",addUserCase.getUsername());
        param.put("password",addUserCase.getPassword());
        param.put("sex",addUserCase.getSex());
        param.put("age",addUserCase.getAge());
        param.put("permission",addUserCase.getPermission());
        param.put("isDelete",addUserCase.getIsDelete());
        //设置头信息
        httpPost.setHeader("content-type","application/json");
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(entity);
        //设置cookie
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);
        //执行请求
        HttpResponse response=TestConfig.defaultHttpClient.execute(httpPost);
        //处理响应结果
        String result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        return result;

    }
}
