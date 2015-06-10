package com.zebone.dnode.etl.adapter.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

import com.zebone.dnode.etl.adapter.dao.AdaptDocMapper;
import com.zebone.dnode.etl.adapter.dao.AdapterEtlMapper;
import com.zebone.dnode.etl.adapter.dao.AdapterMapper;
import com.zebone.dnode.etl.adapter.vo.AdaptDoc;
import com.zebone.dnode.etl.adapter.vo.DictInfo;
import com.zebone.dnode.etl.adapter.vo.DocConf;
import com.zebone.dnode.etl.adapter.vo.FieldConf;
import com.zebone.dnode.etl.adapter.vo.MainTableInfo;
import com.zebone.dnode.etl.adapter.vo.NodeColumnInfo;
import com.zebone.dnode.etl.adapter.vo.TableInfo;
import com.zebone.util.DateUtil;
import com.zebone.util.UUIDUtil;

/**
 * 表转换文档信息
 * @author: caixl
 * @date： 日期：Feb 20, 2014
 * @version 1.0
 */
@Service("adapterService")
@SuppressWarnings("unchecked")
public class AdapterServiceImpl implements AdapterService {

	private static final Log log = LogFactory.getLog(AdapterServiceImpl.class);
	@Resource
	private AdapterMapper adapterMapper;
	@Resource
	private AdapterEtlMapper adapterEtlMapper;
	@Resource
	private AdaptDocMapper adaptDocMapper;
	
	private static String orgCode = null;
	
	@Override
	public void tableToDocByDocXml(DocConf docConf,Map<String,String> mapPara) {
		orgCode = mapPara.get("orgCode");
		//String res = null;
		
		/*============获取文档中所有节点的相关信息=============*/
		List<NodeColumnInfo> list = adapterMapper.getListById(docConf.getId());
		
		if(list!=null &&list.size()>0){
			/*=====================获取主从表和关联字段相关信息======================*/
			List<TableInfo> tbInfos = adapterMapper.getTableInfosById(docConf.getId());
			if(tbInfos!=null && tbInfos.size()>0){
				StringBuffer sb = new StringBuffer("");
				String ids="";
				for(int i=0;i<tbInfos.size();i++){
					TableInfo info = tbInfos.get(i);
					sb.append("'").append(info.getTableId()).append("','").append(info.getTId()).append("',");
				}
				if(sb.length()>0) ids = sb.substring(0, sb.length()-1);
				
				List<TableInfo> tbInfo1s = adapterMapper.getTableNameByTId(ids);
				if(tbInfo1s==null){
					log.error("表名不存在");
				}
				
				for(TableInfo tableInfo:tbInfo1s){
					for(TableInfo info : tbInfos){
						if(info.getTId().equals(tableInfo.getTableId())){
							info.setTName(tableInfo.getTableName());
						}
						if(info.getTableId().equals(tableInfo.getTableId())){
							info.setTableName(tableInfo.getTableName());
						}
					}
				}
				
				/*==================主表SQL字段拼装===================*/
				StringBuilder colmn = new StringBuilder();
				StringBuilder colId = new StringBuilder();
				String tid = tbInfos.get(0).getTId();
				String tname = tbInfos.get(0).getTName();
				String keyName = tbInfos.get(0).getColId();
				String keycol = "";
				String keyCode = "";
				
				int js = 1;
				for(int j=0;j<list.size();j++){
					NodeColumnInfo columnInfo = list.get(j);
					if(tid.equals(columnInfo.getTableId())) {
						colId.append(columnInfo.getColumnId()).append(",");
						colmn.append(columnInfo.getColumnName()).append(" AS COL").append(js).append(",");
						js++;
					}
				}
				colId.deleteCharAt(colId.length()-1);
				colmn.append("DOC_NO AS docNo");
				//colmn.deleteCharAt(colmn.length()-1);
				
				/*===========获取主表数据信息=============*/
				List<Map<String, Object>> maindatas = adapterEtlMapper.getListByInfo(colmn.toString(),tname);
				
				
				if(maindatas!=null && maindatas.size()>0){
					for(Map<String, Object> maindata:maindatas){
						String res = getXmlByData(docConf, list, tbInfos, colId,tname, keyName, keycol, keyCode, maindata);
						if(StringUtils.isBlank(res)){
							continue;
						}
						AdaptDoc record = new AdaptDoc();
						record.setId(UUIDUtil.getUuid());
						record.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
						record.setDocXml(res);
						adaptDocMapper.insertSelective(record);
					}
				}
			}else{//没有子表的情况
				
				DictInfo dictInfo = adapterMapper.getMainTableInfo(list.get(0).getTableId());
				if(dictInfo!=null){
					StringBuilder columns = new StringBuilder();
					StringBuilder ids = new StringBuilder();
					
					DictInfo dictInfo2 = adapterMapper.getInfoBytId(list.get(0).getTableId());
					String keycol = dictInfo2.getDictName();
					String keyId = dictInfo2.getDictCode();
					String keyCode = "";
					for(int i=0;i<list.size();i++){
						NodeColumnInfo columnInfo = list.get(i);
						ids.append(columnInfo.getColumnId()).append(",");
						columns.append(columnInfo.getColumnName()).append(" AS COL").append(i+1).append(",");
					}
					ids.deleteCharAt(ids.length()-1);
					columns.append("DOC_NO AS docNo");
					//columns.deleteCharAt(columns.length()-1);
					
					List<Map<String, Object>> maindatas = adapterEtlMapper.getListByInfo(columns.toString(),dictInfo.getDictName());
					if(maindatas!=null){
						for(Map<String, Object> maindata:maindatas){
							getXmlBySimpleTableData(docConf, list, dictInfo,ids, keycol, keyId, keyCode, maindata);
						}
					}
				}else{
					log.error("不存在 ["+list.get(0).getTableId()+"] 此表标识！");
				}
			}
		}else{
			log.error("["+docConf.getDocName()+"] 不存在！");
		}
	}

	/*
	 *获取单表数据转换成文档流
	 *
	 */
	private void getXmlBySimpleTableData(DocConf docConf,
			List<NodeColumnInfo> list, DictInfo dictInfo, StringBuilder ids,
			String keycol, String keyId, String keyCode,
			Map<String, Object> maindata) {
		Map<String, Object> map = maindata;
		String[] colIds = ids.toString().split(",");
		for(int k=0;k<colIds.length;k++){
			for(NodeColumnInfo columnInfo:list){
				if(colIds[k].equals(columnInfo.getColumnId())){
					Object o = map.get("COL"+(k+1));
					columnInfo.setCode(o==null?"":o.toString());
					columnInfo = getDisplayNameByCode(columnInfo);//
					if(colIds[k].equals(keyId)){
						keyCode = columnInfo.getCode();
					}
				}
			}
		}

		String docNo = map.get("docNo")==null?"":map.get("docNo").toString();//文档编号
		
		Map<String, List<NodeColumnInfo>> datas = new HashMap<String, List<NodeColumnInfo>>();
		datas.put("mainTableData", list);
		
		MainTableInfo mainTableInfo = new MainTableInfo();
		mainTableInfo.setTname(dictInfo.getDictName());
		mainTableInfo.setTkey(keycol);
		mainTableInfo.setTvalue(keyCode);
		String res = getDataToDoc(docConf, docNo, datas, null,mainTableInfo);
	}

	/*
	 * 主从表，查询出来的数据转换成相应的文档流 
	 * 
	 */
	private String getXmlByData(DocConf docConf, List<NodeColumnInfo> list,
			List<TableInfo> tbInfos, StringBuilder colId, String tname,
			String keyName, String keycol, String keyCode,
			Map<String, Object> maindata) {
		String res;
		Map<String, List<NodeColumnInfo>> datas = null;
		Map<String, Integer> countdata = null;//每个子表查询的数据数量
		datas = new HashMap<String, List<NodeColumnInfo>>();
		List<NodeColumnInfo> mainInfo = new ArrayList<NodeColumnInfo>();
		Map<String, Object> map = maindata;
		String[] colIds = colId.toString().split(",");
		for(int k=0;k<colIds.length;k++){
			for(NodeColumnInfo columnInfo:list){
				if(colIds[k].equals(columnInfo.getColumnId())){
					Object o = map.get("COL"+(k+1));
					columnInfo.setCode(o==null?"":o.toString());
					
					columnInfo = getDisplayNameByCode(columnInfo);//
					
					mainInfo.add(columnInfo);
					
					if(keyName.equals(colIds[k])){//获取关联值
						keyCode = columnInfo.getCode();
						keycol = columnInfo.getColumnName();
					}
				}
			}
		}
		
		String docNo = map.get("docNo")==null?"":map.get("docNo").toString();//文档编号
		
		datas.put("mainTableData", mainInfo);//主表数据
		
		/*================获取子表数据=================*/
		countdata = new HashMap<String, Integer>();
		for(int a = 0;a<tbInfos.size();a++){//遍历所有子表
			String tableId = tbInfos.get(a).getTableId();
			String tableName = tbInfos.get(a).getTableName();
			String foreignKey = tbInfos.get(a).getColmn();
			StringBuilder subColumn = new StringBuilder();
			StringBuilder subColId = new StringBuilder();
			
			int js1 =1;
			for(int b=0;b<list.size();b++){
				NodeColumnInfo columnInfo = list.get(b);
				if(tableId.equals(columnInfo.getTableId())) {
					subColId.append(columnInfo.getColumnId()).append(",");
					subColumn.append(columnInfo.getColumnName()).append(" AS COL").append(js1).append(",");
					js1++;
				}
			}
			subColId.deleteCharAt(subColId.length()-1);
			subColumn.deleteCharAt(subColumn.length()-1);
			
			/*===========获取子表数据信息=============*/
			List<Map<String, Object>> subdatas = adapterEtlMapper.getSubListByInfo(subColumn.toString(),tableName,foreignKey,keyCode);
			if(subdatas!=null && subdatas.size()>0){
				countdata.put("subtable"+a, subdatas.size());
				
				for(int c = 0;c<subdatas.size();c++){
					List<NodeColumnInfo> subInfo = new ArrayList<NodeColumnInfo>();
					Map<String, Object> submap = subdatas.get(c);
					String[] subColIds = subColId.toString().split(",");
					
					for(int d=0;d<subColIds.length;d++){
						for(NodeColumnInfo columnInfo:list){
							if(subColIds[d].equals(columnInfo.getColumnId())){
								NodeColumnInfo columnInfo2 = new NodeColumnInfo();
								columnInfo2.setColumnId(columnInfo.getColumnId());
								columnInfo2.setColumnName(columnInfo.getColumnName());
								columnInfo2.setFieldId(columnInfo.getFieldId());
								columnInfo2.setPath(columnInfo.getPath());
								columnInfo2.setTableId(columnInfo.getTableId());
								Object o = submap.get("COL"+(d+1));
								columnInfo2.setCode(o==null?"":o.toString());
								
								getDisplayNameByCode(columnInfo2);//
								
								subInfo.add(columnInfo2);
							}
						}
					}
					
					datas.put("subTableData"+a+c, subInfo);
				}
			}else{
				countdata.put("subtable"+a, 0);
			}
		}
		MainTableInfo mainTableInfo = new MainTableInfo();
		mainTableInfo.setTname(tname);
		mainTableInfo.setTkey(keycol);
		mainTableInfo.setTvalue(keyCode);
		res = getDataToDoc(docConf, docNo, datas, countdata,mainTableInfo);
		return res;
	}
	
	/*================获取文档展示信息===================*/
	public NodeColumnInfo getDisplayNameByCode(NodeColumnInfo columnInfo){
		String fieldId = columnInfo.getFieldId();
		FieldConf fieldConf = adapterMapper.getFieldConfById(fieldId);
		if(fieldConf!=null){
			if("4".equals(fieldConf.getFieldType())){
				DictInfo dictInfo = adapterMapper.getDictByTypeIdAndCode(fieldConf.getFieldValue(),columnInfo.getCode());
				if(dictInfo!=null){
					columnInfo.setDisplayName(dictInfo.getDictName());
				}else{
					log.error(columnInfo.getColumnName()+": 标准中不存在编码["+columnInfo.getCode()+"]");
				}
			}else if("5".equals(fieldConf.getFieldType())){
				DictInfo dictInfo = adapterMapper.getTableInfoById(fieldConf.getFieldValue());
				if(dictInfo!=null){
					DictInfo dictInfo1 = adapterMapper.getMainNameByCode(dictInfo.getDictName(),columnInfo.getCode());
					if(dictInfo1!=null){
						columnInfo.setDisplayName(dictInfo1.getDictName());
					}else{
						log.error("["+fieldConf.getFieldValue()+"] 该标识主数据类型不存在编码 ["+columnInfo.getCode()+"]");
					}
				}else{
					log.error("["+fieldConf.getFieldValue()+"] 该标识主数据类型不存在");
				}
			}else{
				columnInfo.setDisplayName(columnInfo.getCode());
			}
		}else{
			log.error("不存在此标识元数据：["+fieldId+"]");
		}
		return columnInfo;
	}
	
	/**
	 * 将数据填充到XML文档
	 * @param docXml 文档模板
	 * @param docNo 文档编号
	 * @param datas 主从表数据
	 * @param countdata 子表的记录数量
	 * @return 
	 * String
	 */
	public String getDataToDoc(DocConf docConf,String docNo,Map<String, List<NodeColumnInfo>> datas,Map<String, Integer> countdata,MainTableInfo mainTableInfo){
		Document document = null;
		try {
			document = DocumentHelper.parseText(docConf.getDocXml());
			/********************************************文档体内容处理********************************************/
			/*========主表数据==========*/
			List<NodeColumnInfo> maindatas = datas.get("mainTableData");
			String xpath = maindatas.get(0).getPath();
			List mainEles = document.selectNodes(xpath.substring(0,xpath.indexOf("[@")));
			Iterator it = mainEles.iterator();
			while(it.hasNext()){
				Element ele = (Element) it.next();
				String code = ele.attributeValue("code");
				String name = ele.attributeValue("name");
				for(int i=0;i<maindatas.size();i++){
					NodeColumnInfo columnInfo = maindatas.get(i);
					String path = columnInfo.getPath();
					String datacode = path.substring(path.indexOf("='")+2,path.indexOf("']")).trim();
					StringBuilder sb = new StringBuilder(path);
					sb.delete(0, path.indexOf("']")+2);
					String dataname = sb.substring(sb.indexOf("='")+2, sb.indexOf("']")).trim();
					
					if(code.equals(datacode) && name.equals(dataname)){
						Element element = ele.element("value");
						Attribute attribute1 = element.attribute("code");
						Attribute attribute2 = element.attribute("displayName");
						attribute1.setValue(columnInfo.getCode());
						attribute2.setValue(columnInfo.getDisplayName());
					}
				}
			}
			/*===========主表数据============*/
			
			/*===========子表数据=============*/
			if(countdata==null){
				
			}else{
				for(int a=0;a<countdata.size();a++){//循环子表
					int n = countdata.get("subtable"+a);
					if(n==1){//子表的数据只有一条
						List<NodeColumnInfo> list = datas.get("subTableData"+a+0);
						String subXpath = list.get(0).getPath();
						List subEles = document.selectNodes(subXpath.substring(0,subXpath.indexOf("[@")));
						Iterator subIt = subEles.iterator();
						while(subIt.hasNext()){
							Element ele = (Element) subIt.next();
							String code = ele.attributeValue("code");
							String name = ele.attributeValue("name");
							for(int c=0;c<list.size();c++){
								NodeColumnInfo columnInfo = list.get(c);
								String path = columnInfo.getPath();
								String datacode = path.substring(path.indexOf("='")+2,path.indexOf("']")).trim();
								StringBuilder sb = new StringBuilder(path);
								sb.delete(0, path.indexOf("']")+2);
								String dataname = sb.substring(sb.indexOf("='")+2, sb.indexOf("']")).trim();
								if(code.equals(datacode) && name.equals(dataname)){
									Element element = ele.element("value");
									Attribute attribute1 = element.attribute("code");
									Attribute attribute2 = element.attribute("displayName");
									attribute1.setValue(columnInfo.getCode());
									attribute2.setValue(columnInfo.getDisplayName());
								}
							}
						}
					}else if(n>1){
						List<NodeColumnInfo> ss = datas.get("subTableData"+a+0);
						String bb = ss.get(0).getPath();
						List cc = document.selectNodes(bb.substring(0,xpath.indexOf("/slot")));
						Element eleml = (Element)cc.get(0);
						Element ele = eleml.getParent();
						for(int j=0;j<n-1;j++){
							Element element = eleml.createCopy();
							ele.add(element);
						}
						
						List subEles = document.selectNodes(bb.substring(0,xpath.indexOf("/slot")));
						
						for(int b=0;b<n;b++){//子表的数据有多条
							List<NodeColumnInfo> list = datas.get("subTableData"+a+b);
							Element element = (Element)subEles.get(b);
							List eles = element.elements();
							if(eles!=null && eles.size()>0){
								for(int c=0;c<eles.size();c++){
									Element eleSlot = (Element)eles.get(c);
									String code = eleSlot.attributeValue("code");
									String name = eleSlot.attributeValue("name");
									for(int d=0;d<list.size();d++){
										NodeColumnInfo columnInfo = list.get(d);
										String path = columnInfo.getPath();
										String datacode = path.substring(path.indexOf("='")+2,path.indexOf("']")).trim();
										StringBuilder sb = new StringBuilder(path);
										sb.delete(0, path.indexOf("']")+2);
										String dataname = sb.substring(sb.indexOf("='")+2, sb.indexOf("']")).trim();
										if(code.equals(datacode) && name.equals(dataname)){
											Element mmm = eleSlot.element("value");
											Attribute attribute1 = mmm.attribute("code");
											Attribute attribute2 = mmm.attribute("displayName");
											attribute1.setValue(columnInfo.getCode());
											attribute2.setValue(columnInfo.getDisplayName());
										}
									}
								}
							}
						}
					}
				}
			}
			/*===========子表数据=============*/
			/********************************************文档体内容处理********************************************/
			
			/********************************************文档头内容处理********************************************/

			Element eleDocNo = (Element)document.selectSingleNode("//ClinicalDocument/header/event/slot[@code='EX00.00.000.54']/value");
			Element eleUpType = (Element)document.selectSingleNode("//ClinicalDocument/header/generate/slot[@code='EX00.00.000.56']/value");
			Element docVersion = (Element)document.selectSingleNode("//ClinicalDocument/header/event/slot[@code='EX00.00.000.60']/value");
			Element docKind = (Element)document.selectSingleNode("//ClinicalDocument/header/event/slot[@code='EX00.00.000.55']/value");
			List<Element> docOrgCodeElement = (List<Element>)document.selectNodes("//ClinicalDocument/header/*/slot[@code='EX00.00.000.06']/value");
			
			
			if(StringUtils.isBlank(docNo)){
				String id = UUIDUtil.getUuid();
				mainTableInfo.setDocNo(id);
				eleDocNo.attribute("code").setValue(id);//文档编号生成规则
				eleDocNo.attribute("displayName").setValue(id);
				eleUpType.attribute("code").setValue("1");
				eleUpType.attribute("displayName").setValue("新建");
			}else{
				mainTableInfo.setDocNo(docNo);
				eleDocNo.attribute("code").setValue(docNo);
				eleDocNo.attribute("displayName").setValue(docNo);
				eleUpType.attribute("code").setValue("2");
				eleUpType.attribute("displayName").setValue("修改");
			}
			
			docVersion.attribute("code").setValue(docConf.getDocVersion());
			docVersion.attribute("displayName").setValue(docConf.getDocVersion());
			docKind.attribute("code").setValue(docConf.getDocName());
			docKind.attribute("displayName").setValue(docConf.getDocName());
			for(Element orgCodeEle : docOrgCodeElement){
				orgCodeEle.attribute("code").setValue(orgCode);
				orgCodeEle.attribute("displayName").setValue(orgCode);
			}
			
			
			/********************************************文档头内容处理********************************************/
			
			/*===========更新临时表状态=============*/
			adapterEtlMapper.updateTransformStatus(mainTableInfo);
						
			return document.asXML();
			
		} catch (DocumentException e) {
			log.error("", e);
			e.printStackTrace();
		}
		return null;
	}
}
