package com.zebone.dip.metadata.service;

import java.nio.charset.Charset;
import java.util.List;

import com.zebone.dip.metadata.vo.MdNode;



/**
 * 解析xml的服务
 * @author 陈阵 
 * @date 2013-8-5 上午8:57:45
 */
public interface Dom4jXmlService {
	
	public static final Charset GBK_CHARSET = Charset.forName("GBK");
	
	public static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
	
	/**
	 * 解析xml生成节点对像
	 * @param xml
	 * @return
	 * @author 陈阵 
	 * @date 2013-8-5 上午8:58:09
	 */
	List<MdNode> loadNodes(String xml);


}
