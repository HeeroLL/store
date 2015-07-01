package com.sell.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

/**
 * 
 * 发送HTTP请求工具类
 * 
 * @author lilin
 * @version [版本号, 2015年7月1日]
 */
public final class HttpUtils {
    
    public static void main(String[] args) throws Exception{
        System.out.println(httpsGet("https://www.baidu.com"));
    }
    
    /**
     * http get请求
     * 
     * @param url 请求url
     * @return 响应字符串
     */
    public static String httpGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        return http(httpGet);
    }
    
    /**
     * http post请求(字符串形式)
     * 
     * @param url 请求url
     * @param param 请求参数
     * @return 响应字符串
     */
    public static String httpPost(String url, String param) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(param, "UTF-8"));
        return http(httpPost);
    }
    
    /**
     * http post请求(form形式)
     * 
     * @param url 请求url
     * @param paramMap 请求参数
     * @return 响应字符串
     */
    public static String httpPost(String url, Map<String, String> paramMap) {
        HttpPost httpPost = new HttpPost(url);
        if (paramMap != null) {
            httpPost.setEntity(getUrlEncodedFormEntity(paramMap));
        }
        
        return http(httpPost);
    }
    
    /**
     * https get请求
     * 
     * @param url 请求url
     * @return 响应字符串
     */
    public static String httpsGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        return http(getSSLHttpClient(), httpGet);
    }
    
    /**
     * https post请求(字符串形式)
     * 
     * @param url 请求url
     * @param param 请求参数
     * @return 响应字符串
     */
    public static String httpsPost(String url, String param) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(param, "UTF-8"));
        return http(getSSLHttpClient(), httpPost);
    }
    
    /**
     * https post请求(form形式)
     * 
     * @param url 请求url
     * @param param 请求参数
     * @return 响应字符串
     */
    public static String httpsPost(String url, Map<String, String> paramMap) {
        HttpPost httpPost = new HttpPost(url);
        if (paramMap != null) {
            httpPost.setEntity(getUrlEncodedFormEntity(paramMap));
        }
        
        return http(getSSLHttpClient(), httpPost);
    }
    
    private static UrlEncodedFormEntity getUrlEncodedFormEntity(Map<String, String> paramMap) {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Entry<String, String> entry : paramMap.entrySet()) {
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return new UrlEncodedFormEntity(formparams, Consts.UTF_8);
    }
    
    private static CloseableHttpClient getSSLHttpClient() {
        SSLContext sslContext = SSLContexts.createSystemDefault();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }
    
    private static String http(HttpUriRequest request) {
        return http(HttpClients.createDefault(), request);
    }
    
    private static String http(CloseableHttpClient httpclient, HttpUriRequest request) {
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(request);
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("exception.httprequest-error", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("exception.httprequest-error", e);
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                throw new RuntimeException("exception.httprequest-error", e);
            }
        }
    }
}
