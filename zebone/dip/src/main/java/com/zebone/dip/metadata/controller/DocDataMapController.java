package com.zebone.dip.metadata.controller;

import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.dip.metadata.service.DocDataMapService;
import com.zebone.dip.metadata.service.Dom4jXmlService;
import com.zebone.dip.metadata.vo.AcResult;
import com.zebone.dip.metadata.vo.DocConf;
import com.zebone.dip.metadata.vo.DocDataMap;
import com.zebone.dip.metadata.vo.DocDataMapForm;
import com.zebone.dip.metadata.vo.FDocMapping;
import com.zebone.dip.metadata.vo.MdNode;

/**
 * 文档数据映射controller
 * 
 * @author cz
 */
@Controller
@RequestMapping("/metadata")
public class DocDataMapController {

	@Resource
	private DocDataMapService docDataMapService;
	
	@Resource
	private Dom4jXmlService dom4jXmlService;
    
	/**
	 * 获取系统所有的文档
	 * @param map
	 * @return
	 */
	@RequestMapping("/docmapIndex")
	public String index(@RequestParam("id") String docId, ModelMap map) {
		String treeValue = "";
		List<MdNode> mdNodeList = null;
		if(StringUtils.isNotEmpty(docId)){
			DocConf dc = docDataMapService.getDocById(docId);
            map.put("docName", dc.getDocName());
			mdNodeList = dom4jXmlService.loadNodes(dc.getDocXml());
			treeValue = docDataMapService.loadNodes(mdNodeList, docId);
		}
		map.put("treeValue", treeValue);
		map.put("docId", docId);
		return "dip/metadata/docdatamap_index";
	}
	
	
	/** 
	 * 分批加载数据
	 * @param docId  文档id
	 * @param start  开始位置
	 * @param skip   数据量
	 * @param map
	 * @return
	 * @author 陈阵 
	 * @date 2013-8-17 上午8:00:38
	 */
	@RequestMapping("/getDocDataMap")
	@ResponseBody
	public List<DocDataMap> getDocDataMap(@RequestParam("docId") String docId,@RequestParam(value="start",defaultValue="0") int start,
			@RequestParam(value="skip",defaultValue="50") int skip, ModelMap map) {
		List<MdNode> mdNodeList = null;
		List<MdNode> subMdNodeList = null;
		List<DocDataMap> subddmList = null;
		if(StringUtils.isNotEmpty(docId)){
			DocConf dc = docDataMapService.getDocById(docId);
            map.put("docName", dc.getDocName());
			mdNodeList = dom4jXmlService.loadNodes(dc.getDocXml());
			int nodeListSize = mdNodeList.size();
			int startIndex = start * skip;
			int endIndex = startIndex + skip;
			
			if(startIndex >= nodeListSize){
				return subddmList;
			}else if(endIndex >= nodeListSize){
				endIndex = nodeListSize;
			}
			subMdNodeList = mdNodeList.subList(startIndex, endIndex);
			subddmList = docDataMapService.loadNodesMap(subMdNodeList,docId, startIndex);

		}
      return subddmList;
	}
	
	/**
	 * 根据文档id加载文档 
	 * @param docId
	 * @return
	 */
	@RequestMapping("/loadDocDataMap")
	@ResponseBody
	public List<DocDataMap> loadDocDataMap(@RequestParam("docId") String docId){
		return null;
	}
	
	/**
	 * 模糊查询表名
	 * @param table
	 * @return
	 */
	@RequestMapping("/getTable")
	@ResponseBody
	public AcResult getTable(@RequestParam("query") String table){
		AcResult json = new AcResult();
		if(StringUtils.isNotEmpty(table)){
			 json = docDataMapService.getTable(table);
		}
	    return json;
	}
	
	/**
	 *  模糊查询列名
	 * @param col
	 * @param tableId
	 * @return
	 */
	@RequestMapping("/getCol")
	@ResponseBody
	public AcResult getCol(@RequestParam("query") String col,@RequestParam("tableId") String tableId){
		AcResult json = new AcResult();
		if(StringUtils.isNotEmpty(col) && StringUtils.isNotEmpty(tableId)){
			json = docDataMapService.getCol(tableId, col);
		}
		if(StringUtils.isNotEmpty(tableId) && StringUtils.isEmpty(col)){
			json = docDataMapService.getAllCol(tableId);
		}
		return json;
	}
	
	/**
	 * 新增文档数据映射
	 * @param ddmf
	 * @return
	 */
	@RequestMapping("/addDocDataMap")
	@ResponseBody
	public String addDocDataMap(DocDataMapForm ddmf){
		List<FDocMapping> fdmList = ddmf.getDdm();
		docDataMapService.saveDocDataMap(fdmList);
		return "succeed";
	}
}
