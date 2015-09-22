package com.zebone.dip.compare.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 字典对照模块controller
 * @author YinCM
 *
 */
@Controller
public class CompareDictionaryController {

	/**
	 * 模板载入地址
	 * @return
	 */
	@RequestMapping("compare/dictionary/index")
	public String compareLoadModuleIndex(){
		return "dip/compare/dictionary/dictionaryLoadIndex";
	}
	
	/**
	 * 文件上传
	 * @return
	 */
	@RequestMapping("compare/dictionary/uploadFile")
	@ResponseBody
	public Map<String, String> fileUpload(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("hello.jpg", "hello.jpg");
		map.put("successCount", "1200");
		map.put("failCount", "12");
		map.put("detailDownload", "aqqq.xslt");
		
		return map;
	}
	
	
}
