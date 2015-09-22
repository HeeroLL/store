/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * Administrator              New             Aug 8, 2013
 */
package com.zebone.register.util;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

@Component
public class XmlParser extends DefaultHandler {
	private static final Log log = LogFactory.getLog(XmlParser.class);
	int n = 0;//slot标记
	int m = 0;//document标记
	String key = "";//获取数据的键值
	Map<String, String> map = null;//解析xml流获取的相应的信息
    String joinStr = ""; //用来拼接Map的key值
	
	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		if(m == 1){
			if(n == 1){
				this.map.put(key, attributes.getValue("code"));
				n = 0;
			}
		}
		if("header".equals(name)){
			m = 1;
			this.map = new HashMap<String, String>();
		}

        if ("event".equals(name) || "author".equals(name) || "generate".equals(name)
                || "management".equals(name) || "service".equals(name)
                || "patient".equals(name) || "relevance".equals(name)) {
            joinStr = name.toUpperCase();
        }

		if("slot".equals(name)){
			n = 1;
			key = joinStr + "-" + attributes.getValue("code");
		}
	}

	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		
		if("header".equals(name)){
			m = 0;
		}
		
	}
	
	public Map<String, String> getParamInfo(String xml){
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			XmlParser dh = new XmlParser();
			
			InputSource is = new InputSource();
			StringReader sr = new StringReader(xml);
			is.setCharacterStream(sr);
			parser.parse(is , dh);//解析xml流
			return dh.map;
		}catch (Exception e) {
			log.error("解析xml出错",e);
			throw new RuntimeException("解析xml出错");
		}
	}
	
}
