package com.sell.util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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
import org.apache.http.util.EntityUtils;

/**
 * 
 * 发送HTTP请求工具类
 * 
 * @author lilin
 * @version [版本号, 2015年7月1日]
 */
public final class HttpUtils {
    /**
     * http连接
     */
    public static final int HTTP_CONNECTION_TYPE_HTTP = 0;
    
    /**
     * https连接
     */
    public static final int HTTP_CONNECTION_TYPE_HTTPS = 1;
    
    /**
     * 定义http连接
     */
    private CloseableHttpClient httpClient;
    
    /**
     * 私有化构造函数
     */
    private HttpUtils() {
        this(HTTP_CONNECTION_TYPE_HTTP);
    }
    
    /**
     * 私有化构造函数
     * 
     * @param type 连接类型
     */
    private HttpUtils(int type) {
        if (HTTP_CONNECTION_TYPE_HTTPS == type) {
            httpClient = getSSLHttpClient();
        } else {
            httpClient = HttpClients.createDefault();
        }
    }
    
    /**
     * 以下为每次访问不关闭连接的API(为提升性能，一个业务流程如果需要多次HTTP请求，建议使用一个httpclient实例)，需调用getInstance方法获取httpclient实例，
     * 并在使用完成后手动调用closeConnection()关闭连接
     */
    
    /**
     * http get请求
     * 
     * @param url 请求url
     * @return 响应字符串
     */
    public String httpget(String url) {
        HttpGet httpGet = new HttpGet(url);
        return http(httpClient, httpGet, false);
    }
    
    /**
     * http post请求(字符串形式)
     * 
     * @param url 请求url
     * @param param 请求参数
     * @return 响应字符串
     */
    public String httppost(String url, String param) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(param, "UTF-8"));
        return http(httpClient, httpPost, false);
    }
    
    /**
     * http post请求(form形式)
     * 
     * @param url 请求url
     * @param paramMap 请求参数
     * @return 响应字符串
     */
    public String httppost(String url, Map<String, String> paramMap) {
        HttpPost httpPost = new HttpPost(url);
        if (paramMap != null) {
            httpPost.setEntity(getUrlEncodedFormEntity(paramMap));
        }
        return http(httpClient, httpPost, false);
    }
    
    /**
     * https get请求
     * 
     * @param url 请求url
     * @return 响应字符串
     */
    public static String httpsget(String url) {
        HttpGet httpGet = new HttpGet(url);
        return http(getSSLHttpClient(), httpGet, false);
    }
    
    /**
     * https post请求(字符串形式)
     * 
     * @param url 请求url
     * @param param 请求参数
     * @return 响应字符串
     */
    public String httpspost(String url, String param) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(param, "UTF-8"));
        return http(getSSLHttpClient(), httpPost, false);
    }
    
    /**
     * https post请求(form形式)
     * 
     * @param url 请求url
     * @param param 请求参数
     * @return 响应字符串
     */
    public String httpspost(String url, Map<String, String> paramMap) {
        HttpPost httpPost = new HttpPost(url);
        if (paramMap != null) {
            httpPost.setEntity(getUrlEncodedFormEntity(paramMap));
        }
        
        return http(getSSLHttpClient(), httpPost, false);
    }
    
    /**
     * 默认返回HTTP连接
     * 
     * @return http
     */
    public static HttpUtils getInstance() {
        return new HttpUtils();
    }
    
    /**
     * 默认返回HTTP连接
     * 
     * @param type 连接类型
     * @return http
     */
    public static HttpUtils getInstance(int type) {
        return new HttpUtils(type);
    }
    
    /********************************************* 以下为每次访问关闭连接的API **********************************************************/
    
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
        return http(getSSLHttpClient(), httpGet, true);
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
        return http(getSSLHttpClient(), httpPost, true);
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
        
        return http(getSSLHttpClient(), httpPost, true);
    }
    
    private static UrlEncodedFormEntity getUrlEncodedFormEntity(Map<String, String> paramMap) {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Entry<String, String> entry : paramMap.entrySet()) {
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return new UrlEncodedFormEntity(formparams, Consts.UTF_8);
    }
    
    private static CloseableHttpClient getSSLHttpClient() {
        X509TrustManager x509mgr = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
            }
            
            public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
            }
            
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }
        };
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{x509mgr}, null);
            SSLConnectionSocketFactory sslsf =
                new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("exception.httprequest-error", e);
        }
    }
    
    private static String http(HttpUriRequest request) {
        return http(HttpClients.createDefault(), request, true);
    }
    
    private static String http(CloseableHttpClient httpclient, HttpUriRequest request, boolean isFinishClose) {
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
            if (isFinishClose) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    throw new RuntimeException("exception.httprequest-error", e);
                }
            }
        }
    }
    
    public static void main(String[] args)
        throws Exception {
        
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("verifyCode", "gBWGwSga2cpavGJYg85e7Q==");
        paramMap.put("xml",
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Request lang=\"zh-CN\" service=\"RouteService\"><Head>BSPdevelop</Head><Body><RouteRequest method_type=\"1\" tracking_number=\"444029276937\" tracking_type=\"1\"/></Body></Request>");
        
        System.out.println(httpsPost("https://bsp-oisp.test.sf-express.com/bsp-oisp/sfexpressService", paramMap));
        System.out.println(httpsGet("https://www.baidu.com"));
    }
    
}
