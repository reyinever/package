package com.tester.httpclient.cookies;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class MyCookiesForPost {

    private String url;
    //解析 .properties 文件
    private ResourceBundle resourceBundle;
    //存储cookie信息
    private CookieStore cookieStore;

    @BeforeTest
    public void beforeTest() {
        resourceBundle = ResourceBundle.getBundle("application");
        url = resourceBundle.getString("test.url");
        //System.out.println(url);
    }

    @Test
    public void testGetCookies() throws IOException {
        //保存结果
        String result=null;
        //从配置文件中获取变量
        String uri = resourceBundle.getString("getCookies.uri");
        //拼接url
        String testUrl = this.url + uri;
        System.out.println(testUrl);
        //逻辑代码
        HttpGet httpGet = new HttpGet(testUrl);

        DefaultHttpClient client=new DefaultHttpClient();
        HttpResponse response=client.execute(httpGet);

        HttpEntity httpEntity= response.getEntity();
        result= EntityUtils.toString(httpEntity,"utf-8");
        //System.out.println(result);

        //获取cookies信息
        this.cookieStore=client.getCookieStore();
        List<Cookie> cookieList=this.cookieStore.getCookies();

        for(Cookie c:cookieList){
            String name=c.getName();
            String value=c.getValue();
            System.out.println("cookie_name="+name+"; cookie_value="+value);
        }

    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void postWithCookies() throws IOException {
        String uri=this.resourceBundle.getString("postWithCookies.uri");
        //拼接最终的测试地址
        String testUrl=this.url+uri;

        //声明client对象
        DefaultHttpClient client=new DefaultHttpClient();
        //声明post方法
        HttpPost httpPost=new HttpPost(testUrl);
        //添加参数
        JSONObject param=new JSONObject();
        param.put("name","haha");
        param.put("age","21");

        //设置请求头信息 设置header
        httpPost.setHeader("content-type","application/json");
        //将参数信息添加到方法中
        StringEntity stringEntity=new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(stringEntity);
        //声明一个对象来进行响应结果的存储
        String result;
        //设置cookies信息
        client.setCookieStore(this.cookieStore);
        //执行post方法
        HttpResponse response=client.execute(httpPost);
        //获取响应结果
        result=EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        //处理结果，判断返回结果是否符合预期
        //将返回的响应结果字符串转换为json对象
        JSONObject resultJson=new JSONObject(result);
        //获取结果中的值
        String msg=(String)resultJson.get("msg");
        String status=(String)resultJson.get("status");
        //具体的判断返回结果的值
        Assert.assertEquals("ok",msg);
        Assert.assertEquals("1",status);

    }

}
