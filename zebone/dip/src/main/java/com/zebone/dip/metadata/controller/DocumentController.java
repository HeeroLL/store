/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Jun 28, 2013		文档管理的控制层
 */
package com.zebone.dip.metadata.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.app.dict.pojo.Dictionary;
import com.zebone.btp.app.dict.service.DictionaryService;
import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.dictionary.service.DipDictionaryService;
import com.zebone.dip.md.vo.NameCode;
import com.zebone.dip.metadata.service.DocumentService;
import com.zebone.dip.metadata.vo.DocConf;
import com.zebone.dip.metadata.vo.DocTreeInfo;
import com.zebone.dip.metadata.vo.FieldConf;
import com.zebone.dip.metadata.vo.TreeInfo;
import com.zebone.util.DateUtil;
import com.zebone.util.Encodes;
import com.zebone.util.EncodingTool;
import com.zebone.util.JsonUtil;
import com.zebone.util.UUIDUtil;

@Controller
public class DocumentController {
	
	@Resource
	private DocumentService documentService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private DipDictionaryService dipDictionaryService;
	
	/**
	  * @Title: docvIndex 
	  * @Description: 文档模型管理主页面（查询页面）
	  * @author LinBin
	  * @param map
	  * @return
	  * @throws
	 */
	@RequestMapping("metadata/docvIndex")
	public String docvIndex(ModelMap map){
		List<NameCode> type = dipDictionaryService.getTypeByCode("EXV00.00.026");
		map.put("docTypeCode", type);
		return "dip/metadata/doc_version_index";
	}
	
	/**
	 * 
	 * @Description: 注册模型首页
	 * @param  String
	 * @return String
	 * @throws 
	 *
	 * @author wangpeng
	 * @date 2015年5月30日
	 */
	@RequestMapping("metadata/regvIndex")
	public String regvIndex(ModelMap map){
		List<NameCode> versionType = dipDictionaryService.getTypeByCode("EXV00.00.077");
		map.put("versionType", versionType);
		return "dip/metadata/register_version_index";
	}
	
	/**
	  * @Title: docvStructor 
	  * @Description: 跳转文档结构页面
	  * @author LinBin
	  * @param id
	  * @param name
	  * @param map
	  * @return
	  * @throws
	 */
	@RequestMapping("metadata/docvStructor")
	public String docvStructor(@RequestParam("id")String id,@RequestParam("name")String name,ModelMap map){
		DocTreeInfo doctree = documentService.getInfoById(id);
		List<Dictionary> versions = dictionaryService.getDictionaryByTypeCode("datasetversion");
		List<String> list = documentService.getDatasetNames();
        List<Dictionary> standardList = dictionaryService.getDictionaryByTypeCode("standorgan");
        //文档是否已存在数据映射关系（现在改成不需要判断，修改人林彬）
//        int result = documentService.isDocMapping(id);
//        map.put("docMapping", result > 0 ? true : false);
        for(TreeInfo info:doctree.getTreeInfos()){
        	if("ClinicalDocument".equals(info.getFieldId())){
        		info.setName(name);
        	}
        }
		String str = JsonUtil.writeValueAsString(doctree.getTreeInfos());
		map.put("docId", doctree.getDocId());
		map.put("list", str);
		map.put("dataset", list);
		map.put("versions", versions);
        map.put("standardList", standardList);
		map.put("docName", name);
		return "dip/metadata/doc_version";
	}
	
	/**
	  * @Title: docvStructor 
	  * @Description: 重新加载文档树的时候，调用
	  * @author LinBin
	  * @param id
	  * @param name
	  * @param map
	  * @return
	  * @throws
	 */
	@RequestMapping("metadata/getDocById")
	@ResponseBody
	public Map<String, Object> docvStructor(String id,String docName){
		DocTreeInfo doctree = documentService.getInfoById(id);
        for(TreeInfo info:doctree.getTreeInfos()){
	       	if("ClinicalDocument".equals(info.getFieldId())){
	       		info.setName(docName);
	       	}
        }
        Map<String, Object> map= new HashMap<String, Object>();
		String str = JsonUtil.writeValueAsString(doctree.getTreeInfos());
		map.put("list", str);
		map.put("success", true);
        return map;
	}
	
	@RequestMapping("metadata/docvListInfo")
	@ResponseBody
	public JsonGrid docvListInfo(DocConf docConf,Pagination<DocConf> page){
        if (docConf.getDocName() != null && !"".equals(docConf.getDocName())) {
            String docName = Encodes.urlDecode(docConf.getDocName());
            docConf.setDocName(EncodingTool.escapeStr(docName));
        }
        docConf.setModelType("1");
        Pagination<DocConf> p = documentService.docvInfoPage(page,docConf);
		JsonGrid jGrid = new JsonGrid("true",p.getTotalCount(),p.getData());
		return jGrid;
	}
	
	/**
	 * 
	 * @Description: 注册模型 
	 * @param  JsonGrid
	 * @return JsonGrid
	 * @throws 
	 *
	 * @author wangpeng
	 * @date 2015年6月2日
	 */
	@RequestMapping("metadata/registervListInfo")
	@ResponseBody
	public JsonGrid registervListInfo(DocConf docConf,Pagination<DocConf> page){
        if (docConf.getDocName() != null && !"".equals(docConf.getDocName())) {
            String docName = Encodes.urlDecode(docConf.getDocName());
            docConf.setDocName(EncodingTool.escapeStr(docName));
        }
        docConf.setModelType("2");
        Pagination<DocConf> p = documentService.docvInfoPage(page,docConf);
		JsonGrid jGrid = new JsonGrid("true",p.getTotalCount(),p.getData());
		return jGrid;
	}
	
	/**
	 * 
	 * @Description: 保存文档/实体
	 * @param  docConf
	 * @return Map<String,Object>
	 * @throws 
	 *
	 * @author wangpeng
	 * @date 2015年6月3日
	 */
	@RequestMapping("metadata/docvInfoSave")
	@ResponseBody
	public Map<String, Object> docvSaveInfo(DocConf docConf){
		int result = 0;
		if(StringUtils.isEmpty(docConf.getId())){
			docConf.setId(UUIDUtil.getUuid());
			docConf.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
			result = documentService.saveDocvInfo(docConf);
		}else{
			result = documentService.updateDocvInfo(docConf);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		//返回信息
		map.put("success", result==1?true:false);
		map.put("id", docConf.getId());
		return map;
	}
	
	@RequestMapping("metadata/docvInfoRemove")
	@ResponseBody
	public Map<String, Object> docvInfoRemove(@RequestParam("id")String id,@RequestParam("modelType")String modelType){
		int result = documentService.removeDocvInfo(id.split(","));
		String msg = "";
		if("2".equals(modelType)){
			if(result == 0){
				msg = "实体删除失败";
			}else if(result == 2){
				msg = "实体被使用，不能维护";
			}else{
				msg = "实体删除成功";
			}
		}else{
			if(result == 0){
				msg = "文档删除失败";
			}else if(result == 2){
				msg = "文档被使用，不能维护";
			}else{
				msg = "文档删除成功";
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", result == 1?true:false);
		map.put("msg", msg);
		return map;
	}
	
	@RequestMapping("metadata/docvInfoLoad")
	@ResponseBody
	public Map<String, Object> docvInfoLoad(@RequestParam("id")String id){
		DocConf doc = documentService.loadDocvInfoById(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", doc);
		return map;
	}
	
	@RequestMapping("metadata/metadataListBydatasetId")
	@ResponseBody
	public JsonGrid metadataListBydataset(FieldConf fieldConf,Pagination<FieldConf> page){
//        if(dataSetConf.getDatasetName()!=null && !"".equals(dataSetConf.getDatasetName())){
//            dataSetConf.setDatasetName(Encodes.urlDecode(dataSetConf.getDatasetName()));
//        }
		Pagination<FieldConf> p = documentService.metadataListBydataset(page,fieldConf);
		JsonGrid jGrid = new JsonGrid("true",p.getTotalCount(),p.getData());
		return jGrid;
	}
	
	@RequestMapping("metadata/docMappingSave")
	@ResponseBody
	public Map<String, Object> docMappingSave(DocTreeInfo docTreeInfo){
		int result = 0;
		result = documentService.saveDocMapping(docTreeInfo);
			
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", result==1?true:false);
		return map;
	}
	
	/**
	 * 
	 * @Description: 判断文档/实体是否被使用
	 * @param  id
	 * @return Map<String,Object>
	 * @throws 
	 *
	 * @author wangpeng
	 * @date 2015年6月3日
	 */
	@RequestMapping("metadata/isExistXML")
	@ResponseBody
	public Map<String, Object> isExistXML(@RequestParam("id")String id){
		int result = documentService.isExistXMLById(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("docXml", result>0?true:false);
		return map;
	}
	
	/**
	 * 
	 * @Description: 校验文档/实体是否存在
	 * @param  Map<String,Object>
	 * @return Map<String,Object>
	 * @throws 
	 *
	 * @author wangpeng
	 * @date 2015年6月3日
	 */
	@RequestMapping("metadata/validateDocInfo")
	@ResponseBody
	public Map<String, Object> validateDocInfo(DocConf docConf){
		int result = documentService.validateDocInfo(docConf);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", result>0?true:false);
		return map;
	}

    /**
     * 根据文档ID判断该文档是否已经存在数据映射关系
     * @param id
     * @return
     */
    @RequestMapping("metadata/isDocMapping")
    @ResponseBody
    public Map<String, Object> isDocMapping(@RequestParam("id")String id){
        int result = documentService.isDocMapping(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("docMapping", result > 0 ? true : false);
        return map;
    }
    
    
    /*************************************************************  实体结构  start ********************************************************/
    /**
     * 
     * @Description: 实体结构设置跳转页
     * @param  String id
     * @param  String name
     * @param  ModelMap map
     * @return String
     * @throws 
     *
     * @author wangpeng
     * @date 2015年6月3日
     */
    @RequestMapping("metadata/regvStructor")
	public String regvStructor(@RequestParam("id")String id,@RequestParam("name")String name,ModelMap map){
		DocTreeInfo doctree = documentService.getInfoById(id);
		List<Dictionary> versions = dictionaryService.getDictionaryByTypeCode("datasetversion");
		List<String> list = documentService.getDatasetNames();
        List<Dictionary> standardList = dictionaryService.getDictionaryByTypeCode("standorgan");
        //文档是否已存在数据映射关系（现在改成不需要判断，修改人林彬）
//        int result = documentService.isDocMapping(id);
//        map.put("docMapping", result > 0 ? true : false);
        for(TreeInfo info:doctree.getTreeInfos()){
        	if("ClinicalDocument".equals(info.getFieldId())){
        		info.setName(name);
        	}
        }
		String str = JsonUtil.writeValueAsString(doctree.getTreeInfos());
		map.put("docId", doctree.getDocId());
		map.put("list", str);
		map.put("dataset", list);
		map.put("versions", versions);
        map.put("standardList", standardList);
		map.put("docName", name);
		return "dip/metadata/register_version";
	}
    
    
    
    /*************************************************************  实体结构  end ********************************************************/
}
