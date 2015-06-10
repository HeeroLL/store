/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Jun 29, 2013		文档管理业务实现层
 */
package com.zebone.dip.metadata.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.metadata.dao.DataSetConfMapper;
import com.zebone.dip.metadata.dao.DocConfMapper;
import com.zebone.dip.metadata.dao.DocMap2Mapper;
import com.zebone.dip.metadata.dao.DocMappingMapper;
import com.zebone.dip.metadata.dao.FieldConfMapper;
import com.zebone.dip.metadata.service.DocumentService;
import com.zebone.dip.metadata.vo.DocConf;
import com.zebone.dip.metadata.vo.DocMapping;
import com.zebone.dip.metadata.vo.DocTreeInfo;
import com.zebone.dip.metadata.vo.FieldConf;
import com.zebone.dip.metadata.vo.TreeInfo;
import com.zebone.util.UUIDUtil;
@Service("documentService")
public class DocumentServiceImpl implements DocumentService {

	@Resource
	private DocConfMapper docConfMapper;
	
	@Resource
	private DataSetConfMapper dataSetConfMapper;
	
	@Resource
	private FieldConfMapper fieldConfMapper;
	
	@Resource
	private DocMappingMapper docMappingMapper;
	
	@Resource
	private DocMap2Mapper docMap2Mapper;
	
	/**
	 * @author caixl
	 * @date Jun 29, 2013
	 * @description TODO 文档列表信息查询
	 * @param page
	 * @param docConf 
	 * @return Pagination<DocConf>
	 */
	@Override
	public Pagination<DocConf> docvInfoPage(Pagination<DocConf> page,
			DocConf docConf) {
		List<DocConf> list = docConfMapper.docvInfoList(page.getRowBounds(),docConf);
		int totalCount = docConfMapper.docvInfoCount(docConf);
		page.setData(list);
		page.setTotalCount(totalCount);
		return page;
	}

	/**
	 * @author caixl
	 * @date Jul 1, 2013
	 * @description TODO 保存文档基本信息
	 * @param docConf
	 * @return int
	 */
	@Override
	public int saveDocvInfo(DocConf docConf) {
		int res = docConfMapper.insertSelective(docConf);
		if(res > 0) return 1;
		return 0;
	}

	/**
	 * @author caixl
	 * @date Jul 1, 2013
	 * @description TODO 更新文档基本信息
	 * @param docConf
	 * @return int
	 */
	@Override
	public int updateDocvInfo(DocConf docConf) {
		int res = docConfMapper.updateByPrimaryKey(docConf);
		if(res > 0)return 1;
		return 0;
	}

	/**
	 * @author caixl
	 * @date Jul 1, 2013
	 * @description TODO 删除相关文档信息
	 * @param ids
	 * @return int
	 */
	@Override
	public int removeDocvInfo(String[] ids) {
		List<String> list = new ArrayList<String>();
		for(String id : ids){
			int count = docMappingMapper.getCountDocMapByDocId(id);
			if(count > 0){
				return 2;
			}else{
				list.add(id);
			}
		}
		if(list!=null && list.size()>0){
			int res = docConfMapper.removeDocvInfo(list);
			if(res > 0) return 1;
		}else{
			return 2;
		}
		return 0;
	}

	/**
	 * @author caixl
	 * @date Jul 1, 2013
	 * @description TODO 加载文档基础信息
	 * @param id
	 * @return DocConf
	 */
	@Override
	public DocConf loadDocvInfoById(String id) {
		return docConfMapper.selectByPrimaryKey(id);
	}

	/**
	 * @author caixl
	 * @date Jul 2, 2013
	 * @description TODO 根据文档id，获取一个文档的层级结构的对象
	 * @param id
	 * @return DocTreeInfo
	 */
	@Override
	public DocTreeInfo getInfoById(String id) {
		DocTreeInfo docTreeInfo = null;
		DocConf doc = docConfMapper.getDocConfById(id);
		if(StringUtils.isEmpty(doc.getDocXml())){
			//最原始的xml模版
			docTreeInfo = getDocTreeInfo(id);
		}else{
			//数据库中设置好的xml模版
			docTreeInfo = getDocTreeInfoByXML(doc);
		}
		
		return docTreeInfo;
	}

	/**
	  * @Title: getDocTreeInfoByXML 
	  * @Description: 获取已经设置好的xml模版信息
	  * @author LinBin
	  * @param doc
	  * @return
	  * @throws
	 */
	public DocTreeInfo getDocTreeInfoByXML(DocConf doc){
		Document docu = null;
		DocTreeInfo docTreeInfo = new DocTreeInfo();
		docTreeInfo.setDocId(doc.getId());
		List<TreeInfo> list = docMappingMapper.getInfoByDoc(doc.getId());
		List<TreeInfo> treeInfos = new ArrayList<TreeInfo>();
		try {
			docu = DocumentHelper.parseText(doc.getDocXml());
			Element root = docu.getRootElement();
			parseElementXML(root,treeInfos,list,null);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		docTreeInfo.setTreeInfos(treeInfos);
		return docTreeInfo;
	}
	
	public void parseElementXML(Element el,List<TreeInfo> treeInfos,List<TreeInfo> list,TreeInfo treeInfo){
		List eles = el.elements();
		TreeInfo t = null;
		if(el.attribute("code") != null){
			String xpath = el.getPath()+"[@code='"+el.attributeValue("code")+"'][@name='"+el.attributeValue("name")+"']";
			for(TreeInfo map : list){
				if(map.getXpath().equals(xpath) 
						&& "1".equals(map.getIsFeild()) && map.getFieldCode().equals(el.attributeValue("code"))){
					map.setName(el.attributeValue("name"));
					if(treeInfo!=null){
						map.setPid(treeInfo.getId());
					}else{
						map.setPid("");
					}
					t = map;
					treeInfos.add(map);
				}
			}
		}else{
			for(TreeInfo map : list){
				if(map.getXpath().equals(el.getPath()) && map.getFieldId().equals(el.getName())){
					map.setName(el.attributeValue("name"));
					if(treeInfo!=null){
						map.setPid(treeInfo.getId());
					}else{
						map.setPid("");
					}
					t = map;
					treeInfos.add(map);
				}
			}
		}
		if(eles !=null && eles.size()>0){
			for(int i = 0; i < eles.size(); i++){
				Element ele = (Element)eles.get(i);
				if(!"value".equals(ele.getName())){
					parseElementXML(ele,treeInfos,list,t);
				}
			}
		}
	}
	
	/**
	  * @Title: getDocTreeInfo 
	  * @Description: 获取最原始的xml模版信息
	  * @author LinBin
	  * @param id
	  * @return
	  * @throws
	 */
	public DocTreeInfo getDocTreeInfo(String id){
		DocTreeInfo docTreeInfo = new DocTreeInfo();
		docTreeInfo.setDocId(id);
		List<TreeInfo> list = new ArrayList<TreeInfo>();
		SAXReader sr = new SAXReader();
		try {
			//Document doc = sr.read(ClassLoader.getSystemResourceAsStream("doc/doc_model.xml"));
			ClassPathResource resource = new ClassPathResource("doc/doc_model.xml");
			//Document doc = sr.read(DocumentServiceImpl.class.getResourceAsStream("doc/doc_model.xml"));
			Document doc = sr.read(resource.getInputStream());
			Element root = doc.getRootElement();
			praseElement(root,list,null);
			docTreeInfo.setTreeInfos(list);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return docTreeInfo;
	}
	
	/**
	  * @Title: praseElement 
	  * @Description: 获取节点树相关信息
	  * @author LinBin
	  * @param el
	  * @param list
	  * @param treeInfo
	  * @throws
	 */
	@SuppressWarnings("rawtypes")
	private void praseElement(Element el,List<TreeInfo> list,TreeInfo treeInfo){
		List eles = el.elements();
		TreeInfo t = new TreeInfo();
		t.setName(el.attributeValue("name"));
		if(el.attribute("code") != null){
			t.setIsFeild("1");
			String fieldId = fieldConfMapper.getFieldIdByFieldCode(el.attributeValue("code"));
			if(StringUtils.isEmpty(fieldId)){
				t.setFieldId(el.attributeValue("code"));
			}else{
				t.setFieldId(fieldId);
			}
			t.setFieldCode(el.attributeValue("code"));
			t.setXpath(el.getPath()+"[@code='"+t.getFieldCode()+"'][@name='"+t.getName()+"']");
		}else{
			t.setIsFeild("0");
			t.setFieldId(el.getName());
			t.setXpath(el.getPath());
		}
		
		t.setId(UUIDUtil.getUuid());
		if(treeInfo!=null){
			t.setPid(treeInfo.getId());
		}else{
			t.setPid("");
		}
		list.add(t);
		if (eles != null && eles.size() > 0) {
			for(int i = 0; i < eles.size(); i++){
				Element e = (Element) eles.get(i);
				if(!"value".equals(e.getName())){
					praseElement(e, list,t);
				}
			}
		}
	}

	/**
	 * @author caixl
	 * @date Jul 2, 2013
	 * @description TODO 获取数据集名称信息
	 * @return List<String>
	 */
	@Override
	public List<String> getDatasetNames() {
		List<String> list =  dataSetConfMapper.getDatasetNames();
		return list;
	}

	/**
	 * @author caixl
	 * @param dataSetConf 
	 * @param page 
	 * @date Jul 2, 2013
	 * @description TODO 获取某元数据及下的元数据列表信息
	 * @return Pagination<FieldConf>
	 */
	@Override
	public Pagination<FieldConf> metadataListBydataset(Pagination<FieldConf> page, FieldConf dataSetConf) {
		List<FieldConf> list = fieldConfMapper.metadataListBydataset(page.getRowBounds(),dataSetConf);
		int totalCount = fieldConfMapper.metadataTotalCountBydataset(dataSetConf);
		page.setData(list);
		page.setTotalCount(totalCount);
		return page;
	}

	/**
	 * @author caixl
	 * @date Jul 3, 2013
	 * @description TODO 将文档与数据源的绑定存入库中
	 * @param docTreeInfo
	 * @return int
	 */
	@Override
	public int saveDocMapping(DocTreeInfo docTreeInfo) {
		//已删除的节点映射id
		String deleteTreeNodesId = docTreeInfo.getDeleteTreeNodesId();
		if(deleteTreeNodesId!=null&&!deleteTreeNodesId.equals("")){
			String[] ids = deleteTreeNodesId.split(",");
			for(String mapid : ids){
				//删除外键映射
				docMap2Mapper.deleteByMappingId(mapid);
				//删除节点文档映射信息
				docMappingMapper.deleteByPrimaryKey(mapid);
			}
		}
		//删除文档绑定元数据的相关信息
//		List<String> mapIds = docMappingMapper.getMapIdsByDocId(docTreeInfo.getDocId());
//		if(mapIds != null && mapIds.size()>0){
//			for(int i=0;i<mapIds.size();i++) docMap2Mapper.deleteByMappingId(mapIds.get(0));
//		}
//		int res = docMappingMapper.deleteByDocId(docTreeInfo.getDocId());
//		if(res < 0) return 0;
		
		
		List<TreeInfo> treeInfos = docTreeInfo.getTreeInfos();
		List<DocMapping> addList = new ArrayList<DocMapping>();
		List<DocMapping> updateList = new ArrayList<DocMapping>();
		//映射关系批量插入（有条件）
		List<String> mapIds = docMappingMapper.getMapIdsByDocId(docTreeInfo.getDocId());
		if(mapIds != null && mapIds.size()>0){
			//如果mapIds有数据，说明是更新文档
			for(TreeInfo treeInfo : treeInfos){
				DocMapping map = new DocMapping();
				map.setDocId(docTreeInfo.getDocId());
				map.setFieldId(treeInfo.getFieldId());
				map.setIsFeild(treeInfo.getIsFeild());
				map.setIsSelect(treeInfo.getIsSelect());
				map.setRepeat(treeInfo.getRepeat());
				map.setXpath(treeInfo.getXpath());
				map.setIsOnly(treeInfo.getIsOnly());
				if(treeInfo.getId()!=null&&treeInfo.getId()!=""){
					DocMapping temp = docMappingMapper.selectByPrimaryKey(treeInfo.getId());
					if(temp==null){
						map.setId(UUIDUtil.getUuid());
						addList.add(map);
					}else{
						map.setId(treeInfo.getId());
						updateList.add(map);
					}
				}else{
					map.setId(UUIDUtil.getUuid());
					addList.add(map);
				}
			}
		}else{
			//如果mapIds没数据，说明是第一次保存文档，保存所有数据
			for(TreeInfo treeInfo : treeInfos){
				DocMapping map = new DocMapping();
				map.setDocId(docTreeInfo.getDocId());
				map.setFieldId(treeInfo.getFieldId());
				map.setIsFeild(treeInfo.getIsFeild());
				map.setIsSelect(treeInfo.getIsSelect());
				map.setRepeat(treeInfo.getRepeat());
				map.setXpath(treeInfo.getXpath());
				map.setIsOnly(treeInfo.getIsOnly());
				map.setId(UUIDUtil.getUuid());
				addList.add(map);
			}
		}
		
		if(addList.size()>0){
			docMappingMapper.insertBatch(addList);
		}
		if(updateList.size()>0){
			for(DocMapping obj : updateList){
				docMappingMapper.updateMappingByPrimaryKey(obj);
			}
		}
		
		//拼装xml格式数据流
		String xml = getXMLByTreeInfo(docTreeInfo.getTreeInfos());
		docConfMapper.saveDocXmlById(docTreeInfo.getDocId(),xml);
		
		return 1;
	}

	private String getXMLByTreeInfo(List<TreeInfo> treeInfos) {
		Document doc = DocumentHelper.createDocument();
		Element root = null;
		String str = "";
		for(TreeInfo treeInfo:treeInfos){
			if(StringUtils.isEmpty(treeInfo.getPid()) || "null".equals(treeInfo.getPid())){
				root = doc.addElement(treeInfo.getFieldId());
				root.addAttribute("name", treeInfo.getName());
				str = treeInfo.getId(); 
			}
		}
		parseListDoc(root,treeInfos,str);
		//System.out.println(doc.asXML());
		return doc.asXML();
	}
	
	public void parseListDoc(Element el,List<TreeInfo> treeInfos,String pid){
		for(int i=0;i<treeInfos.size();i++){
			TreeInfo treeInfo = treeInfos.get(i);
			if(pid.equals(treeInfo.getPid())){
				Element ele = null;
				if("1".equals(treeInfo.getIsFeild())){
					ele = el.addElement("slot");
					ele.addAttribute("name", treeInfo.getName());
					ele.addAttribute("code", treeInfo.getFieldCode());
					
					Element value = ele.addElement("value");
					value.addAttribute("code", "");
					value.addAttribute("displayName", "");
				}else{
					ele = el.addElement(treeInfo.getFieldId());
					ele.addAttribute("name", treeInfo.getName());
					parseListDoc(ele,treeInfos,treeInfo.getId());
				}
			}
		}
	}

	/**
	 * @author caixl
	 * @date Jul 4, 2013
	 * @description TODO 判断文档是否绑定元数据
	 * @param id
	 * @return int
	 */
	@Override
	public int isExistXMLById(String id) {
		return docConfMapper.getCountById(id);
	}

	/**
	 * @author caixl
	 * @date Jul 30, 2013
	 * @description TODO 验证文档是否重复
	 * @param docConf
	 * @return int
	 */
	@Override
	public int validateDocInfo(DocConf docConf) {
		return docConfMapper.validateDocInfo(docConf);
	}

    @Override
    public int isDocMapping(String id) {
        return docConfMapper.countMappingInfById(id);
    }
    
    @Override
    public int updateDocConfByIdSelective(DocConf docConf){
    	return docConfMapper.updateByPrimaryKeySelective(docConf);
    }
}
