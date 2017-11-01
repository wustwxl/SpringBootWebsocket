package com.wust.utils;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

//import org.apache.commons.httpclient.Header;
//import org.apache.commons.httpclient.HttpStatus;
//import org.apache.commons.httpclient.NameValuePair;

/**
 * Description:
 * 以get/post的方式发送数据到指定的http接口---HTTP接口的调用
 */

public class HttpClientUtil {

    private static final Log log = LogFactory.getLog(HttpClientUtil.class);

    // 除了URL,不带参数的get方法
    public static String getHttp(String url) {
        String responseMsg = "";

        // 1.构造HttpClient的实例
        HttpClient httpClient = new HttpClient();

        // 2.创建GetMethod的实例
        log.info("URL --> " + url);
        GetMethod getMethod = new GetMethod(url);

        // 使用系统系统的默认的恢复策略
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

        try {
            //3.执行getMethod,调用http接口
            httpClient.executeMethod(getMethod);

            //4.读取内容
            byte[] responseBody = getMethod.getResponseBody();

            //5.处理返回的内容
            responseMsg = new String(responseBody);
            log.info("处理结果 --> " + responseMsg);

        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6.释放连接
            getMethod.releaseConnection();
        }

        return responseMsg;
    }

    //除了URL,有参数的get方法
    public static String getHttp(String url_param, String param) {

        String url = url_param + "?param=" + param;

        return getHttp(url);
    }

    //除了URL,有参数的post方法
    public static String postHttp(String url, String param) {
        String responseMsg = "";

        //1.构造HttpClient的实例
        HttpClient httpClient = new HttpClient();
        httpClient.getParams().setContentCharset("utf-8");

        //2.构造PostMethod的实例
        log.info("URL --> " + url);
        PostMethod postMethod = new PostMethod(url);

        //3.把参数值放入到PostMethod对象中（可放置多个参数）
        //方式1：
        //NameValuePair[] data = {new NameValuePair("param1", param), new NameValuePair("param2", param2)};
        //postMethod.setRequestBody(data);

        //方式2：
        postMethod.addParameter("param", param);
        //postMethod.addParameter("param2", param2)


        try {
            // 4.执行postMethod,调用http接口
            httpClient.executeMethod(postMethod);//200

            // 5.读取内容
            responseMsg = postMethod.getResponseBodyAsString().trim();
            log.info("处理结果 --> " + responseMsg);

            // 6.处理返回的内容

        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 7.释放连接
            postMethod.releaseConnection();
        }
        return responseMsg;
    }
}
