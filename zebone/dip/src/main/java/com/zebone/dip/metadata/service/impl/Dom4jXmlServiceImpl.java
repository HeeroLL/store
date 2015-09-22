package com.zebone.dip.metadata.service.impl;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import com.zebone.dip.metadata.service.Dom4jXmlService;
import com.zebone.dip.metadata.vo.MdNode;

@Service("dom4jXmlService")
public class Dom4jXmlServiceImpl implements Dom4jXmlService {

	private static final Logger logger = Logger.getLogger(Dom4jXmlServiceImpl.class);
	
	private static int nodeId = 0;

	@Override
	public List<MdNode> loadNodes(String xml) {
		// TODO Auto-generated method stub
		nodeId = 0;
		List<MdNode> mdNodeList = new ArrayList<MdNode>();
		SAXReader sr = new SAXReader();
		try {
			Document doc = sr.read(new ByteArrayInputStream(xml
					.getBytes(UTF8_CHARSET)));
			Element rootEle = doc.getRootElement();
			praseElement(rootEle, mdNodeList, null);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}
		
        return mdNodeList;
	}
    
	/**
	 * 
	 * @param el	      节点元素
	 * @param mdNodeList  节点列表 
	 * @param parentNode  父亲节点
	 * @author 陈阵 
	 * @date 2013-8-5 上午8:59:29
	 */
	@SuppressWarnings("rawtypes")
	private void praseElement(Element el, List<MdNode> mdNodeList, MdNode parentNode) {
		List els = el.elements();
		/** 此节点还有子节点 **/
		if (els != null && els.size() > 0) {
			MdNode mdNode = new MdNode();
			String eleName = el.getName();
			mdNode.setpNode(parentNode);
			mdNode.setLevel(getLevel(el));
			mdNode.setNodeEName(eleName);
			/** id用于 jquery zTree 生成树状结构 **/
			mdNode.setId(nodeId ++);
			
			if("slot".equals(eleName)){
				String mdNodeCName = el.attributeValue("name");
				mdNode.setNodeCName(mdNodeCName);
				/** <slot>节点标签 并且不是文档头中的节点才是数据元节点 **/
				if (el.getPath().indexOf("header") == -1) {		
					/** 数据元标识符  **/
					String mdNodeCode = el.attributeValue("code");
					mdNode.setNodeCode(mdNodeCode);
					mdNode.setFloor(true);
				}else{
					mdNode.setFloor(false);
				}
				/** 结点对应的层次  slot/username  username节点就在第二层 **/
				mdNode.setXpath(el.getPath()+"[@code='"+el.attributeValue("code")+"'][@name='"+el.attributeValue("name")+"']");
			}else{
				String xpath = el.getPath();
				mdNode.setNodeCName(eleName);
				mdNode.setXpath(xpath);			
				Attribute attr = el.attribute("name");
				if(attr != null){
					mdNode.setNodeCName(attr.getValue());
				}
			}
			if(el.getPath().indexOf("structuredBody") != -1){
				if(!"structuredBody".equals(el.getName())){
					mdNodeList.add(mdNode);
				}
			}
			for (int i = 0; i < els.size(); i++) {
				Element e = (Element) els.get(i);
				praseElement(e, mdNodeList,mdNode);
			}
		} else {
			
		}
	}
	
	/**
	 * 获取元素的层次
	 * @param el
	 * @return
	 * @author 陈阵 
	 * @date 2013-8-5 上午9:05:58
	 */
	private int getLevel(Element el){
          String path =  el.getPath();
          return StringUtils.split(path,"/").length;
	}

}
