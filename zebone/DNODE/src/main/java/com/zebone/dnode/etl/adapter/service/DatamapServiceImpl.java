package com.zebone.dnode.etl.adapter.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.zebone.dnode.etl.adapter.dao.DatamapMapper;
import com.zebone.dnode.etl.adapter.vo.DictInfo;
import com.zebone.dnode.etl.adapter.vo.DictMap;
import com.zebone.dnode.etl.adapter.vo.FieldConf;

/**
 * 文档数据对照引擎实现
 * @date： 日期：Dec 25, 2013
 * @version 1.0
 */
@Service("datamapService")
public class DatamapServiceImpl implements DatamapService {

	private final static Log log = LogFactory.getLog(DatamapServiceImpl.class);
	
	@Resource
	private DatamapMapper datamapMapper;
	/**
	 * 文档数据对照
	 * @param docXml 
	 */
	@Override
	public String datamap(String docXml) {
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(docXml);
			Element root = doc.getRootElement();
			Element header = root.element("header");
			Element generate = header.element("generate");
			List list = generate.elements();
			
			String orgCode = "";//机构编码
			
			for(int i=0;i<list.size();i++){
				Element el = (Element)list.get(i);
				if("EX00.00.000.06".equals(el.attributeValue("code"))){
					Element ele = el.element("value");
					orgCode = ele.attributeValue("code");
				}
			}
			
			Element structuredBody = root.element("structuredBody");//文档体
			
			iteratorElement(structuredBody, orgCode);//遍历转换
			
			return doc.asXML();
		} catch (DocumentException e) {
			log.error("", e);
			e.printStackTrace();
		}catch (Exception e) {
			log.error("", e);
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * 遍历文档节点
	 */
	public void iteratorElement(Element element,String orgCode) throws Exception{
		List list = element.elements();
		for(int i=0;i<list.size();i++){
			Element ele = (Element)list.get(i);
			if("slot".equals(ele.getName())){
				transformElement(ele,orgCode);
			}else{
				iteratorElement(ele,orgCode);
			}
		}
	}
	
	/*
	 * 转换数据
	 */
	public void transformElement(Element element,String orgCode) throws Exception{
		String fieldCode = element.attributeValue("code");//元数据标识
		String fieldName = element.attributeValue("name");//元数据名称
		
		FieldConf fieldConf = datamapMapper.getByFieldCode(fieldCode);
		if(fieldConf == null){
			throw new Exception("["+fieldName+" " + fieldCode + "] 为不合法的元数据");
		}else{
			if("4".equals(fieldConf.getFieldType())){//数据字典
				List<DictMap> list = datamapMapper.getDictList(orgCode,fieldConf.getFieldValue());
				if(list!=null && list.size()>0){
					Element ele = element.element("value");
					boolean flag = false;
					for(int i=0;i<list.size();i++){
						DictMap dictMap = list.get(i);
						Attribute code = ele.attribute("code");
						if(code.getValue().trim().equals(dictMap.getDictCode())){
							DictInfo dictInfo = datamapMapper.getByDictId(dictMap.getDictId());
							
							code.setValue(dictInfo.getDictCode());
							ele.attribute("displayName").setValue(dictInfo.getDictName());
							flag = true;
							break;
						}
					}
					if(!flag) throw new Exception("["+fieldName+"] 字典型数据不合法");
				}else{
					throw new Exception("["+fieldName+"] 字典型数据在中心没有相关对照数据");
				}
			}else if("5".equals(fieldConf.getFieldType())){//主数据
				String tableName = datamapMapper.getByMdId(fieldConf.getFieldValue());//标准主数据表
				String orgTableName = tableName + "_M";//机构主数据表
				
				Element ele = element.element("value");
				Attribute code = ele.attribute("code");
				String mdId = "";
				try{
					mdId = datamapMapper.getMdIdByMd(orgTableName,orgCode,code.getValue().trim());
				}catch (Exception e) {
					throw new Exception("查询机构主数据表出现异常");
				}
				if(StringUtils.isBlank(mdId)){
					throw new Exception("["+fieldName+"] 主数据型数据没有相关对照数据");
				}else{
					DictInfo dictInfo = datamapMapper.getDictByMdId(tableName,mdId);
					if(dictInfo!=null){
						code.setValue(dictInfo.getDictCode());
						ele.attribute("displayName").setValue(dictInfo.getDictName());
					}
				}
			}
		}
	}
}
