package com.zebone.dip.log;

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

import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.log.service.LogService;
import com.zebone.dip.log.vo.DocLog;
import com.zebone.dip.log.vo.LogDetail;
import com.zebone.dip.log.vo.ParmInfo;

/**
 * 类描述：审计日志控制层
 * @author: caixl
 * @date： 日期：Sep 10, 2013
 * @version 1.0
 */
@Controller
public class LogController {
	@Resource
	private LogService logService;
	
	@RequestMapping("log/logIndex")
	public String logIndex(ModelMap map){
		List<ParmInfo> list = logService.getDictInfo();
		map.put("list", list);
		return "dip/log/log_index";
	}
	
	@RequestMapping("log/logOrganInfo")
	@ResponseBody
	public JsonGrid logOrganInfo(@RequestParam("query")String name,Pagination<ParmInfo> page){
		Pagination<ParmInfo> p = logService.getOrganInfo(name,page);
		JsonGrid jGrid = new JsonGrid("true",p.getTotalCount(),p.getData());
		return jGrid;
	}
	@RequestMapping("log/docLogInfoList")
	@ResponseBody
	public JsonGrid docLogInfoList(DocLog docLog,Pagination<DocLog> page){
		if(!StringUtils.isEmpty(docLog.getUploadTime())){
			docLog.setUploadTime(docLog.getUploadTime().trim()+" 00:00:00");
		}
		if(!StringUtils.isEmpty(docLog.getReceiveStatus())){
			docLog.setReceiveStatus(docLog.getReceiveStatus().trim()+" 23:59:59");
		}
		Pagination<DocLog> p = logService.getDocLogPage(docLog,page);
		JsonGrid jGrid = new JsonGrid("true",p.getTotalCount(),p.getData());
		return jGrid;
	}
	
	@RequestMapping("log/logInfoLoad")
	@ResponseBody
	public Map<String, Object> logInfoLoad(LogDetail logDetail){
		Map<String, Object> map = new HashMap<String, Object>();
		LogDetail logDetail1 = logService.getLogDetailByLogInfo(logDetail);
		if(logDetail1 != null){
			map.put("success", true);
			map.put("data", logDetail1);
		}else{
			map.put("success", false);
		}
		return map;
	}
	@RequestMapping("log/index")
	public String index(){
		return "dip/log/index";
	}
	@RequestMapping("log/logTree")
	public String logTree(){
		return "dip/log/log_tree";
	}
}
