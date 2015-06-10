/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Jun 24, 2013		元数据管理系统控制层
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
import com.zebone.btp.app.dict.pojo.DictionaryType;
import com.zebone.btp.app.dict.service.DictionaryService;
import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.metadata.service.MetaDataService;
import com.zebone.dip.metadata.vo.DataSetConf;
import com.zebone.dip.metadata.vo.DatasetMapping;
import com.zebone.dip.metadata.vo.FieldConf;
import com.zebone.util.DateUtil;
import com.zebone.util.Encodes;
import com.zebone.util.EncodingTool;
import com.zebone.util.UUIDUtil;

@Controller
public class MetaDataController {
	
	@Resource
	private DictionaryService dictionaryService;
	
	@Resource
	private MetaDataService metaDataService;
	
	@RequestMapping("metadata/index")
	public String index(){
		return "dip/metadata/index";
	}
	
	@RequestMapping("metadata/metadataTree")
	public String metadataTree(){
		return "dip/metadata/metadata_tree";
	}
	
	@RequestMapping("metadata/metadataIndex")
	public String metadataIndex(ModelMap map){
		List<Dictionary> list = dictionaryService.getDictionaryByTypeCode("mulufenlei");
		map.put("list", list);
		return "dip/metadata/metadata_index";
	}
	
	@RequestMapping("metadata/metadataEdit")
	public String metadataEdit(@RequestParam("id")String id,ModelMap map){
		FieldConf fieldConf = metaDataService.getMetadataINfoById(id);
		List<Dictionary> list = dictionaryService.getDictionaryByTypeCode("mulufenlei");
		map.put("fieldConf", fieldConf);
		map.put("list", list);
		return "dip/metadata/metadata_edit";
	}
	
	@RequestMapping("metadata/datasetIndex")
	public String datasetIndex(ModelMap map){
		List<Dictionary> list = dictionaryService.getDictionaryByTypeCode("standorgan");
		map.put("list", list);
		return "dip/metadata/dataset_index";
	}
	
	@RequestMapping("metadata/metadataList")
	@ResponseBody
	public JsonGrid metadataList(FieldConf fieldConf,Pagination<FieldConf> page){
        if (fieldConf.getFieldId() != null && !"".equals(fieldConf.getFieldId())) {
            fieldConf.setFieldId(Encodes.urlDecode(fieldConf.getFieldId()));
        }
        if (fieldConf.getFieldName() != null && !"".equals(fieldConf.getFieldName())) {
            String fieldName = Encodes.urlDecode(fieldConf.getFieldName());
            fieldConf.setFieldName(EncodingTool.escapeStr(fieldName));
        }
        Pagination<FieldConf> p = metaDataService.metadataPage(page,fieldConf);
		JsonGrid jGrid = new JsonGrid("true",p.getTotalCount(),p.getData());
		return jGrid;
	}
	
	@RequestMapping("metadata/metadataSave")
	@ResponseBody
	public Map<String, Object> metadataSave(FieldConf fieldConf){
		int result =0;
		if(StringUtils.isEmpty(fieldConf.getId())){
			fieldConf.setId(UUIDUtil.getUuid());
			fieldConf.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
			result = metaDataService.saveMetadata(fieldConf);
		}else{
			result = metaDataService.updateMetadata(fieldConf);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", result == 1 ? true:false);
		map.put("id", fieldConf.getId());
		return map;
	}
	
	@RequestMapping("metadata/metadataRemove")
	@ResponseBody
	public Map<String, Object> metadataRemove(@RequestParam("id")String id){
		int result =0;
		String msg = "";
		result = metaDataService.removeMetadataByIds(id.split(","));
		/**
		if(result == 2){
			msg = "元数据与数据集绑定，无法删除";
		}else if(result == 3){
			msg = "元数据与文档绑定，无法删除";
		}else if(result == 4){
			msg = "元数据与数据集和文档绑定，无法删除";
		}*/
		if(result == 2) msg = "元数据被使用，无法删除";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", result == 1 ? true:false);
		map.put("msg", msg);
		return map;
	}
	
	@RequestMapping("metadata/datasetList")
	@ResponseBody
	public JsonGrid datasetList(DataSetConf dataSetConf,Pagination<DataSetConf> page){
        if (dataSetConf.getDatasetName() != null && !"".equals(dataSetConf.getDatasetName())) {
            String dataSetName = Encodes.urlDecode(dataSetConf.getDatasetName());
            dataSetConf.setDatasetName(EncodingTool.escapeStr(dataSetName));
        }
        Pagination<DataSetConf> p = metaDataService.datasetPage(page,dataSetConf);
		JsonGrid jGrid = new JsonGrid("true",p.getTotalCount(),p.getData());
		return jGrid;
	}
	
	@RequestMapping("metadata/datasetEdit")
	public String datasetEdit(@RequestParam("id")String id,ModelMap map){
		DataSetConf dataSetConf = metaDataService.getDataSetConfById(id);
		List<Dictionary> list = dictionaryService.getDictionaryByTypeCode("standorgan");
		List<Dictionary> versions = dictionaryService.getDictionaryByTypeCode("datasetversion");
		map.put("dataSetConf", dataSetConf);
		map.put("list", list);
		map.put("versions", versions);
		return "dip/metadata/dataset_edit";
	}
	
	@RequestMapping("metadata/datasetSave")
	@ResponseBody
	public Map<String, Object> datasetSave(DataSetConf dataSetConf){
		int result = 0;
		if(StringUtils.isEmpty(dataSetConf.getId())){
			dataSetConf.setId(UUIDUtil.getUuid());
			dataSetConf.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
			result = metaDataService.saveDataSetConf(dataSetConf);
		}else{
			result = metaDataService.updateDataSetConf(dataSetConf);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", result == 1 ? true:false);
		map.put("id", dataSetConf.getId());
		return map;
	}
	
	@RequestMapping("metadata/datasetMetadata")
	public String datasetMetadata(@RequestParam("id")String id,@RequestParam("datasetName")String datasetName,ModelMap map){
		map.put("id", id);
		map.put("datasetName", datasetName);
		return "dip/metadata/dataset_metadata";
	}
	
	@RequestMapping("dataset/datasetRemove")
	@ResponseBody
	public Map<String, Object> datasetInfoRemove(@RequestParam("id")String id){
		int result = metaDataService.removeDatasetInfo(id.split(","));
		String msg = "";
		if(result == 2){
			msg = "数据集被使用，无法删除";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", result==1?true:false);
		map.put("msg", msg);
		return map;
	}
	
	@RequestMapping("metadata/metaListByDatasetId")
	@ResponseBody
	public Map<String, Object> metaListByDatasetId(@RequestParam("id")String id,@RequestParam("query") String name){
		FieldConf f = new FieldConf();
		f.setFieldName(name);
		f.setId(id);
		List<FieldConf> objs = metaDataService.getMetadatasByQuery(f);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", objs);
		map.put("success", true);
		int total =0;
		if(objs != null && objs.size() > 0){
			total = objs.size();
		}
		map.put("query", name);
		map.put("total", total);
		return map;
	}
	
	@RequestMapping("metadata/metadataAdd")
	@ResponseBody
	public Map<String, Object> metadataAdd(DatasetMapping datasetMapping){
		datasetMapping.setId(UUIDUtil.getUuid());
		int result = metaDataService.addMetadataInfo(datasetMapping);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", result==1?true:false);
		return map;
	}
	
	@RequestMapping("metadata/metadataRevoke")
	@ResponseBody
	public Map<String, Object> metadataRevoke(@RequestParam("fieldId")String fieldId,@RequestParam("datasetId")String datasetId){
		int result = metaDataService.revokeMetadata(fieldId.split(","),datasetId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", result==1?true:false);
		return map;
	}
	@RequestMapping("metadata/datasetMetadataList")
	@ResponseBody
	public JsonGrid datasetMetadataList(FieldConf fieldConf,Pagination<FieldConf> page){
		Pagination<FieldConf> p = metaDataService.datasetMetadataPage(page,fieldConf);
		JsonGrid jGrid = new JsonGrid("true",p.getTotalCount(),p.getData());
		return jGrid;
	}
	
	@RequestMapping("metadata/isExistsFieldCode")
	@ResponseBody
	public Map<String, Object> isExistsFieldCode(@RequestParam("fieldCode")String fieldCode,@RequestParam("id")String id){
		int res = metaDataService.isExistsFieldCode(fieldCode,id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", res>0?true:false);
		return map;
	}
	
	@RequestMapping("metadata/isExistsFieldId")
	@ResponseBody
	public Map<String, Object> isExistsFieldId(@RequestParam("fieldId")String fieldId,@RequestParam("id")String id){
		int res = metaDataService.isExistsFieldId(fieldId,id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", res>0?true:false);
		return map;
	}
	
	@RequestMapping("metadata/dictListByName")
	@ResponseBody
	public Map<String, Object> dictListByName(@RequestParam("id")String id,@RequestParam("query") String name){
		List<DictionaryType> list = metaDataService.getListInfoById(id,name);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		map.put("success", true);
		int total =0;
		if(list != null && list.size() > 0){
			total = list.size();
		}
		map.put("query", name);
		map.put("total", total);
		return map;
	}
	
	@RequestMapping("metadata/metadataIsUsingById")
	@ResponseBody
	public Map<String, Object> metadataIsUsingById(@RequestParam("id")String id){
		int result = metaDataService.metadataIsUsingById(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result == 1 ?"1":"2");
		return map;
	}
	
	@RequestMapping("metadata/existsDatasetInfo")
	@ResponseBody
	public Map<String, Object> existsDatasetInfo(DataSetConf dataSetConf){
		int result = metaDataService.existsDatasetInfo(dataSetConf);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result == 1 ?"1":"2");
		return map;
	}
	
	@RequestMapping("metadata/datasetExistsYsjbm")
	@ResponseBody
	public Map<String, Object> datasetExistsYsjbm(DatasetMapping datasetMapping){
		int result = metaDataService.datasetExistsYsjbm(datasetMapping);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result == 1 ?"1":"2");
		return map;
	}
	
	@RequestMapping("metadata/dataListByDatasetName")
    @ResponseBody
    public Map<String, Object> dataListByDatasetName(@RequestParam("query") String name){
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<DataSetConf> list = metaDataService.getListByDatasetName(name);
    	map.put("data", list);
		map.put("success", true);
		int total =0;
		if(list != null && list.size() > 0){
			total = list.size();
		}
		map.put("query", name);
		map.put("total", total);
    	return map;
    }
	/**
	@RequestMapping("metadata/uploadfile")
	public String uploadFile(HttpServletRequest request,@RequestParam("importFile")MultipartFile file,ModelMap map){
		if(!file.isEmpty()){
			String contextPath = RequestContextUtils.getWebApplicationContext(request).getServletContext().getRealPath("/");
			if(!(contextPath.endsWith("/") || contextPath.endsWith("\\"))){
				contextPath = contextPath + "/";
			}
			String uploadPath = contextPath + "upload/metadata/";//上传路径
			File uploadFile = new File(uploadPath);
			if(!uploadFile.exists()){
				uploadFile.mkdirs();
			}
			String contentType = file.getOriginalFilename();
			String str = contentType.substring(contentType.lastIndexOf("."));
			String newFileName = System.currentTimeMillis()+str;//新文件名
			try {
				file.transferTo(new File(uploadPath+newFileName));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			map.put("fileName",uploadPath+newFileName);
		}
		
		return "dip/metadata/metadata_import";
	}
	
	@RequestMapping("metadata/metadataImport")
	public String metadataImport(){
		return "dip/metadata/metadata_import";
	}
	
	@RequestMapping("metadata/importFile")
	@ResponseBody
	public Map<String, Object> importFile(@RequestParam("fileName")String fileName){
		
		Map<String, Object> map = new HashMap<String, Object>();
		return map;
	}
	*/
}
