package com.tester.httpclient.cookies;

import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class MyCookiesForGet {

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
        result=EntityUtils.toString(httpEntity,"utf-8");
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

    @Test(dependsOnMethods={"testGetCookies"})
    public void testWithCookies() throws IOException {
        String uri=this.resourceBundle.getString("getWithCookies.uri");
        String testUrl=this.url+uri;
        System.out.println(testUrl);

        HttpGet httpGet=new HttpGet(testUrl);
        DefaultHttpClient client=new DefaultHttpClient();
        //设置cookies信息
        client.setCookieStore(this.cookieStore);
        System.out.println("cookieStore:"+cookieStore);

        HttpResponse response=client.execute(httpGet);

        //获取响应状态码
        int statusCode=response.getStatusLine().getStatusCode();
        System.out.println("状态码："+statusCode);

        if(statusCode==200){
            String result=EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println(result);
        }else{
            System.out.println("请求失败");
        }







    }

}
