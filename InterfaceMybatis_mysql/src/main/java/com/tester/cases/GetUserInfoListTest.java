package com.tester.cases;

import com.mongodb.util.JSON;
import com.tester.config.TestConfig;
import com.tester.model.GetUserInfoCase;
import com.tester.model.GetUserListCase;
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
import java.util.List;

public class GetUserInfoListTest {

    @Test(dependsOnGroups = "loginTrue",description = "获取性别为男的用户信息")
    public void getUserInfoList() throws IOException, InterruptedException {
        SqlSession sqlSession= DatabaseUtils.getSqlSession();
        GetUserListCase getUserListCase=sqlSession.selectOne("getUserListCase",1);
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUrl);

        //发送请求，获取结果
        JSONArray resultJson=getJsonResult(getUserListCase);

        Thread.sleep(2000);
        // --------------------------------------执行sql语句
        List<User> userList=sqlSession.selectList(getUserListCase.getExpected(),getUserListCase);
        for(User u:userList){
            System.out.println("获取的user:"+u.toString());
        }
        JSONArray userListJson=new JSONArray(userList);
        Assert.assertEquals(userListJson.length(),resultJson.length());
        for(int i=0;i<resultJson.length();i++){
            JSONObject expect=(JSONObject)resultJson.get(i);
            JSONObject actual=(JSONObject)userListJson.get(i);
            Assert.assertEquals(expect.toString(),actual.toString());
        }



    }

    private JSONArray getJsonResult(GetUserListCase getUserListCase) throws IOException {
        HttpPost httpPost=new HttpPost(TestConfig.getUserListUrl);
        JSONObject param=new JSONObject();
        param.put("username",getUserListCase.getUsername());
        param.put("sex",getUserListCase.getSex());
        param.put("age",getUserListCase.getAge());
        //设置请求头
        httpPost.setHeader("content-type","application/json");
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(entity);

        TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);

        HttpResponse httpResponse=TestConfig.defaultHttpClient.execute(httpPost);
        String result= EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        System.out.println(result);
        JSONArray jsonArray=new JSONArray(result);
        System.out.println(jsonArray.toString());

        return jsonArray;
    }
}
