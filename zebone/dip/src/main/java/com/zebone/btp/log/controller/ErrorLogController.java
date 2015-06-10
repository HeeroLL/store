package com.zebone.btp.log.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.log.pojo.ErrorLog;
import com.zebone.btp.log.service.ErrorLogService;

/**
 * 错误日志
 * @author 宋俊杰
 *
 */
@Controller
@RequestMapping("btp/errorlog")
public class ErrorLogController {
	private static final Log log = LogFactory.getLog(ErrorLogController.class);
	@Resource
	private ErrorLogService errorLogService;
	
	@RequestMapping("/index")
	public String index(){
		return "btp/log/errorlog_index";
	}
	
	@RequestMapping("/findErrorLog")
	@ResponseBody
	public JsonGrid findErrorLog(String className, String methodName,
			String errorinfo,
			@RequestParam(required = false) Date beginCreateTime,
			@RequestParam(required = false) Date endCreateTime,
			Pagination<ErrorLog> pagination) {
		className = className != null ? className.trim():null;
		methodName = methodName != null ? methodName.trim():null;
		pagination = this.errorLogService.findErrorInfo(className, methodName,
				errorinfo, beginCreateTime, endCreateTime, pagination);
		JsonGrid jsonGrid = new JsonGrid();
		jsonGrid.setData(pagination.getData());
		jsonGrid.setTotal(pagination.getTotalCount());
		jsonGrid.setSuccess("true");
		return jsonGrid;
	}
	
	@RequestMapping("/getErrorInfoById")
	@ResponseBody
	public String  getErrorInfoById(String errorLogid){
		String errorInfo = this.errorLogService.getErrorInfoById(errorLogid);
		return errorInfo;
	}
	
	@RequestMapping("/deleteErrorLog")
	@ResponseBody
	public  Map<String, Object> deleteErrorLog(@RequestParam("id")String errorLogid){
		 Map<String, Object> map = new HashMap<String, Object>();
		try {
			this.errorLogService.deleteById(errorLogid);
		} catch (Exception e) {
			log.error("",e);
			 map.put("success", false);
			return map;
		}
		 map.put("success", true);
		return map;
	}
}
