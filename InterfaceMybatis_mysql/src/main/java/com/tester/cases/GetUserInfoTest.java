package com.tester.cases;

import com.tester.config.TestConfig;
import com.tester.model.GetUserInfoCase;
import com.tester.model.UpdateUserInfoCase;
import com.tester.model.User;
import com.tester.utils.DatabaseUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetUserInfoTest {

    @Test(dependsOnGroups="loginTrue",description="获取userId为1的用户信息")
    public void getUserInfo() throws InterruptedException, IOException {
        SqlSession sqlSession= DatabaseUtils.getSqlSession();
        GetUserInfoCase getUserInfoCase=sqlSession.selectOne("getUserInfoCase",1);
        System.out.println(getUserInfoCase.toString());
        System.out.println(TestConfig.getUserInfoUrl);

        JSONArray resultJson=getJsonResult(getUserInfoCase);

        Thread.sleep(3000);
        User user=sqlSession.selectOne(getUserInfoCase.getExpected(),getUserInfoCase);
        System.out.println(user.toString());

        List userList=new ArrayList();
        userList.add(user);
        JSONArray jsonArray=new JSONArray(userList);
        System.out.println("获取用户信息："+jsonArray.toString());
        System.out.println("调用接口获取用户信息："+resultJson.toString());

        //验证结果
        //注意：如果数据的顺序不一样也会验证失败，
        //     如果顺序不一样需要把数据一个一个取出来进行对比
        //Assert.assertEquals(jsonArray,resultJson);

        JSONArray jsonArray1=new JSONArray(resultJson.getString(0));
        Assert.assertEquals(jsonArray.toString(),jsonArray1.toString());

    }

    private JSONArray getJsonResult(GetUserInfoCase getUserInfoCase) throws IOException {
        HttpPost httpPost=new HttpPost(TestConfig.getUserInfoUrl);
        JSONObject param=new JSONObject();
        param.put("id",getUserInfoCase.getUserId());

        //设置请求头
        httpPost.setHeader("content-type","application/json");
        //添加参数
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(entity);
        //设置cookies
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);

        HttpResponse response=TestConfig.defaultHttpClient.execute(httpPost);
        String result= EntityUtils.toString(response.getEntity(),"utf-8");

        System.out.println(result);
        List resultList= Arrays.asList(result);
        JSONArray array=new JSONArray(resultList);
        System.out.println(array.toString());
        return array;

    }

}
