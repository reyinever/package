package com.tester.httpclient.demo;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class HttpClientDemo1 {

    @Test
    public void test1() throws IOException {
        //用来存放结果
        String result = null;
        //get方法
        HttpGet get = new HttpGet("http://www.baidu.com");
        //创建 httpClient 对象
        CloseableHttpClient client = HttpClients.createDefault();
        //发送get请求
        CloseableHttpResponse response = client.execute(get);

        //把响应结果转换为字符串
        //result = EntityUtils.toString(response.getEntity(),"utf-8");

        HttpEntity httpEntity = response.getEntity();
        System.out.println(httpEntity);
        result=EntityUtils.toString(httpEntity,"utf-8");
        System.out.println(result);

    }
}
