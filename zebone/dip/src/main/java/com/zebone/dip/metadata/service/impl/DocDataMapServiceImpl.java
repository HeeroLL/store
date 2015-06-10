package com.zebone.dip.metadata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zebone.dip.metadata.dao.DocConfMapper;
import com.zebone.dip.metadata.dao.DocMap2Mapper;
import com.zebone.dip.metadata.dao.DocMappingMapper;
import com.zebone.dip.metadata.dao.FieldColumnConfMapper;
import com.zebone.dip.metadata.dao.FieldConfMapper;
import com.zebone.dip.metadata.dao.FieldTableConfMapper;
import com.zebone.dip.metadata.service.DocDataMapService;
import com.zebone.dip.metadata.vo.AcResult;
import com.zebone.dip.metadata.vo.DocConf;
import com.zebone.dip.metadata.vo.DocDataMap;
import com.zebone.dip.metadata.vo.DocMap2;
import com.zebone.dip.metadata.vo.DocMapping;
import com.zebone.dip.metadata.vo.FDocMapping;
import com.zebone.dip.metadata.vo.FieldColumnConf;
import com.zebone.dip.metadata.vo.FieldConf;
import com.zebone.dip.metadata.vo.FieldTableConf;
import com.zebone.dip.metadata.vo.MdNode;
import com.zebone.util.JsonUtil;
import com.zebone.util.UUIDUtil;

@Service("docDataMapService")
public class DocDataMapServiceImpl implements DocDataMapService {
    
	private static final Logger logger = Logger.getLogger(DocDataMapServiceImpl.class);
	
	@Resource
	/** 文档配置 mapper**/
	private DocConfMapper docConfMapper;
	
	@Resource
	/** 文档数据映射 mapper **/
	private DocMappingMapper docMappingMapper;
	
	/** 文档数据外键映射  **/
	@Resource
	private DocMap2Mapper docMap2Mapper;
	
	@Resource
	/** 表配置  mapper**/
	private FieldTableConfMapper fieldTableConfMapper;
	
	@Resource
	/** 列配置 mapper  **/
	private FieldColumnConfMapper fieldColumnConfMapper;
	
	@Resource
	/** 数据源mapper **/
	private FieldConfMapper fieldConfMapper;
	
	@Override
	public List<DocConf> getAllDoc() {
		// TODO Auto-generated method stub
		List<DocConf> docConfList = docConfMapper.selectAll();
		return docConfList;
	}

	@Override
	public DocConf getDocById(String docId) {
		// TODO Auto-generated method stub
		return docConfMapper.selectDXByPrimaryKey(docId);
	}
	

	@Override
	public String loadNodes(List<MdNode> mdNodeList, String docId) {
		// TODO Auto-generated method stub
		List<DocDataMap> ddmList  = new ArrayList<DocDataMap>();

        for(MdNode node: mdNodeList){
        	DocDataMap ddm = new DocDataMap();
        	ddm.setNodeCName(node.getNodeCName());
        	ddm.setNodeEName(node.getNodeEName());
        	ddm.setId(node.getId());
        	ddm.setLevel(node.getLevel());
        	ddm.setFloor(node.isFloor());
        	if(node.getpNode() == null){
            	ddm.setpNodeCName("");
            	ddm.setpNodeEName("");
            	ddm.setPid(-1);
        	}else{
            	ddm.setpNodeCName("");
            	ddm.setpNodeEName(node.getpNode().getNodeEName());
            	ddm.setPid(node.getpNode().getId());
        	}
			if (node.isFloor()) {
				FieldConf fc = new FieldConf();
				fc.setFieldCode(node.getNodeCode());
				fc = fieldConfMapper.selectByFieldId(fc.getFieldCode());
						
				DocMapping docMapping = docMappingMapper.getDocMappingByDocIdXpath(docId, node.getXpath());
				/** 设置数据源 id **/
                ddm.setFieldId(fc.getId());
                /** 获取文档映射主键  **/
                ddm.setMappingId(docMapping.getId());
			}
        	ddmList.add(ddm);
        }
		String rStr = JsonUtil.writeValueAsString(ddmList);
        return rStr;
	}
	
	
	@Override
	public List<DocDataMap> loadNodesMap(List<MdNode> mdNodeList, String docId, int start) {
		// TODO Auto-generated method stub
		int idx_name = start;
		int idx_id = start;
		List<DocDataMap> ddmList  = new ArrayList<DocDataMap>();
		/** 将元数据对应的主键放入到node中 **/
	    for(MdNode node:mdNodeList){
	       if(node.isFloor()){
               FieldConf fc = new FieldConf();
	    	   fc.setFieldCode(node.getNodeCode());
	    	   fc = fieldConfMapper.selectByFieldId(fc.getFieldCode());
	    	   /** 设置数据源 id **/
	    	   node.setFieldId(fc.getId());
	       }
	    }
	    
        for(MdNode node: mdNodeList){
        	DocDataMap ddm = new DocDataMap();
        	ddm.setNodeCName(node.getNodeCName());
        	ddm.setNodeEName(node.getNodeEName());
        	ddm.setId(node.getId());
        	ddm.setLevel(node.getLevel());
        	ddm.setXpath(node.getXpath());
        	if(node.getpNode() == null){
            	ddm.setpNodeCName("");
            	ddm.setpNodeEName("");
            	ddm.setPid(-1);
        	}else{
            	ddm.setpNodeCName("");
            	ddm.setpNodeEName(node.getpNode().getNodeEName());
            	ddm.setPid(node.getpNode().getId());
        	}
        	/** 文档外键映射集合  **/
        	List<DocDataMap> docDataMapFkey = new ArrayList<DocDataMap>();
            if(node.isFloor()){  	
              	/** 根据数据源编码获取数据源主键id **/
            	FieldConf fc = new FieldConf();
            	fc.setFieldCode(node.getNodeCode());
            	/** 设置数据源对应主键id **/
            	ddm.setFieldId(node.getFieldId());
            	
                /** 获取文档数据映射  **/
            	DocMapping dm = new DocMapping();
            	dm.setDocId(docId);
            	dm.setFieldId(ddm.getFieldId());
            	dm.setXpath(ddm.getXpath());
            	dm = docMappingMapper.selectByDFC(dm);
            	
            	if(dm != null && dm.getCloumnId() != null){
                	/** 根据列id 获取表名列名  **/
                	FieldColumnConf fcc = fieldColumnConfMapper.selectByPrimaryKey(dm.getCloumnId());
                	ddm.setColId(fcc.getId());
                	ddm.setColName(fcc.getColumnDesc());
                	
                	FieldTableConf ftc = fieldTableConfMapper.selectByPrimaryKey(fcc.getTableId());
                	ddm.setTableId(ftc.getId());
                	ddm.setTableName(ftc.getTableDesc());
            	}

            	int ti = idx_name;
                String table = "<input type=\"text\" class=\"combo-text\" id=\"ddm_table_o_"+idx_id+"\" value=\""+StringUtils.defaultString(ddm.getTableName())+"\">";
                table +=  "<input type=\"hidden\"  id=\"ddm_table_"+idx_id+"\" value=\""+StringUtils.defaultString(ddm.getTableId())+"\">";
                String col = "<input type=\"text\" class=\"combo-text\" id=\"ddm_col_o_"+idx_id+"\" value=\""+StringUtils.defaultString(ddm.getColName())+"\">";
                col += "<input type=\"hidden\" id=\"ddm_col_"+idx_id+"\" name=\"ddm["+ ti +"].cloumnId\" value=\""+StringUtils.defaultString(ddm.getColId())+"\">";
                 
                String group = "<select name=\"ddm["+ ti +"].group\" id=\"ddm_group_"+idx_id+"\">" +
                		"<option value=\"1\" "+((dm != null) && (StringUtils.isNotBlank(dm.getGroupNo()) && "1".equals(dm.getGroupNo()))?"selected=\"selected\"":"")+">组一</option>"+
                		"<option value=\"2\" "+((dm != null) && (StringUtils.isNotBlank(dm.getGroupNo()) && "2".equals(dm.getGroupNo()))?"selected=\"selected\"":"")+">组二</option>"+
                		"<option value=\"3\" "+((dm != null) && (StringUtils.isNotBlank(dm.getGroupNo()) && "3".equals(dm.getGroupNo()))?"selected=\"selected\"":"")+">组三</option>"+
                		"<option value=\"4\" "+((dm != null) && (StringUtils.isNotBlank(dm.getGroupNo()) && "4".equals(dm.getGroupNo()))?"selected=\"selected\"":"")+">组四</option>"+
                		"<option value=\"5\" "+((dm != null) && (StringUtils.isNotBlank(dm.getGroupNo()) && "5".equals(dm.getGroupNo()))?"selected=\"selected\"":"")+">组五</option>"+
                		"</select>";
            	ddm.setTable(table);
            	ddm.setCol(col);
            	ddm.setFloor(true);
            	ddm.setGroup(group);
            	
                /** 查询文档数据外键映射  **/
            	if(dm != null){
            		List<DocMap2> dm2List = docMap2Mapper.selectBySortMappingId(dm.getId());
                	int rnum = 0;
                	if(dm2List != null && dm2List.size() >=0){
                		for(DocMap2 dm2 : dm2List){		
                			DocDataMap ddmFkey = new DocDataMap();
                			
                			ddmFkey.setFieldId(dm2.getFieldId());

                        	/** 根据列id 获取表名列名  **/
                        	FieldColumnConf rfcc = fieldColumnConfMapper.selectByPrimaryKey(dm2.getCloumnId());
                        	ddmFkey.setColId(rfcc.getId());
                        	ddmFkey.setColName(rfcc.getColumnDesc());
                        	
                        	FieldTableConf rftc = fieldTableConfMapper.selectByPrimaryKey(rfcc.getTableId());
                        	ddmFkey.setTableId(rftc.getId());
                        	ddmFkey.setTableName(rftc.getTableDesc());
                        	
                        	
                			String smpara = "ddmr_fi_o_"+ ti + "_" +rnum;
                			DocMapping docMapping = docMappingMapper.selectByPrimaryKey(dm2.getMappingId());
                			String xpath = docMapping.getXpath();
                			int lastIndex1 = xpath.lastIndexOf("\'");
                			int lastIndex2 = xpath.lastIndexOf("\'", lastIndex1-1) + 1;
                			String fkNodeName = xpath.substring(lastIndex2,lastIndex1);
                			String rpNode = "<input type=\"text\" id=\"ddmr_fi_o_"+ ti +"_"+rnum+"\" style=\"width:100px\" value=\""+ fkNodeName +"\"/>&nbsp;"+
             			            "<input type=\"hidden\" id=\"ddmr_fi_"+ti +"_"+rnum+"\" name=\"ddm["+ ti+"].dm2["+rnum+"].mappingId\" value=\""+StringUtils.defaultString(dm2.getMappingId())+"\">"+
            			            "<a id=\"menuBtn\" href=\"#\" onclick=\"javascript:showMenu(\'"+ smpara + "\');return false;\">选择</a>";
            			    
                			String rtable = "<input type=\"hidden\" type=\"text\" class=\"combo-text\" id=\"ddmr_table_"+ti +"_"+rnum +"\" value=\""+ StringUtils.defaultString(ddmFkey.getTableId()) +"\" />" +
                					 "<input type=\"text\" id=\"ddmr_table_o_"+ti +"_"+rnum +"\" value=\""+StringUtils.defaultString(ddmFkey.getTableName())+"\"/>";
                			String rcol = "<input type=\"hidden\" class=\"combo-text\" id=\"ddmr_col_"+ti +"_"+ rnum +"\" name=\"ddm["+ti+"].dm2["+rnum+"].cloumnId\"" +
                					" value=\""+StringUtils.defaultString(ddmFkey.getColId())+"\"/>"+
                					 "<input type=\"text\" id=\"ddmr_col_o_"+ ti +"_"+rnum +"\" value=\""+ StringUtils.defaultString(ddmFkey.getColName())+"\"/>";
                			String rope = "<a href=\"javascript:void(0);\" onclick=\"javascript:removeRow(this,event);\">删除</a>";
                			ddmFkey.setNodeCName("");
                			ddmFkey.setpNodeCName(rpNode);
                			ddmFkey.setTable(rtable);
                			ddmFkey.setCol(rcol);
                			ddmFkey.setOpe(rope);
                			docDataMapFkey.add(ddmFkey);
                			rnum++;
                		}
                	}
            	}
            	int ddm_row_num_n = docDataMapFkey==null?0:docDataMapFkey.size();
                String ope = "<input type=\"hidden\" name=\"ddm["+ ti +"].docId\" value=\""+StringUtils.defaultString(docId)+"\">"+
                		"<input type=\"hidden\" name=\"ddm["+ ti +"].fieldId\" value=\""+StringUtils.defaultString(ddm.getFieldId())+"\">"+
                		"<input type=\"hidden\" name=\"ddm["+ ti +"].xpath\" value=\""+StringUtils.defaultString(ddm.getXpath())+"\">"+
                		"<input type=\"hidden\" id=\"ddm_row_"+ti+"_n\" value=\""+ ddm_row_num_n +"\"/>"+
                		"<a href=\"javascript:void(0)\" onclick=\"javascript:addRow(this,event,"+ ti +");\">新增</a>";
            	ddm.setOpe(ope);
                idx_name++;            	
            }
  
            ddmList.add(ddm);
        	if(docDataMapFkey != null && docDataMapFkey.size() > 0){
        		ddmList.addAll(docDataMapFkey);
        	}
            idx_id++;
        }
        return ddmList;
	}
	
	
	public AcResult getTable(String tableDesc){
		 AcResult acr = new AcResult();
		 List<AcResult.AcData> acDataList = new ArrayList<AcResult.AcData>();
		 List<FieldTableConf> tableList = fieldTableConfMapper.selectByLikeTableDesc(tableDesc);
		 for(FieldTableConf ftc : tableList){
			 AcResult.AcData acData= new AcResult.AcData(ftc.getId(), ftc.getTableDesc());
			 acDataList.add(acData);
		 }
		 acr.setSuccess(true);
		 acr.setTotal(acDataList.size());
		 acr.setData(acDataList);
		 return acr;
	}
	
	public AcResult getCol(String tableId,String colDesc){
		 AcResult acr = new AcResult();
		 List<AcResult.AcData> acDataList = new ArrayList<AcResult.AcData>();
		 Map<String,String> par = new HashMap<String,String>();
		 par.put("tableId", tableId);
		 par.put("colDesc", colDesc);
		 List<FieldColumnConf> colList = fieldColumnConfMapper.selectLikeByColDesc(par);
		 for(FieldColumnConf fcc : colList){
			 AcResult.AcData acData= new AcResult.AcData(fcc.getId(), fcc.getColumnDesc());
			 acDataList.add(acData);
		 }
		 acr.setSuccess(true);
		 acr.setTotal(acDataList.size());
		 acr.setData(acDataList);
		 return acr;
	}
	
	public AcResult getAllCol(String tableId){
		 AcResult acr = new AcResult();
		 List<AcResult.AcData> acDataList = new ArrayList<AcResult.AcData>();
		 List<FieldColumnConf> colList = fieldColumnConfMapper.selectAllCol(tableId);
		 for(FieldColumnConf fcc : colList){
			 AcResult.AcData acData= new AcResult.AcData(fcc.getId(), fcc.getColumnDesc());
			 acDataList.add(acData);
		 }
		 acr.setSuccess(true);
		 acr.setTotal(acDataList.size());
		 acr.setData(acDataList);
		 return acr;
	}

	@Override
	public void saveDocDataMap(List<FDocMapping> fdmList) {
		// TODO Auto-generated method stub
		for(FDocMapping fdm :fdmList){
			if(StringUtils.isEmpty(fdm.getCloumnId())|| StringUtils.isEmpty(fdm.getFieldId())){
				continue;
			}
			/** 文档数据映射  **/
			DocMapping dm = new DocMapping();
			dm.setDocId(fdm.getDocId());
			dm.setFieldId(fdm.getFieldId());
			dm.setXpath(fdm.getXpath());
			dm.setCloumnId(fdm.getCloumnId());
			dm.setFunc(fdm.getFunc());
			dm.setGroupNo(fdm.getGroup());
	        
			DocMapping result = docMappingMapper.selectByDFC(dm);
			if(result == null){
				/** 没有映射元数据  插入数据  **/
				String id = UUIDUtil.getUuid();
				dm.setId(id);
				dm.setIsFeild(fdm.getIsFeild());
				dm.setIsSelect(fdm.getIsSelect());
				dm.setRepeat(fdm.getRepeat());
				docMappingMapper.insert(dm);
			}else{
				/** 直接更新记录  **/
				dm.setId(result.getId());
				docMappingMapper.updateByPrimaryKeySelective(dm);
			}
			
		
			List<DocMap2> dm2List = fdm.getDm2();
			docMap2Mapper.deleteBySortMappingId(dm.getId());
			if(dm2List != null && dm2List.size() > 0){
				/** 文档数据外键映射  **/
				for(DocMap2 dm2 : dm2List){
					   dm2.setId(UUIDUtil.getUuid());
					   dm2.setSortMappingId(result.getId());
					   dm2.setFunction(dm.getFunc());
					   /** 数据源id和列id不为空的时候才插入数据库 **/
					   if(StringUtils.isNotEmpty(dm2.getMappingId()) && StringUtils.isNotEmpty(dm2.getCloumnId())){
			               docMap2Mapper.insert(dm2);
					   }
				}
			}			
		}
	}

}
