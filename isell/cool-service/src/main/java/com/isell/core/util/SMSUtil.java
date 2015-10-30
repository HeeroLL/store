package com.isell.core.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.isell.core.pogo.TemplateSMS;

public class SMSUtil {
	
	public static String ip="api.ucpaas.com";
	public static String version="2014-06-30";
	
	public String getSignature(String accountSid, String authToken,String timestamp) throws Exception{
		String sig = accountSid + authToken + timestamp;
		String signature = DigestUtils.md5Hex(sig);
		return signature;
	}
	
	public StringBuffer getStringBuffer() {
		StringBuffer sb = new StringBuffer("https://");
		sb.append(ip);
		return sb;
	}
	
	public HttpResponse post(String cType,String accountSid,String authToken,String timestamp,String url,HttpClient httpclient,String body) throws Exception{
		HttpPost httppost = new HttpPost(url);
		httppost.setHeader("Accept", cType);
		httppost.setHeader("Content-Type", cType+";charset=utf-8");
		String src = accountSid + ":" + timestamp;
		String auth = Coder.encryptBASE64(src);
		httppost.setHeader("Authorization", auth);
		BasicHttpEntity requestBody = new BasicHttpEntity();
        requestBody.setContent(new ByteArrayInputStream(body.getBytes("UTF-8")));
        requestBody.setContentLength(body.getBytes("UTF-8").length);
        httppost.setEntity(requestBody);
        // 执行客户端请求
		HttpResponse response = httpclient.execute(httppost);
		return response;
	}
	
	/**
	 * 发送短信
	 * @param accountSid 
	 * @param authToken
	 * @param appId
	 * @param templateId
	 * @param to 手机号码
	 * @param param 发送内容
	 * @return String
	 */
	public String templateSMS(String accountSid, String authToken,
			String appId, String templateId, String to, String param) {
		String result = "";
		CloseableHttpClient client =HttpClientBuilder.create().build();
		try {
			// 构造请求URL内容
			String timestamp = DateUtil.dateToStr(new Date(),
					DateUtil.DATE_TIME_NO_SLASH);// 时间戳
			String signature =getSignature(accountSid,authToken,timestamp);
			String url = getStringBuffer().append("/").append(version)
					.append("/Accounts/").append(accountSid)
					.append("/Messages/templateSMS")
					.append("?sig=").append(signature.toUpperCase()).toString();
			System.out.println(signature);
			TemplateSMS templateSMS=new TemplateSMS();
			templateSMS.setAppId(appId);
			templateSMS.setTemplateId(templateId);
			templateSMS.setTo(to);
			templateSMS.setParam(param);
			//Gson gson = new Gson();
			//String body = gson.toJson(templateSMS);
			String body = JsonUtil.writeValueAsString(templateSMS);
			body="{\"templateSMS\":"+body+"}";
			System.out.println(body);
			HttpResponse response=post("application/json",accountSid, authToken, timestamp, url, client, body);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			EntityUtils.consume(entity);
			System.out.println("发送成功");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			// 关闭连接
			try {client.close();} catch (IOException e) {}
		}
		return result;
	}

	public static void main(String[] args) {
		new SMSUtil().templateSMS("20857dfa396b243bf35d8a49845110e3", "70973baed3589527c080758b5d44d312", 
				"c27c6161599048889a5e894d4c7e2765", "1194", "13961295157", "1234");
	}
	
	/**
	 * 发送短信<一句话功能简述><功能详细描述>
	 *
	 * @param templateId 模板id
	 * @param mobile 电话
	 * @param param 参数
	 * @return 是否成功
	 */
	public static String sendMsg(String templateId,String mobile,String param, String url){
	    TemplateSMS templateSMS = new TemplateSMS();
        templateSMS.setTemplateId(templateId);
        templateSMS.setTo(mobile);
        templateSMS.setParam(param);
        
        //Gson gson = new Gson();
        // 正式地址用AppConfig.SEND_SMS_URL
        //String result = HttpUtils.httpPost(AppConfig.SEND_SMS_URL, gson.toJson(templateSMS));
        String result = HttpUtils.httpPost(url, JsonUtil.writeValueAsString(templateSMS));
        if (StringUtils.isEmpty(result)) {
            return "发送失败";
        }else {
            return "发送成功";
        }
	}
}
