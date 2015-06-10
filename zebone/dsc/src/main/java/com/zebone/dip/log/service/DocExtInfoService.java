package com.zebone.dip.log.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

import com.zebone.btp.core.util.Identities;
import com.zebone.dip.log.vo.DocExtendInfo;

/**
 * 类描述：xml流的加工处理
 * @author: caixl
 * @date： 日期：Sep 5, 2013
 * @version 1.0
 */

public class DocExtInfoService {
	
	private static Log log = LogFactory.getLog(DocExtInfoService.class);
	
	/**
	 * 方法描述: 给文档增加扩展信息
	 * @author caixl
	 * @date Sep 5, 2013
	 * @param xml
	 * @return 
	 * String
	 */
	public static String addExtInfo(String xml){
		String str = "";
		String comment = "\n<!-- EXT{\"uuid\":\""+Identities.uuid()+"\"} -->\n";//文档唯一标识
		Pattern p = Pattern.compile("<\\?xml.+\\?>");
		Matcher m = p.matcher(xml);
		if(m.find()){
			String g  = m.group()+comment;
			str = xml.replaceFirst("<\\?xml.+\\?>", g);
		}else{
			str = comment+xml;
		}
		return str;
	}
	/**
	 * 方法描述: 获取注释内容（获取标识数据）
	 * @author caixl
	 * @date Sep 5, 2013
	 * @param xml
	 * @return 
	 * String
	 */
	public static DocExtendInfo getExtInfo(String xml){
		BufferedReader br = new BufferedReader(new StringReader(xml));
		String sb = null;
		DocExtendInfo ro = null;
		try {
			
			br.readLine();//读取xml流第一行
			sb = br.readLine();//读取xml流注释（文档唯一标识）
			if(sb.length() > 0){
				String str = sb.replace("<!--", "").replace("-->", "").replace("EXT", "");
				
				ObjectMapper map = new ObjectMapper();
				ro = map.readValue(str, DocExtendInfo.class);
			}
		} catch (IOException e) {
			log.error("读取xml流异常", e);
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e1) {
				log.error("关闭xml流异常", e1);
				e1.printStackTrace();
			}
		}
		return ro;
	}
	
	public static void main(String[] args) {
		String xml = DocExtInfoService.addExtInfo("<?xml version=\"1.0\" encoding=\"UTF-8\"?><!-- sadasdad --><a>asdcasc</a><!-- sadasdad -->");
		System.out.println(xml);
		DocExtendInfo returnObject = DocExtInfoService.getExtInfo(xml);
		if(returnObject!=null){
			System.out.println(returnObject.getUuid());
		}
	}
}
