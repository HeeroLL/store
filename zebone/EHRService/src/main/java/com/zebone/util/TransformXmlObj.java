package com.zebone.util;

import java.io.StringReader;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.zebone.dip.log.vo.Syslog;

/**
 * 类描述：xml与对象之间的转换
 * @author: caixl
 * @date： 日期：Sep 6, 2013
 * @version 1.0
 */

public class TransformXmlObj {
	/**
	 * 方法描述: 将对象转换成xml流
	 * @author caixl
	 * @date Sep 7, 2013
	 * @param obj 要转换的对象
	 * @param clazz xml中对应的对象的class
	 * @return 
	 * String
	 */
	public static String objToXml(Object obj,Class<?> clazz){
		XStream stream = getXmlStream(clazz);
		return stream.toXML(obj);
	}
	
	/**
	 * 方法描述: 将xml流转换对象
	 * @author caixl
	 * @date Sep 7, 2013
	 * @param xml xml流
	 * @param clazz 节点对应几个对象的class
	 * @return 
	 * Object
	 */
	public static Object xmlToObj(String xml,Class<?> clazz){
		XStream stream = getXmlStream(clazz);
		Object obj = null;
		obj = stream.fromXML(new StringReader(xml));
		return obj;
	}

	/**获取XStream对象实例*/
	private static XStream getXmlStream(Class<?> clazz) {
		XStream stream = new XStream(new DomDriver());
		stream.processAnnotations(clazz);
		return stream;
	}	
	
	/**
	 * 方法描述: 
	 * @author caixl
	 * @date Sep 6, 2013
	 * @param args 
	 * void
	 */
	public static void main(String[] args) {
		try {
			SAXReader sax = new SAXReader();
			Document doc = sax.read("f:/111.xml");
			if(doc!=null){
				Syslog syslog = (Syslog)TransformXmlObj.xmlToObj(doc.asXML(),Syslog.class);
				System.out.println(syslog.getDocNo()+"-"+syslog.getDoctype()+"-"+syslog.getError().getCode()+"-"+syslog.getCategory());
				String xml = TransformXmlObj.objToXml(syslog,Syslog.class);
				System.out.println(xml);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
