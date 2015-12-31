package com.isell.core.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.Header;
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
import org.apache.log4j.Logger;
import org.springframework.util.StreamUtils;

/**
 * <pre>
 * 发送HTTP请求工具类
 * 1. 通过调用getInstance方法获取httpclient实例，从而发送http/https请求可以保证每次访问不关闭连接，
 *    在使用完成后手动调用closeConnection()关闭连接。
 * 2. 通过提供的静态方法，可以更方便发送请求，适用于绝大多数单次请求的场景
 * (为提升性能，一个业务流程如果需要多次HTTP请求，建议采用方法1：使用一个httpclient实例)
 * </pre>
 * 
 * @author lilin
 * @version [版本号, 2015年7月1日]
 */
public final class HttpUtils {
    /**
     * log
     */
    private static final Logger log = Logger.getLogger(HttpUtils.class);
    
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
     * get请求
     * 
     * @param url 请求url
     * @return 响应字符串
     */
    public String sendGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        return http(httpClient, httpGet, false);
    }
    
    /**
     * post请求(字符串形式)
     * 
     * @param url 请求url
     * @param param 请求参数
     * @return 响应字符串
     */
    public String sendPost(String url, String param) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(param, "UTF-8"));
        return http(httpClient, httpPost, false);
    }
    
    /**
     * post请求(form形式)
     * 
     * @param url 请求url
     * @param paramMap 请求参数
     * @return 响应字符串
     */
    public String sendPost(String url, Map<String, String> paramMap) {
        HttpPost httpPost = new HttpPost(url);
        if (paramMap != null) {
            httpPost.setEntity(getUrlEncodedFormEntity(paramMap));
        }
        return http(httpClient, httpPost, false);
    }
    
    /**
     * 关闭HTTP
     */
    public void closeConn() {
        closeConnenction(httpClient);
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
     * @param headers 请求头
     * @return 响应字符串
     */
    public static String httpPost(String url, String param, Header... headers) {
        HttpPost httpPost = new HttpPost(url);
        if (ArrayUtils.isNotEmpty(headers)) {
            httpPost.setHeaders(headers);
        }
        httpPost.setEntity(new StringEntity(param, "UTF-8"));
        return http(httpPost);
    }
    
    /**
     * http post请求(字符串形式)
     * 
     * @param url 请求url
     * @param param 请求参数
     * @param contentType contentType
     * @param headers 请求头
     * @return 响应字符串
     */
    public static String httpPost(String url, String param, String contentType, Header... headers) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", contentType);
        httpPost.setHeader("Content-Type", contentType + ";charset=utf-8");
        if (ArrayUtils.isNotEmpty(headers)) {
            for (Header header : headers) {
                httpPost.setHeader(header);
            }
        }
        httpPost.setEntity(new StringEntity(param, "UTF-8"));
        return http(httpPost);
    }
    
    /**
     * http post请求(form形式)
     * 
     * @param url 请求url
     * @param paramMap 请求参数
     * @param headers 请求头
     * @return 响应字符串
     */
    public static String httpPost(String url, Map<String, String> paramMap, Header... headers) {
        HttpPost httpPost = new HttpPost(url);
        if (ArrayUtils.isNotEmpty(headers)) {
            httpPost.setHeaders(headers);
        }
        if (paramMap != null) {
            httpPost.setEntity(getUrlEncodedFormEntity(paramMap));
        }
        
        return http(httpPost);
    }
    
    /**
     * http post请求(form形式)
     * 
     * @param url 请求url
     * @param paramMap 请求参数
     * @param contentType contentType
     * @param headers 请求头
     * @return 响应字符串
     */
    public static String httpPost(String url, Map<String, String> paramMap, String contentType, Header... headers) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", contentType);
        httpPost.setHeader("Content-Type", contentType + ";charset=utf-8");
        if (ArrayUtils.isNotEmpty(headers)) {
            for (Header header : headers) {
                httpPost.setHeader(header);
            }
        }
        
        if (paramMap != null) {
            httpPost.setEntity(getUrlEncodedFormEntity(paramMap));
        }
        
        return http(httpPost);
    }
    
    /**
     * http post请求(form形式)
     * 
     * @param url 请求url
     * @param paramMap 请求参数
     * @param headers 请求头
     * @return 响应字符串
     */
    public static String httpPostGBK(String url, Map<String, String> paramMap, Header... headers) {
        HttpPost httpPost = new HttpPost(url);
        if (ArrayUtils.isNotEmpty(headers)) {
            for (Header header : headers) {
                httpPost.setHeader(header);
            }
        }
        
        if (paramMap != null) {
            httpPost.setEntity(getUrlEncodedFormEntity(paramMap, "GBK"));
        }
        
        return http(HttpClients.createDefault(), httpPost, true, "GBK");
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
     * @param headers 请求头
     * @return 响应字符串
     */
    public static String httpsPost(String url, String param, Header... headers) {
        HttpPost httpPost = new HttpPost(url);
        if (ArrayUtils.isNotEmpty(headers)) {
            httpPost.setHeaders(headers);
        }
        httpPost.setEntity(new StringEntity(param, "UTF-8"));
        return http(getSSLHttpClient(), httpPost, true);
    }
    
    /**
     * https post请求(字符串形式)
     * 
     * @param url 请求url
     * @param param 请求参数
     * @param contentType contentType
     * @param headers 请求头
     * @return 响应字符串
     */
    public static String httpsPost(String url, String param, String contentType, Header... headers) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", contentType);
        httpPost.setHeader("Content-Type", contentType + ";charset=utf-8");
        if (ArrayUtils.isNotEmpty(headers)) {
            for (Header header : headers) {
                httpPost.setHeader(header);
            }
        }
        
        httpPost.setEntity(new StringEntity(param, "UTF-8"));
        return http(getSSLHttpClient(), httpPost, true);
    }
    
    /**
     * 双向认证模式：https post请求(字符串形式)
     * 
     * @param url 请求url
     * @param param 请求参数
     * @param contentType contentType
     * @param headers 请求头
     * @return 响应字符串
     */
    public static String httpsPostAuth(String url, String param, String contentType, Header... headers) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", contentType);
        httpPost.setHeader("Content-Type", contentType + ";charset=utf-8");
        if (ArrayUtils.isNotEmpty(headers)) {
            for (Header header : headers) {
                httpPost.setHeader(header);
            }
        }
        
        httpPost.setEntity(new StringEntity(param, "UTF-8"));
        return http(getSSLHttpClient(), httpPost, true);
    }
    
    /**
     * https post请求(form形式)
     * 
     * @param url 请求url
     * @param param 请求参数
     * @param headers 请求头
     * @return 响应字符串
     */
    public static String httpsPost(String url, Map<String, String> paramMap, Header... headers) {
        HttpPost httpPost = new HttpPost(url);
        if (ArrayUtils.isNotEmpty(headers)) {
            httpPost.setHeaders(headers);
        }
        if (paramMap != null) {
            httpPost.setEntity(getUrlEncodedFormEntity(paramMap));
        }
        
        return http(getSSLHttpClient(), httpPost, true);
    }
    
    /**
     * https post请求(form形式)
     * 
     * @param url 请求url
     * @param param 请求参数
     * @param contentType contentType
     * @param headers 请求头
     * @return 响应字符串
     */
    public static String httpsPostGBK(String url, Map<String, String> paramMap, String contentType, Header... headers) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", contentType);
        httpPost.setHeader("Content-Type", contentType + ";charset=GBK");
        if (ArrayUtils.isNotEmpty(headers)) {
            for (Header header : headers) {
                httpPost.setHeader(header);
            }
        }
        if (paramMap != null) {
            httpPost.setEntity(getUrlEncodedFormEntity(paramMap, "GBK"));
        }
        
        return http(getSSLHttpClient(), httpPost, true, "GBK");
    }
    
    /**
     * https post请求(form形式)
     * 
     * @param url 请求url
     * @param param 请求参数
     * @param contentType contentType
     * @param headers 请求头
     * @return 响应字符串
     */
    public static String httpsPost(String url, Map<String, String> paramMap, String contentType, Header... headers) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", contentType);
        httpPost.setHeader("Content-Type", contentType + ";charset=utf-8");
        if (ArrayUtils.isNotEmpty(headers)) {
            for (Header header : headers) {
                httpPost.setHeader(header);
            }
        }
        if (paramMap != null) {
            httpPost.setEntity(getUrlEncodedFormEntity(paramMap));
        }
        
        return http(getSSLHttpClient(), httpPost, true);
    }
    
    private static UrlEncodedFormEntity getUrlEncodedFormEntity(Map<String, String> paramMap, String charsat) {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Entry<String, String> entry : paramMap.entrySet()) {
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        try {
            return new UrlEncodedFormEntity(formparams, charsat);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("exception.httprequest-error", e);
        }
    }
    
    private static UrlEncodedFormEntity getUrlEncodedFormEntity(Map<String, String> paramMap) {
        return getUrlEncodedFormEntity(paramMap, "UTF-8");
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
    
    /**
     * 发送HTTP消息
     *
     * @param request 请求
     * @return 响应字符串
     */
    public static String http(HttpUriRequest request) {
        return http(HttpClients.createDefault(), request, true);
    }
    
    /**
     * 发送HTTP消息
     *
     * @param httpclient httpClient
     * @param request 请求
     * @param isFinishClose 是否关闭连接
     * @param charset 字符集
     * @return 响应字符串
     */
    public static String http(CloseableHttpClient httpclient, HttpUriRequest request, boolean isFinishClose, String charset) {
        CloseableHttpResponse response = null;
        StringBuilder builder = new StringBuilder();
        builder.append("sendToUrl: ").append(request.getURI()).append(System.getProperty("line.separator", "\n"));
        try {
            if (request instanceof HttpPost) {
                HttpPost httpPost = ((HttpPost)request);
                // 记录发送日志
                builder.append("parameter: ")
                    .append(StreamUtils.copyToString(httpPost.getEntity().getContent(), Charset.forName(charset)))
                    .append(System.getProperty("line.separator", "\n"));
            }
            response = httpclient.execute(request);
            String result = EntityUtils.toString(response.getEntity(), charset);
            // 记录响应日志
            builder.append("response:  ").append(result);
            log.info(builder.toString());
            return result;
        } catch (Exception e) {
            // 记录异常日志
            builder.append("response:  ").append(e);
            log.error(builder.toString());
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
                closeConnenction(httpclient);
            }
        }
    }
    
    /**
     * 发送HTTP消息
     *
     * @param httpclient httpClient
     * @param request 请求
     * @param isFinishClose 是否关闭连接
     * @return 响应字符串
     */
    public static String http(CloseableHttpClient httpclient, HttpUriRequest request, boolean isFinishClose) {
        return http(httpclient, request, isFinishClose, "UTF-8");
    }
    
    private static void closeConnenction(CloseableHttpClient httpclient) {
        try {
            httpclient.close();
        } catch (IOException e) {
            throw new RuntimeException("exception.httprequest-error", e);
        }
    }
}
